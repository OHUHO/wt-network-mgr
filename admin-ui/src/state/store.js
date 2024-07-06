import Vue from "vue";
import Vuex from "vuex";

import modules from "./modules";

Vue.use(Vuex);

const store = new Vuex.Store({
  modules,
  // https://vuex.vuejs.org/guide/strict.html
  // strict: process.env.NODE_ENV !== "production",
});

export default store;
