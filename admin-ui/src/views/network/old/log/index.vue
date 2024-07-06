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
                          type="text"
                          class="form-control"
                          placeholder="搜索..."
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
              <b-dropdown-item @click="editLog">编辑</b-dropdown-item>
              <b-dropdown-item @click="deleteLog">删除</b-dropdown-item>
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
                <p class="text-muted mb-2">{{ item.name }}</p>
                <!-- <h5>{{ data.products }}</h5> -->
              </div>
              <div class="col-6">
                <p class="text-muted mb-2">{{ item.createTime }}</p>
                <!-- <h5>{{ data.balance }}</h5> -->
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 删除 -->
    <b-modal ref="modal-delete" title="确认删除？">
      <h6>标题标题标题标题</h6>
      <template v-slot:modal-footer>
        <b-button variant="secondary" @click="hideModal">取消</b-button>
        <b-button variant="primary" @click="hideModal">
          确定
          <i class="fab fa-telegram-plane ml-1"></i>
        </b-button>
      </template>
    </b-modal>

    <!-- 添加 编辑 -->
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
            value=""
            placeholder="输入标题"
          ></b-form-input>
        </b-form-group>
        <b-form-group
          id="example text"
          label-cols-sm="1"
          label-cols-lg="1"
          label="年份"
          label-for="text"
        >
          <b-form-input id="date" value="2023-03-29" type="date"></b-form-input>
        </b-form-group>
        <b-form-group
          id="example text"
          label-cols-sm="1"
          label-cols-lg="1"
          label="内容"
          label-for="text"
        >
          <ckeditor v-model="editorData" :editor="editor"></ckeditor>
        </b-form-group>
      </form>
      <template v-slot:modal-footer>
        <b-button variant="secondary" @click="hideModal">取消</b-button>
        <b-button variant="primary" @click="hideModal">
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
      editorData: "<p>请输入日志~~~~</p>",
      list: [
        {
          id: 1,
          title: "简单的描述内容",
          name: "管理员0417",
          createTime: "2023-03-29",
        },
        {
          id: 2,
          title: "网络维护和优化日志管理的描述",
          name: "管理员001",
          createTime: "2023-03-29",
        },
        {
          id: 3,
          title: "这是一段描述内容",
          name: "管理员0012",
          createTime: "2023-03-29",
        },
        {
          id: 4,
          title: "这是一段网络日志维护的描述",
          name: "优化部门主管",
          createTime: "2023-03-29",
        },
        {
          id: 5,
          title: "这是一段网络日志维护的描述",
          name: "维护一线人员",
          createTime: "2023-03-29",
        },
        {
          id: 6,
          title: "这是一段网络日志维护的描述",
          name: "未知",
          createTime: "2023-03-29",
        },
        {
          id: 7,
          title: "这是一段网络日志维护的描述",
          name: "维护一线人员",
          createTime: "2023-03-29",
        },
        {
          id: 8,
          title: "这是一段网络日志维护的描述",
          name: "未知",
          createTime: "2023-03-29",
        },
        {
          id: 9,
          title: "这是一段网络日志维护的描述",
          name: "网络一线人员",
          createTime: "2023-03-29",
        },
        {
          id: 10,
          title: "这是一段网络日志维护的描述",
          name: "未知",
          createTime: "2023-03-29",
        },
        {
          id: 11,
          title: "这是一段网络日志维护的描述",
          name: "一线人员",
          createTime: "2023-03-29",
        },
        {
          id: 12,
          title: "这是一段网络日志维护的描述",
          name: "未知",
          createTime: "2023-03-29",
        },
        {
          id: 13,
          title: "这是一段网络日志维护的描述",
          name: "一线人员",
          createTime: "2023-03-29",
        },
        {
          id: 14,
          title: "这是一段网络日志维护的描述",
          name: "未知",
          createTime: "2023-03-29",
        },
        {
          id: 15,
          title: "这是一段网络日志维护的描述",
          name: "未知",
          createTime: "2023-03-29",
        },
        {
          id: 16,
          title: "这是一段网络日志维护的描述",
          name: "一线人员",
          createTime: "2023-03-29",
        },
        {
          id: 17,
          title: "这是一段网络日志维护的描述",
          name: "未知",
          createTime: "2023-03-29",
        },
        {
          id: 18,
          title: "这是一段网络日志维护的描述",
          name: "未知",
          createTime: "2023-03-29",
        },
        {
          id: 19,
          title: "这是一段网络日志维护的描述",
          name: "一线人员",
          createTime: "2023-03-29",
        },
      ],
    };
  },
  methods: {
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
    editLog() {
      this.$refs["modal-add"].show();
      this.modalTitle = "编辑日志";
      this.optionBtnText = "保存";
    },
    deleteLog() {
      this.$refs["modal-delete"].show();
    },
  },
};
</script>

<style lang="scss" scoped></style>
