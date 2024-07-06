<template>
  <Layout>
    <PageHeader :title="title" :items="items" />
    <div class="row">
      <div class="col-lg-12">
        <div class="card">
          <div class="card-body">
            <div class="row">
              <div class="col-md-8">
                <div class="product-detail">
                  <div>
                    一、看线路是否正常：
                    用户自己查看门外端口和水晶头是否接触正常，用户家中的线路中间是否有损坏(特别中间有直接头的用户，请检查接触是否正常)，计算机网卡端口和水晶头是否接触正常。在线路有问题时，计算机的“网络连接”将会出现“叉号”，
                    二、看网卡是否工作正常：
                    网卡正常工作时，网卡灯会闪烁。如果没有信号，请检查线路。
                    三、置IP地址：
                    如果“网络连接”状态正常，如下图，并且用户依然不能上网，那么可能就是没有配置IP地址，IP地址的配置方法请用户参照贵州师范大学网络与信息中心主页《上网指南》
                    四、网络测试步骤：
                    1、IP地址设置是否生效。点击“开始”→“运行”，在“运行”对话框内输入cmd或者command(win98使用)。Dos界面打开后，直接输入“ipconfig/all”，如果输出类似如下图：则你的地址设置生效。(数字可能不相同)。
                  </div>
                  <img src="@/assets/images/product/img1.png" />
                  <div>
                    其中：Physical Address是你计算机的物理地址 IP
                    Address是你计算机的ip地址 Default
                    Gateway是你计算机的网关地址 DNS
                    Servers是你计算机的域名解析服务器地址
                    2、如果IP地址生效，你依然不能上网，请检测你的计算机和网关的连接情况。点击“开始”－>“运行”，在“运行”对话框内输入“ping你的网关地址/t“，类似如下图：
                  </div>
                </div>
                <!-- end product img -->
              </div>
              <div class="col-md-4">
                <div class="row">
                  <div class="col-md-12">
                    <a href="#" class="text-primary">2018.07.19</a>
                    <h5 class="mt-1 mb-3">种类: 线路故障</h5>
                    <h5 class="mt-2 mb-3">标题: 线路故障分析原因</h5>
                    <h5 class="mt-3 md-3">设备名: tjx</h5>
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

export default {
  components: { Layout, PageHeader },

  data() {
    return {
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
  methods: {
    imageShow(event) {
      const image = event.target.src;
      const expandImg = document.getElementById("expandedImg");
      expandImg.src = image;
    },
  },
};
</script>

<style lang="scss" scoped></style>
