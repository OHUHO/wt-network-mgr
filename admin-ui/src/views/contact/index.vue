<template>
  <Layout>
    <PageHeader :title="title" :items="items" />
    <div>
      <user></user>
    </div>
  </Layout>
</template>

<script>
import Layout from "@/layout/index";
import PageHeader from "@/layout/common/page-header";
import user from "@/views/contact/components/user";

export default {
  components: { Layout, PageHeader,user },

  data() {
    return {
      title: "通讯录管理",
      items: [
        {
          text: "吴彦组网络管理系统",
        },
        {
          text: "通讯录管理",
          active: true,
        },
      ],
    };
  },
};
</script>

<style lang="scss" scoped></style>
