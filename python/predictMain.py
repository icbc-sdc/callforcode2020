import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import tensorflow as tf
import matplotlib.pyplot as plt
import datetime
from tensorflow.keras import models, layers, losses, metrics, callbacks
import sys
import demjson
import json
import pandas as pd
from dataProcess.dataPreProcess import PreProcess

returnCode = 0
ret_data = {'returnCode': returnCode, 'predictData': {}}


def return_result(returnCode=1, predictData={}):
    ret_data['returnCode'] = returnCode
    ret_data['predictData'] = predictData

def batch_dataset(dataset):
    dataset_batched = dataset.batch(WINDOW_SIZE, drop_remainder=True)
    return dataset_batched

class Block(layers.Layer):
    def __init__(self, **kwargs):
        super(Block, self).__init__(**kwargs)

    def call(self, x_input, x):
        x_out = tf.maximum((1 + x) * x_input[:, -1, :], 0.0)
        return x_out

    def get_config(self):
        config = super(Block, self).get_config()
        return config

    # 自定义损失函数，考虑平方差和预测目标平方的比值
class MSPE(losses.Loss):
    def call(self, y_true, y_pred):
        err_percent = (y_true - y_pred) ** 2 / (tf.maximum(y_true ** 2, 1e-7))
        mean_err_percent = tf.reduce_mean(err_percent)
        return mean_err_percent

    def get_config(self):
        config = super(MSPE, self).get_config()
        return config

def plot_metric(history, metric):
    train_metrics = history.history[metric]
    epochs = range(1, len(train_metrics) + 1)
    plt.plot(epochs, train_metrics, 'bo--')
    plt.title('Training ' + metric)
    plt.xlabel("Epochs")
    plt.ylabel(metric)
    plt.legend(["train_" + metric])
    plt.show()

if __name__ == "__main__":
    try:
        data = demjson.decode(sys.argv[1])
    except:
        ret_data = return_result()
        ret_json = json.dumps(ret_data)
        print(ret_json)
        sys.exit()
    data_preProcess = PreProcess('day')
    covid_data = data_preProcess.dataProcess(data)

    dfdata = covid_data.set_index("date")
    dfdiff = dfdata.diff(periods=1).dropna()
    dfdiff = dfdiff.reset_index("date")

    dfdiff.plot(x="date", y=["confirmed_num", "cured_num", "dead_num"], figsize=(10, 6))
    plt.xticks(rotation=60)
    dfdiff = dfdiff.drop("date", axis=1).astype("float32")

    # 用某日前8天窗口数据作为输入预测该日数据
    WINDOW_SIZE = 8

    ds_data = tf.data.Dataset.from_tensor_slices(tf.constant(dfdiff.values, dtype=tf.float32)) \
        .window(WINDOW_SIZE, shift=1).flat_map(batch_dataset)

    ds_label = tf.data.Dataset.from_tensor_slices(
        tf.constant(dfdiff.values[WINDOW_SIZE:], dtype=tf.float32))

    # 数据较小，可以将全部训练数据放入到一个batch中，提升性能
    ds_train = tf.data.Dataset.zip((ds_data, ds_label)).batch(38).cache()

    tf.keras.backend.clear_session()
    x_input = layers.Input(shape=(None, 3), dtype=tf.float32)
    x = layers.LSTM(3, return_sequences=True, input_shape=(None, 3))(x_input)
    x = layers.LSTM(3, return_sequences=True, input_shape=(None, 3))(x)
    x = layers.LSTM(3, return_sequences=True, input_shape=(None, 3))(x)
    x = layers.LSTM(3, input_shape=(None, 3))(x)
    x = layers.Dense(3)(x)

    # 考虑到新增确诊，新增治愈，新增死亡人数数据不可能小于0，设计如下结构
    # x = tf.maximum((1+x)*x_input[:,-1,:],0.0)
    x = Block()(x_input, x)
    model = models.Model(inputs=[x_input], outputs=[x])
    model.summary()

    optimizer = tf.keras.optimizers.Adam(learning_rate=0.01)
    model.compile(optimizer=optimizer, loss=MSPE(name="MSPE"))
    logdir = "./data/keras_model/" + datetime.datetime.now().strftime("%Y%m%d-%H%M%S")

    tb_callback = tf.keras.callbacks.TensorBoard(logdir, histogram_freq=1)
    # 如果loss在100个epoch后没有提升，学习率减半。
    lr_callback = tf.keras.callbacks.ReduceLROnPlateau(monitor="loss", factor=0.5, patience=100)
    # 当loss在200个epoch后没有提升，则提前终止训练。
    stop_callback = tf.keras.callbacks.EarlyStopping(monitor="loss", patience=200)
    callbacks_list = [tb_callback, lr_callback, stop_callback]

    history = model.fit(ds_train, epochs=500, callbacks=callbacks_list)

    # 预测此后100天的新增走势,将其结果添加到dfresult中
    for i in range(100):
        arr_predict = model.predict(tf.constant(tf.expand_dims(dfresult.values[-38:, :], axis=0)))

        dfpredict = pd.DataFrame(tf.cast(tf.floor(arr_predict), tf.float32).numpy(),
                                 columns=dfresult.columns)
        dfresult = dfresult.append(dfpredict, ignore_index=True)

    dfresult.query("confirmed_num==0").head()
    dfresult.query("cured_num==0").head()
    dfresult.query("dead_num==0").head()

    model.save('./data/tf_model_savedmodel', save_format="tf")
    model_loaded = tf.keras.models.load_model('./data/tf_model_savedmodel', compile=False)
    optimizer = tf.keras.optimizers.Adam(learning_rate=0.001)
    model_loaded.compile(optimizer=optimizer, loss=MSPE(name="MSPE"))
    predict_data = model_loaded.predict(ds_train)
    ret_data = return_result(0,predict_data)
    ret_json = demjson.encode(ret_data)
    print(ret_json)

