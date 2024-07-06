<template>
  <Layout>
    <PageHeader :title="title" :items="items" />
    <div class="row">
      <div class="col-xl-8">
        <Widget />
        <Revenue />
      </div>
      <div class="col-xl-4">
        <SalesAnalytics />
        <EarningReport />
      </div>
    </div>
  </Layout>
</template>

<script>
import Layout from "@/layout/index";
import PageHeader from "@/layout/common/page-header";

import Widget from "@/components/widget";
import Revenue from "@/components/revenue";
import SalesAnalytics from "@/components/sales-analytics";
import EarningReport from "@/components/earning";

export default {
  components: {
    Layout,
    PageHeader,
    Widget,
    Revenue,
    SalesAnalytics,
    EarningReport,
  },
  data() {
    return {
      title: "主页",
      items: [
        {
          text: "吴彦组网络管理系统",
        },
        {
          text: "主页",
          active: true,
        },
      ],
    };
  },
  mounted() {
    this.findUserInfo();
  },
  methods: {
    findUserInfo() {},
  },
};
</script>
