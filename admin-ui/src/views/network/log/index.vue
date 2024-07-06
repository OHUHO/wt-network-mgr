<template>
  <Layout>
    <PageHeader :title="title" :items="items" />

    <div class="row">
      <div class="col-lg-12">
        <div class="card">
          <div class="card-body">
            <div class="row">
              <div class="col-md-3">
                <b-button
                  @click="addLog"
                  class="w-lg"
                  variant="light"
                  modal-center
                  >添加日志</b-button
                >
              </div>

              <div class="col-md-9">
                <ul class="list-inline user-chat-nav text-right mb-0">
                  <li class="list-inline-item d-inline-block d-sm-none">
                    <div class="dropdown">
                      <button
                        class="btn nav-btn dropdown-toggle"
                        type="button"
                        data-toggle="dropdown"
                        aria-haspopup="true"
                        aria-expanded="false"
                      >
                        <i class="mdi mdi-magnify"></i>
                      </button>
                      <div
                        class="dropdown-menu dropdown-menu-right dropdown-menu-md"
                      >
                        <form class="p-2">
                          <div class="search-box">
                            <div class="position-relative">
                              <input
                                type="text"
                                class="form-control rounded"
                                placeholder="Search..."
                              />
                              <i class="mdi mdi-magnify search-icon"></i>
                            </div>
                          </div>
                        </form>
                      </div>
                    </div>
                  </li>

                  <li class="d-none d-sm-inline-block">
                    <div class="search-box mr-2">
                      <div class="position-relative">
                        <input
                          id="filter-input"
                          v-model="keywords"
                          @keyup.enter="handleEnter"
                          type="search"
                          class="form-control"
                          placeholder="输入关键词..."
                        />
                        <i class="mdi mdi-magnify search-icon"></i>
                      </div>
                    </div>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-xl-3 col-sm-6" v-for="(item, index) in list" :key="index">
        <div class="card">
          <div class="card-body">
            <b-dropdown
              right
              toggle-class="arrow-none card-drop"
              class="float-right"
              variant="white"
            >
              <template v-slot:button-content>
                <i
                  class="mdi mdi-dots-horizontal"
                  style="margin-right: -12px;"
                ></i>
              </template>
              <b-dropdown-item @click="editLog(item)">编辑</b-dropdown-item>
              <b-dropdown-item @click="deleteLog(item)">删除</b-dropdown-item>
            </b-dropdown>
            <h4 class="card-title mb-3">✨</h4>

            <div class="text-center">
              <!-- <h6>{{ item.title }}</h6> -->
              <router-link
                :to="{ name: 'LogDetails', params: { logId: item.id } }"
                >{{ item.title }}</router-link
              >
            </div>
            <hr class="my-3" />
            <div class="row text-center">
              <div class="col-6">
                <p class="text-muted mb-2">{{ item.userId }}</p>
                <!-- <h5>{{ data.products }}</h5> -->
              </div>
              <div class="col-6">
                <p class="text-muted mb-2">
                  {{ item.year + "-" + item.month + "-" + item.day }}
                </p>
                <!-- <h5>{{ data.balance }}</h5> -->
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 删除 弹窗-->
    <b-modal ref="modal-delete" title="确认删除？">
      <h6 v-if="rawData != null">{{ rawData.title }}</h6>
      <template v-slot:modal-footer>
        <b-button variant="secondary" @click="hideModal">取消</b-button>
        <b-button variant="primary" @click="hideModalDelete">
          确定
          <i class="fab fa-telegram-plane ml-1"></i>
        </b-button>
      </template>
    </b-modal>

    <!-- 添加 编辑  弹窗-->
    <b-modal
      ref="modal-add"
      size="xl"
      :title="modalTitle"
      title-class="font-18"
    >
      <form>
        <b-form-group
          id="example text"
          label-cols-sm="1"
          label-cols-lg="1"
          label="标题"
          label-for="text"
        >
          <b-form-input
            for="text"
            value=" "
            placeholder="请输入标题"
            v-model="tanchuangData.title"
          ></b-form-input>
        </b-form-group>
        <b-form-group
          id="example text"
          label-cols-sm="1"
          label-cols-lg="1"
          label="年份"
          label-for="text"
        >
          <b-form-input
            id="date"
            value="2023-03-29"
            type="date"
            v-model="tanchuangData.ctime"
          ></b-form-input>
        </b-form-group>
        <b-form-group
          id="example text"
          label-cols-sm="1"
          label-cols-lg="1"
          label="内容"
          label-for="text"
          v-model="tanchuangData.content"
        >
          <ckeditor v-model="tanchuangData.content" :editor="editor"></ckeditor>
        </b-form-group>
      </form>
      <template v-slot:modal-footer>
        <b-button variant="secondary" @click="hideModal">取消</b-button>
        <b-button variant="primary" @click="hideModaladd">
          {{ optionBtnText }}
          <i class="fab fa-telegram-plane ml-1"></i>
        </b-button>
      </template>
    </b-modal>
  </Layout>
</template>

<script>
import Layout from "@/layout/index";
import PageHeader from "@/layout/common/page-header";
import CKEditor from "@ckeditor/ckeditor5-vue";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import {
  logadd,
  logchange,
  logdelete,
  logfindAll,
  logfindkeywords,
} from "@/http/api/log";

export default {
  components: {
    Layout,
    PageHeader,
    ckeditor: CKEditor.component,
  },

  data() {
    return {
      title: "日志管理",
      items: [
        {
          text: "网络和日志",
        },
        {
          text: "日志管理",
          active: true,
        },
      ],
      modalTitle: "",
      optionBtnText: "",
      editor: ClassicEditor,
      filter: null,
      filterOn: [],

      tanchuangData: {
        id: 0,
        userId: 0,
        ctime: "",
        title: "",
        content: "<p>请输入日志~~~~</p>",
        publishDate: "",
        isDelete: 0,
        // content:"",
      },

      list: [],
      rawData: null,
      rawData02: null,
      keywords: "",
    };
  },
  mounted() {
    this.displayAllDatas();
  },

  computed: {
    sortOptions() {
      // Create an options list from our fields
      return this.fields
        .filter((f) => f.sortable)
        .map((f) => {
          return { text: f.label, value: f.key };
        });
    },
  },

  methods: {
    handleEnter() {
      console.log("按下回车，执行操作");
      const keywords = this.keywords;
      logfindkeywords(keywords).then((resp) => {
        if (resp.code == 200) {
          console.log("查询成功");
          this.list = resp.data;
        }
      });
    },

    //页面渲染时将数据库里面的数据展示出来
    displayAllDatas() {
      logfindAll().then((resp) => {
        console.log(resp);
        if (resp.code == 200) {
          this.list = resp.data;
          console.log("查询所有成功");
        }
      });
    },

    // eslint-disable-next-line vue/no-dupe-keys
    addLog() {
      this.$refs["modal-add"].show();
      this.modalTitle = "添加日志";
      this.optionBtnText = "添加";
    },
    hideModal() {
      this.$refs["modal-add"].hide();
      this.$refs["modal-delete"].hide();
    },

    hideModalDelete() {
      const id = this.rawData.id;
      console.log(id);
      logdelete(id).then((resp) => {
        if (resp.code == 200) {
          console.log("删除成功");
          this.displayAllDatas();
        }
      });
      this.$refs["modal-add"].hide();
      this.$refs["modal-delete"].hide();
    },

    //添加日志
    hideModaladd() {
      console.log(this.optionBtnText);
      if (this.optionBtnText == "添加") {
        this.$refs["modal-add"].hide();
        this.$refs["modal-delete"].hide();

        const userId = JSON.parse(window.localStorage.getItem("user")).id;
        const year = this.tanchuangData.ctime.substr(0, 4);
        const month = this.tanchuangData.ctime.substr(5, 2);
        const day = this.tanchuangData.ctime.substr(8, 2);
        //添加一条数据，调用后端接口
        const data = {
          // id: 0,
          userId: userId,
          year: year,
          month: month,
          day: day,
          title: this.tanchuangData.title,
          content: this.tanchuangData.content,
        };
        console.log("data=======", data);
        console.log("ctime------------", this.tanchuangData.ctime);
        //调用后端接口添加
        logadd(data).then((resp) => {
          console.log(resp);
          if (resp.code == 200) {
            console.log("添加成功");

            this.displayAllDatas();
          }
        });
      } else {
        /*       const year = this.rawData02.ctime.substr(0, 4)
        const month = this.rawData02.ctime.substr(5, 2)
        const day = this.rawData02.ctime.substr(8, 2)*/
        const log = {
          id: this.tanchuangData.id,
          userId: this.tanchuangData.userId,
          year: this.tanchuangData.year,
          month: this.tanchuangData.month,
          day: this.tanchuangData.day,
          title: this.tanchuangData.title,
          content: this.tanchuangData.content,
          publishDate: this.tanchuangData.publishDate,
          isDelete: this.tanchuangData.isDelete,
        };
        console.log("修改数据", log);
        //修改日志调用后端接口
        logchange(log).then((resp) => {
          if (resp.code == 200) {
            console.log(resp.data);
            console.log("更新成功");
            this.displayAllDatas();
          }
        });
        this.$refs["modal-add"].hide();
        this.$refs["modal-delete"].hide();
        // this.tanchuangData = null
      }

      this.tanchuangData = {
        id: 0,
        userId: 0,
        ctime: "",
        title: "",
        content: "<p>请输入日志~~~~</p>",
        publishDate: "",
        isDelete: 0,
      };
    },

    editLog(rawDatas) {
      var year = rawDatas.year;
      var month = rawDatas.month;
      var day = rawDatas.day;
      rawDatas.ctime = year + "-" + month + "-" + day;
      this.tanchuangData = rawDatas;
      console.log("需要编辑的数据", rawDatas);
      this.$refs["modal-add"].show();
      this.modalTitle = "编辑日志";
      this.optionBtnText = "保存";
    },

    deleteLog(rawDatas) {
      this.rawData = rawDatas;
      this.$refs["modal-delete"].show();
      console.log(this.rawData);
    },
    onFiltered(filteredItems) {
      // Trigger pagination to update the number of buttons/pages due to filtering
      this.totalRows = filteredItems.length;
      this.currentPage = 1;
    },
  },
};
</script>

<style lang="scss" scoped></style>
