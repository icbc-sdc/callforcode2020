# -*- coding:UTF-8 -*-

import pandas as pd

class PreProcess(object):
    def __init__(self,window='d'):
        self.n_window = window

    def dataProcess(self,data):
        time_stamp1 = data['date']
        confirmed_num1 = data['confirmed_num']
        cured_num1 = data['cured_num']
        dead_num1 = data['dead_num']
        time_stamp2 = list(map(str,time_stamp1.split(',')))
        confirmed_num2 = list(map(int, confirmed_num1.split(',')))
        cured_num2 = list(map(int, cured_num1.split(',')))
        dead_num2 = list(map(int, dead_num1.split(',')))
        covid_data = pd.DataFrame(columns=['date','confirmed_num','cured_num','dead_num'])
        return covid_data