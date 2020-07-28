/* eslint-disable global-require */
/* eslint-disable no-underscore-dangle */
import Vue from 'vue';
import { Icon } from 'leaflet';
// import App from './App.vue';
import vuetify from './plugins/vuetify';
import './plugins/vue2-filters';
import './plugins/vue2-perfect-scrollbar';
import 'leaflet/dist/leaflet.css';
import store from './store';
import Helpers from './helpers';
import router from './router';
import Layout from './Layout.vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

// this part resolve an issue where the markers would not appear
delete Icon.Default.prototype._getIconUrl;

Vue.config.productionTip = false;

Vue.filter('URLify', Helpers.URLify);
Vue.filter('fromNow', Helpers.fromNow);

Vue.use(ElementUI);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  vuetify,
  store,
  router,
  render: h => h(Layout)
});
