<template>
  <Layout>
    <PageHeader :title="title" :items="items" />
    <div>
      <notice/>
    </div>
  </Layout>
</template>

<script>
import Layout from "@/layout/index";
import PageHeader from "@/layout/common/page-header";
import notice from "@/views/notice/components/notice";


export default {
  components: { Layout, PageHeader,notice },

  data() {
    return {
      title: "公告管理",
      items: [
        {
          text: "吴彦组网络管理系统",
        },
        {
          text: "公告管理",
          active: true,
        },
      ],
    };
  },
};
</script>

<style lang="scss" scoped></style>
