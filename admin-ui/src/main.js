import Vue from "vue";
import App from "./App.vue";
import BootstrapVue from "bootstrap-vue";
import VueApexCharts from "vue-apexcharts";
import Vuelidate from "vuelidate";
import VueSweetalert2 from "vue-sweetalert2";
import VueMask from "v-mask";

import vco from "v-click-outside";

import router from "./router";
import store from "@/state/store";
import i18n from "@/i18n/index.js";
import "@/http/index.js";

import "@/assets/scss/app.scss";

import GoEasy from "goeasy";

Vue.config.productionTip = false;
Vue.use(BootstrapVue);
Vue.use(vco);
Vue.use(Vuelidate);
Vue.use(VueSweetalert2);
Vue.use(VueMask);
Vue.use(require("vue-chartist"));
Vue.component("apexchart", VueApexCharts);

new Vue({
  router,
  store,
  i18n,
  render: (h) => h(App),
}).$mount("#app");

const goEasy = GoEasy.getInstance({
  host: "hangzhou.goeasy.io", //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
  appkey: "BC-f9dd1850a64d459cb4cc8f339f8fb4eb", // common key,
  modules: ["im"],
});

Vue.prototype.GoEasy = GoEasy;
Vue.prototype.goEasy = goEasy;
