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
                  @click="addSchedule"
                  class="w-lg"
                  variant="light"
                  modal-center
                  >添加日程</b-button
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
                        <b-input-group class="p-2">
                          <div class="search-box">
                            <div class="position-relative">
                              <b-form-input
                                id="filter-input"
                                v-model="filter"
                                type="search"
                                class="form-control rounded"
                                placeholder="Search..."
                              ></b-form-input>
                              <i class="mdi mdi-magnify search-icon"></i>
                            </div>
                          </div>
                        </b-input-group>
                      </div>
                    </div>
                  </li>
                  <li class="d-none d-sm-inline-block">
                    <div class="search-box mr-2">
                      <div class="position-relative">
                        <input
                          id="filter-input"
                          v-model="filter"
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
      <div class="col-lg-12">
        <div class="card">
          <div class="card-body">
            <div class="row">
              <b-container fluid>
                <b-table
                  :items="tableData"
                  :fields="fields"
                  :current-page="currentPage"
                  :per-page="perPage"
                  :filter="filter"
                  :filter-included-fields="filterOn"
                  :sort-by.sync="sortBy"
                  :sort-desc.sync="sortDesc"
                  :sort-direction="sortDirection"
                  empty-filtered-text="没有匹配的结果"
                  stacked="md"
                  hover
                  show-empty
                  @filtered="onFiltered"
                >
                  <template #cell(look)="row">
                    <b-button
                      size="sm"
                      @click="row.toggleDetails"
                      variant="outline-primary"
                    >
                      {{ row.detailsShowing ? "折 叠" : "展 开" }}
                    </b-button>
                  </template>

                  <template #cell(actions)="row">
                    <b-button
                      size="sm"
                      @click="editSchedule(row.item.id)"
                      class="mr-1"
                      variant="outline-success"
                    >
                      编 辑
                    </b-button>

                    <b-button
                      size="sm"
                      @click="deleteSchedule"
                      class="mr-1"
                      variant="outline-danger"
                    >
                      删 除
                    </b-button>
                  </template>

                  <template #row-details="row">
                    <b-card>
                      <b-alert show
                        ><strong>标题：</strong>{{ row.item.title }}</b-alert
                      >
                      <b-alert variant="success" show
                        ><strong>内容：</strong>{{ row.item.content }}</b-alert
                      >
                    </b-card>
                  </template>
                </b-table>

                <div class="row">
                  <div class="col-md-3">
                    <b-form-group
                      label="记录数/页"
                      label-for="per-page-select"
                      label-cols-lg="3"
                      label-align-sm="left"
                    >
                      <b-form-select
                        id="per-page-select"
                        v-model="perPage"
                        :options="pageOptions"
                      ></b-form-select>
                    </b-form-group>
                  </div>
                  <div class="col-md-9 d-flex justify-content-end">
                    <b-pagination
                      v-model="currentPage"
                      :total-rows="totalRows"
                      :per-page="perPage"
                    ></b-pagination>
                  </div>
                </div>
              </b-container>
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
      title: "网络维护和优化日程管理",
      items: [
        {
          text: "吴彦组网络管理系统",
        },
        {
          text: "网络维护和优化日程",
          active: true,
        },
      ],
      modalTitle: "",
      optionBtnText: "",
      editor: ClassicEditor,
      editorData: "<p>请输入日程~~~~</p>",

      tableData: [
        {
          id: 1,
          year: "2022",
          mounth: "02",
          day: "12",
          title: "标题1",
          content:
            "内容1内容1内容1内容1内容1内容1内容1内容容1内容1内容1内容容1内容1内容1内容容1内容1内容1内容",
          publishDate: "2023-02-01",
        },
        {
          id: 2,
          year: "2023",
          mounth: "04",
          day: "14",
          title: "标题2",
          content: "内容2",
          publishDate: "2023-06-01",
        },
        {
          id: 3,
          year: "2023",
          mounth: "03",
          day: "15",
          title: "标题3",
          content: "内容3",
          publishDate: "2024-06-01",
        },
        {
          id: 4,
          year: "2024",
          mounth: "01",
          day: "16",
          title: "标题4",
          content: "内容4",
          publishDate: "2013-06-01",
        },
        {
          id: 5,
          year: "2033",
          mounth: "12",
          day: "24",
          title: "标题5",
          content: "内容5",
          publishDate: "2023-11-21",
        },
        {
          id: 6,
          year: "2023",
          mounth: "05",
          day: "23",
          title: "标题6",
          content: "内容6",
          publishDate: "2053-09-21",
        },
        {
          id: 7,
          year: "2020",
          mounth: "03",
          day: "19",
          title: "标题7",
          content: "内容7",
          publishDate: "2023-10-21",
        },
      ],

      ttttttemp: {
        _rowVariant: "success",
        _cellVariants: { age: "danger", isActive: "warning" },
      },
      fields: [
        { key: "id", label: "ID", sortable: true, sortDirection: "desc" },
        { key: "year", label: "年", sortable: true, sortDirection: "desc" },
        { key: "mounth", label: "月", sortable: true, sortDirection: "desc" },
        { key: "day", label: "日", sortable: true, sortDirection: "desc" },
        { key: "title", label: "标题", sortable: true, sortDirection: "desc" },

        {
          key: "publishDate",
          label: "创建时间",
          sortable: true,
          sortDirection: "desc",
        },
        { key: "look", label: "查看" },
        { key: "actions", label: "操作" },
      ],
      totalRows: 1,
      currentPage: 1,
      perPage: 5,
      pageOptions: [5, 10, 15, { value: 100, text: "全部记录" }],
      sortBy: "",
      sortDesc: false,
      sortDirection: "asc",
      filter: null,
      filterOn: [],
      infoModal: {
        id: "info-modal",
        title: "",
        content: "",
      },
    };
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
  mounted() {
    // Set the initial number of items
    this.totalRows = this.tableData.length;
  },
  methods: {
    info(item, index, button) {
      this.infoModal.title = `Row index: ${index}`;
      this.infoModal.content = JSON.stringify(item, null, 2);
      this.$root.$emit("bv::show::modal", this.infoModal.id, button);
    },
    resetInfoModal() {
      this.infoModal.title = "";
      this.infoModal.content = "";
    },
    onFiltered(filteredItems) {
      // Trigger pagination to update the number of buttons/pages due to filtering
      this.totalRows = filteredItems.length;
      this.currentPage = 1;
    },
    // 编辑 添加
    addSchedule() {
      this.$refs["modal-add"].show();
      this.modalTitle = "添加日程";
      this.optionBtnText = "添加";
    },

    editSchedule(id) {
      this.$refs["modal-add"].show();
      this.modalTitle = "编辑日程";
      this.optionBtnText = "保存";

      console.log("id==========", id);
    },
    // 删除
    deleteSchedule() {
      this.$refs["modal-delete"].show();
    },
    hideModal() {
      this.$refs["modal-add"].hide();
      this.$refs["modal-delete"].hide();
    },
  },
};
</script>

<style lang="scss" scoped></style>
