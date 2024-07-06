<template>
  <Layout>
    <PageHeader :title="title" :items="items" />

    <div class="row">
      <div class="col-12">
        <div class="card">
          <div class="card-body">
            <!-- <h4 class="card-title">数据表格</h4> -->
            <b-button @click="addLog" class="w-lg" variant="light" modal-center
              >添加经验</b-button
            >
            <div class="row mt-4">
              <div class="col-sm-12 col-md-6">
                <div id="tickets-table_length" class="dataTables_length">
                  <label class="d-inline-flex align-items-center">
                    Show&nbsp;
                    <b-form-select
                      v-model="perPage"
                      size="sm"
                      :options="pageOptions"
                    ></b-form-select
                    >&nbsp;entries
                  </label>
                </div>
              </div>
              <!-- Search -->
              <div class="col-sm-12 col-md-6">
                <div
                  id="tickets-table_filter"
                  class="dataTables_filter text-md-right"
                >
                  <label class="d-inline-flex align-items-center">
                    Search:
                    <b-form-input
                      v-model="filter"
                      type="search"
                      placeholder="搜索..."
                      class="form-control form-control-sm ml-2"
                    ></b-form-input>
                  </label>
                </div>
              </div>
              <!-- End search -->
            </div>
            <!-- Table -->
            <div class="table-responsive mb-0" @click="back">
              <b-table
                :items="tableData"
                :fields="fields"
                responsive="sm"
                :per-page="perPage"
                :current-page="currentPage"
                :sort-by.sync="sortBy"
                :sort-desc.sync="sortDesc"
                :filter="filter"
                :filter-included-fields="filterOn"
                @filtered="onFiltered"
              >
                <template #cell(actions)>
                  <a
                    href="javascript:void(0);"
                    class="mr-3 text-primary"
                    v-b-tooltip.hover
                    title="Edit"
                  >
                    <i class="mdi mdi-pencil font-size-18"></i>
                  </a>
                  <a
                    href="javascript:void(0);"
                    class="text-danger"
                    v-b-tooltip.hover
                    title="Delete"
                  >
                    <i class="mdi mdi-trash-can font-size-18"></i>
                  </a>
                </template>
              </b-table>
            </div>
            <div class="row">
              <div class="col">
                <div
                  class="dataTables_paginate paging_simple_numbers float-right"
                >
                  <ul class="pagination pagination-rounded mb-0">
                    <!-- pagination -->
                    <b-pagination
                      v-model="currentPage"
                      :total-rows="rows"
                      :per-page="perPage"
                    ></b-pagination>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

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
          <b-form-textarea
            id="textarea"
            v-model="content"
            placeholder="请输入内容..."
            rows="3"
            max-rows="6"
          ></b-form-textarea>
        </b-form-group>
        <b-form-group
          id="input-group-3"
          label="种类"
          label-cols-sm="1"
          label-cols-lg="1"
          label-for="input-3"
        >
          <b-form-select
            id="input-3"
            v-model="typeList[0]"
            :options="typeList"
            required
          ></b-form-select>
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
// import CKEditor from "@ckeditor/ckeditor5-vue";
import { tableData } from "./dataAdvancedtable";

export default {
  components: { Layout, PageHeader },
  data() {
    return {
      title: "网络经验",
      items: [
        {
          text: "吴彦组网络管理系统",
        },
        {
          text: "网络经验",
          active: true,
        },
      ],
      modalTitle: "",
      optionBtnText: "",
      editorData: "<p>请输入日志~~~~</p>",
      tableData: tableData,
      totalRows: 1,
      currentPage: 1,
      perPage: 10,
      pageOptions: [10, 25, 50, 100],
      filter: null,
      filterOn: [],
      sortBy: "age",
      sortDesc: false,
      fields: [
        { key: "deviceName", label: "设备名", sortable: true },
        { key: "Kind", label: "种类", sortable: true },
        { key: "deviceArea", label: "设备区域", sortable: true },
        { key: "title", label: "标题", sortable: true },
        { key: "date", label: "日期", sortable: true },
        { key: "actions", label: "操作" },
      ],
      typeList: ["端口", "线路故障", "主机故障", "软件问题"],
      content: "",
    };
  },

  computed: {
    rows() {
      return this.tableData.length;
    },
  },
  mounted() {
    this.totalRows = this.items.length;
  },
  methods: {
    onFiltered(filteredItems) {
      this.totalRows = filteredItems.length;
      this.currentPage = 1;
    },
    back(experienceId) {
      this.$router.push({
        name: "content",
        params: { experienceId: experienceId },
      });
    },
    addLog() {
      this.$refs["modal-add"].show();
      this.modalTitle = "添加文章内容";
      this.optionBtnText = "添加";
    },
    hideModal() {
      this.$refs["modal-add"].hide();
      this.$refs["modal-delete"].hide();
    },
  },
};
</script>
<style lang="scss" scoped></style>
