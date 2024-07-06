<template>
  <Layout>
    <PageHeader :title="title" :items="items" />
    <div>
      <noticeList/>
    </div>
  </Layout>
</template>

<script>
import Layout from "@/layout/index";
import PageHeader from "@/layout/common/page-header";
import noticeList from "@/views/notice/components/noticeList";


export default {
  components: { Layout, PageHeader,noticeList},

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
