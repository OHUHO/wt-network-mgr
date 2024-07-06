<template>
  <Layout>
    <PageHeader :title="title" :items="items" />
    <div class="row">
      <div class="col-lg-12">
        <div class="card">
          <div class="card-body">
            <div class="row" v-if="allDatas != null">
              <div class="col-md-8">
                <div class="product-detail">
                  <div>
                    {{this.allDatas.content}}
                  </div>
<!--                  <img src="@/assets/images/product/img1.png" />-->
<!--                  <div>
                    其中：Physical Address是你计算机的物理地址 IP
                    Address是你计算机的ip地址 Default
                    Gateway是你计算机的网关地址 DNS
                    Servers是你计算机的域名解析服务器地址
                    2、如果IP地址生效，你依然不能上网，请检测你的计算机和网关的连接情况。点击“开始”－>“运行”，在“运行”对话框内输入“ping你的网关地址/t“，类似如下图：
                  </div>-->
                </div>
                <!-- end product img -->
              </div>
              <div class="col-md-4">
                <div class="row">
                  <div class="col-md-12">
                    <a href="#" class="text-primary">{{this.allDatas.publishDate}}</a>
                    <h5 class="mt-1 mb-3">种类: {{this.allDatas.experienceKindId}}</h5>
                    <h5 class="mt-2 mb-3">标题: {{ this.allDatas.title }}</h5>
                    <h5 class="mt-3 md-3">设备名: {{this.allDatas.deviceName}}</h5>
                    <hr class="my-4" />
                  </div>
                </div>

                <div class="row">
                  <!-- <h5 class="font-size-14 mb-3">其他推荐:</h5> -->
                  <div class="product-desc">
                    <b-tabs
                      class="nav-tabs-custom"
                      content-class="border border-top-0 p-4"
                    >
                      <b-tab title="推荐">
                        <div class="table-responsive">
                          <table class="table table-nowrap mb-0">
                            <tbody>
                              <tr>
                                <th scope="row" style="width: 400px">
                                  端口故障原因分析
                                </th>
                                <td>jc</td>
                              </tr>
                              <tr>
                                <th scope="row">主机逻辑</th>
                                <td>ytt</td>
                              </tr>
                              <tr>
                                <th scope="row">主机有哪些故障原因</th>
                                <td>ll</td>
                              </tr>
                              <tr>
                                <th scope="row">端口故障原因分析</th>
                                <td>jc</td>
                              </tr>
                              <tr>
                                <th scope="row">主机逻辑</th>
                                <td>ytt</td>
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
      </div>
    </div>
  </Layout>
</template>

<script>
import Layout from "@/layout/index";
import PageHeader from "@/layout/common/page-header";
import {experienceById} from "@/http/api/experience";

export default {
  components: { Layout, PageHeader },

  data() {
    return {
      allDatas:null,
      title: "网络经验",
      items: [
        {
          text: "网络经验",
        },
        {
          text: "文章",
          active: true,
        },
      ],
    };
  },
  mounted() {
    this.allExperience(this.$route.params.experienceId)
    // console.log("huuuuuuuuuu",this.$route.params.experienceId)
  },


  methods: {
    allExperience(experienceId) {
      experienceById(experienceId).then((resp) => {
        if (resp.code == 200) {
          this.allDatas = resp.data
          console.log("通过id查询成功" , this.allDatas)
        }
      })
    },

    imageShow(event) {
      const image = event.target.src;
      const expandImg = document.getElementById("expandedImg");
      expandImg.src = image;
    },


  },
};
</script>

<style lang="scss" scoped></style>
