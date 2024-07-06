<script>
import HorizontalTopbar from "@/layout/horizontal/topbar";
import HorizontalNav from "@/layout/horizontal/navbar";
import RightBar from "@/layout/common/settings";
import Footer from "@/layout/common/footer";

import { layoutComputed } from "@/state/helpers";

export default {
  components: {
    HorizontalTopbar,
    HorizontalNav,
    Footer,
    RightBar,
  },
  data() {
    return {};
  },
  computed: {
    ...layoutComputed,
  },
  created: () => {
    document.body.setAttribute("data-layout", "horizontal");
    document.body.setAttribute("data-topbar", "dark");
    document.body.removeAttribute("data-sidebar", "dark");
    document.body.removeAttribute("data-layout-size", "boxed");
  },
  methods: {
    toggleRightSidebar() {
      document.body.classList.toggle("right-bar-enabled");
    },
    hideRightSidebar() {
      document.body.classList.remove("right-bar-enabled");
    },
  },
  mounted() {
    document.body.classList.remove("auth-body-bg");
    if (this.loader === true) {
      document.getElementById("preloader").style.display = "block";
      document.getElementById("status").style.display = "block";

      setTimeout(function() {
        document.getElementById("preloader").style.display = "none";
        document.getElementById("status").style.display = "none";
      }, 2500);
    } else {
      document.getElementById("preloader").style.display = "none";
      document.getElementById("status").style.display = "none";
    }
  },
};
</script>

<template>
  <div>
    <div id="preloader">
      <div id="status">
        <div class="spinner">
          <i class="ri-loader-line spin-icon"></i>
        </div>
      </div>
    </div>

    <div id="layout-wrapper">
      <HorizontalTopbar :type="topbar" :width="layoutWidth" />
      <HorizontalNav />
      <div class="main-content">
        <div class="page-content">
          <div class="container-fluid">
            <slot />
          </div>
        </div>
        <Footer />
      </div>
    </div>
    <RightBar />
  </div>
</template>
