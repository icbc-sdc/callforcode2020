module.exports = {
  transpileDependencies: ['vuetify'],
  publicPath: './',
  assetsDir: 'static',
  chainWebpack: config => {
    // config.module
    //   .rule('eslint')
    //   .use('eslint-loader')
    //   .options({
    //     fix: true,
    //     https: true
    //   });
  },
  pwa: {
    workboxOptions: {
      skipWaiting: true
    }
  },
  devServer:{
    proxy: {
      '/api': {
        target: 'http://520d00a7.nat123.fun', // 你请求的第三方接口
        // target: 'http://localhost:9080',
        changeOrigin: true, // 在本地会创建一个虚拟服务端，然后发送请求的数据，并同时接收请求的数据，这样服务端和服务端进行数据的交互就不会有跨域问题
        pathRewrite: {  // 路径重写，
          '^/api': ''  // 替换target中的请求地址，也就是说以后你在请求http://api.douban.com/v2/XXXXX这个地址的时候直接写成/api即可。
        }
      }
    }
  }

};
