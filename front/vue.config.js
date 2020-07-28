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
  }
};
