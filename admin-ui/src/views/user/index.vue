<template>
  <Layout>
    <PageHeader :items="items" :title="title"/>
     <userList/>
  </Layout>
</template>

<script>
import Layout from "../../layout";
import PageHeader from "../../layout/common/page-header";
import userList from "./component/userList";


// import Customers from "@/views/user/component/customers";


export default {
  components: {Layout, PageHeader, userList},

  data() {
    return {
      title: "用户管理",
      display: true,
      items: [
        {
          text: "吴彦组网络管理系统",
        },
        {
          text: "用户管理",
          active: true,
        },
      ],
    };
  },

  methods: {
    dian1() {
      this.display = true
    },
    dian2() {
      this.display = false
    }
  }
};
</script>

<style lang="scss" scoped>

</style>
