import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    isDarkTheme: true
  },
  mutations: {
    TOGGLE_THEME(state) {
      state.isDarkTheme = !state.isDarkTheme;
    }
  },
  actions: {},
  modules: {},
  plugins: [createPersistedState()]
});
