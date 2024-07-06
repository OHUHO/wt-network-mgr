<template>
  <div>
    <div v-click-outside="config" class="right-bar">
      <div data-simplebar class="h-100">
        <div class="rightbar-title px-3 py-4">
          <a
            href="javascript:void(0);"
            class="right-bar-toggle float-right"
            @click="hide"
          >
            <i class="mdi mdi-close noti-icon"></i>
          </a>
          <h5 class="m-0">项目配置</h5>
        </div>
        <div class="p-3">
          <hr class="mt-1" />
          <!-- <div class="setting-name">
            <span class="line"></span>
            <span class="name">主题</span>
            <span class="line"></span>
          </div>
          <div class="text-center flex-r">
            <b-form-checkbox
              v-model="loader"
              name="check-button"
              switch
              @input="changeloader"
            >
            </b-form-checkbox>
          </div> -->

          <div class="setting-name">
            <span class="line"></span>
            <span class="name">导航栏模式</span>
            <span class="line"></span>
          </div>

          <b-form-radio-group
            class="text-center flex-r"
            v-model="layout"
            stacked
            @input="changeLayout($event)"
          >
            <b-form-radio value="vertical" class="mb-1">垂直</b-form-radio>
            <b-form-radio value="horizontal" class="mb-1">水平</b-form-radio>
          </b-form-radio-group>

          <div class="setting-name">
            <span class="line"></span>
            <span class="name">宽度</span>
            <span class="line"></span>
          </div>
          <b-form-radio-group
            class="flex-r"
            v-model="width"
            stacked
            @input="changeWidth($event)"
          >
            <b-form-radio value="fluid" class="mb-1">自适应</b-form-radio>
            <b-form-radio value="boxed">固定</b-form-radio>
          </b-form-radio-group>

          <!-- <div v-if="layout === 'vertical'">
            <div class="setting-name">
              <span class="line"></span>
              <span class="name">菜单样式</span>
              <span class="line"></span>
            </div>
            <b-form-radio-group
              v-model="sidebarType"
              stacked
              @input="changeType($event)"
            >
              <b-form-radio value="dark" class="mb-1">Dark</b-form-radio>
              <b-form-radio value="light" class="mb-1">Light</b-form-radio>
              <b-form-radio value="compact" class="mb-1">Compact</b-form-radio>
              <b-form-radio value="icon" class="mb-1">Icon</b-form-radio>
              <b-form-radio value="colored">Colored</b-form-radio>
            </b-form-radio-group>
          </div>

          <div v-if="layout === 'horizontal'">
            <div class="setting-name">
              <span class="line"></span>
              <span class="name">菜单样式</span>
              <span class="line"></span>
            </div>
            <b-form-radio-group
              v-model="topbar"
              stacked
              @input="changeTopbartype($event)"
            >
              <b-form-radio value="dark" class="mb-1">Dark</b-form-radio>
              <b-form-radio value="light" class="mb-1">Light</b-form-radio>
            </b-form-radio-group>
          </div> -->

          <!-- <h6 class="mt-3">Preloader</h6>
          <hr class="mt-1" />

          <b-form-checkbox
            v-model="loader"
            name="check-button"
            switch
            @input="changeloader"
            >Preloader</b-form-checkbox
          > -->
        </div>

        <hr class="mt-1" />
      </div>
    </div>

    <div class="rightbar-overlay"></div>
  </div>
</template>

<script>
import { layoutMethods } from "@/state/helpers";

export default {
  data() {
    return {
      config: {
        handler: this.handleRightBarClick,
        middleware: this.middleware,
        events: ["click"],
      },
      layout: this.$store ? this.$store.state.layout.layoutType : {} || {},
      width: this.$store ? this.$store.state.layout.layoutWidth : {} || {},
      topbar: this.$store ? this.$store.state.layout.topbar : {} || {},
      sidebarType: this.$store
        ? this.$store.state.layout.leftSidebarType
        : {} || {},
      loader: this.$store ? this.$store.state.layout.loader : {} || {},
    };
  },
  methods: {
    ...layoutMethods,
    hide() {
      this.$parent.toggleRightSidebar();
    },
    // eslint-disable-next-line no-unused-vars
    handleRightBarClick(e, el) {
      this.$parent.hideRightSidebar();
    },
    // eslint-disable-next-line no-unused-vars
    middleware(event, el) {
      if (event.target.classList)
        return !event.target.classList.contains("toggle-right");
    },
    changeLayout(layout) {
      this.changeLayoutType({
        layoutType: layout,
      });
    },
    changeType(type) {
      return this.changeLeftSidebarType({
        leftSidebarType: type,
      });
    },
    changeWidth(width) {
      return this.changeLayoutWidth({
        layoutWidth: width,
      });
    },
    changeTopbartype(value) {
      return this.changeTopbar({
        topbar: value,
      });
    },
    changeloader() {
      return this.changeLoaderValue({
        loader: this.loader,
      });
    },
  },
};
</script>
