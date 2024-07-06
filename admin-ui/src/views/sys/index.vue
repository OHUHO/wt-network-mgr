<template>
  <Layout>
    <PageHeader :title="title" :items="items" />
    <my_information/>
    <br/>

    <div>
      <my_create/>
    </div>
  </Layout>
</template>

<script>
import Layout from "../../layout";
import PageHeader from "../../layout/common/page-header";
import my_create from "./components/my_create";
import my_information from "./components/my_information";

export default {
  components: { Layout, PageHeader ,my_create,my_information},

  data() {
    return {
      title: "我的信息",
      items: [
        {
          text: "吴彦组网络管理系统",
        },
        {
          text: "我的信息",
          active: true,
        },
      ],
    };
  },
};
</script>

<style lang="scss" scoped></style>
