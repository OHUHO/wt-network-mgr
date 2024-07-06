<template>
  <Layout>
    <PageHeader :title="title" :items="items" />
    <div class="row">
      <div class="col-lg-12">
        <div class="card">
          <div class="card-body">
            <div class="row"></div>
            <div class="mt-4" v-if="allDatas != null">
              <h5 class="font-size-14 mb-3">
                <strong>{{ allDatas.title }}</strong>
              </h5>
              <div class="product-desc">
                <b-tabs
                  class="nav-tabs-custom"
                  content-class="border border-top-0 p-4"
                >
                  <b-tab title="日志内容">
                    <div>
                      <p v-html="allDatas.content"></p>
                    </div>
                  </b-tab>
                  <b-tab title="日志属性">
                    <div class="table-responsive">
                      <table class="table table-nowrap mb-0">
                        <tbody>
                          <tr>
                            <th scope="row" style="width: 400px">标题</th>
                            <td>{{ allDatas.title }}</td>
                          </tr>
                          <tr>
                            <th scope="row">时间</th>
                            <td>
                              {{ allDatas.year }}-{{ allDatas.month }}-{{
                                allDatas.day
                              }}
                            </td>
                          </tr>
                          <tr>
                            <th scope="row">创建人</th>
                            <td>{{ allDatas.userId }}</td>
                          </tr>
                          <tr>
                            <th scope="row">创建时间</th>
                            <td>{{ allDatas.publishDate }}</td>
                          </tr>
                        </tbody>
                      </table>
                    </div>
                  </b-tab>
                </b-tabs>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </Layout>
</template>

<script>
import Layout from "@/layout/index";
import PageHeader from "@/layout/common/page-header";
import { logfindId } from "@/http/api/log";
import { all } from "axios";

export default {
  components: { Layout, PageHeader },

  data() {
    return {
      title: "日志详情",
      items: [
        {
          text: "日志管理",
        },
        {
          text: "日志详情",
          active: true,
        },
      ],

      allDatas: null,
    };
  },
  mounted() {
    this.getLogById(this.$route.params.logId);
  },
  methods: {
    getLogById(logId) {
      console.log("当前日志id: ", logId);
      logfindId(logId).then((resp) => {
        if (resp.code == 200) {
          console.log("通过id查询成功", resp.data);
          this.allDatas = resp.data;
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped></style>
