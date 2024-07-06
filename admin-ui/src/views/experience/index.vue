<template>
  <Layout>
    <PageHeader :title="title" :items="items" />

    <div class="row">
      <div class="col-12">
        <div class="card">
          <div class="card-body">
            <!-- <h4 class="card-title">数据表格</h4> -->
            <div
              :style="{
                'pointer-events': currentRole == 'CHASER' ? 'none' : '',
              }"
            >
              <!-- :disabled="currentRole == 'CHASER'" -->
              <b-button
                @click="addLog"
                class="w-lg"
                variant="light"
                modal-center
                >添加经验
              </b-button>
            </div>

            <div class="row mt-4">
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
              </div>
            </div>
            <!-- pointer-events: none; -->
            <div
              class="table-responsive mb-0"
              :style="{
                'pointer-events': currentRole == 'CHASER' ? 'none' : '',
              }"
            >
              <b-table
                :items="tableData"
                :fields="fields"
                responsive="sm"
                :current-page="currentPage"
                :per-page="perPage"
                :filter="filter"
                :filter-included-fields="filterOn"
                :sort-by.sync="sortBy"
                :sort-desc.sync="sortDesc"
                ref="selectableTable"
                :select-mode="selectMode"
                selectable
                @row-selected="back"
                @filtered="onFiltered"
              >
                <template #cell(actions)="raw">
                  <a
                    href="javascript:void(0);"
                    class="mr-3 text-primary"
                    v-b-tooltip.hover
                    title="Edit"
                    @click="editExperience(raw.item)"
                  >
                    <i class="mdi mdi-pencil font-size-18"></i>
                  </a>
                  <a
                    href="javascript:void(0);"
                    class="text-danger"
                    v-b-tooltip.hover
                    title="Delete"
                    @click="deleteExperienceId(raw.item)"
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
                    <b-pagination
                      v-model="currentPage"
                      :total-rows="totalRows"
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

    <!-- 删除 弹窗-->
    <b-modal ref="modal-delete" title="确认删除？">
      <h6 v-if="rawAlls != null">{{ rawAlls.title }}</h6>
      <template v-slot:modal-footer>
        <b-button variant="secondary" @click="hideModal">取消</b-button>
        <b-button variant="primary" @click="hideModalDelete">
          确定
          <i class="fab fa-telegram-plane ml-1"></i>
        </b-button>
      </template>
    </b-modal>

    <!-- 添加 编辑 弹窗-->
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
            v-model="tanchuangData.title"
            :class="{
              'is-invalid': submitted && $v.tanchuangData.title.$error,
            }"
          ></b-form-input>
          <div
            v-if="submitted && tanchuangData.title.required"
            class="invalid-feedback"
          >
            标题不能为空
          </div>
        </b-form-group>

        <b-form-group
          id="example text"
          label-cols-sm="1"
          label-cols-lg="1"
          label="设备名"
          label-for="text"
        >
          <b-form-input
            for="text"
            value=""
            placeholder="输入设备名"
            v-model="tanchuangData.deviceName"
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="example text"
          label-cols-sm="1"
          label-cols-lg="1"
          label="日期"
          label-for="text"
        >
          <b-form-input
            id="date"
            value="2023-03-29"
            type="date"
            v-model="tanchuangData.publishDate"
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="example text"
          label-cols-sm="1"
          label-cols-lg="1"
          label="设备区域"
          label-for="text"
        >
          <b-form-input
            for="text"
            value=""
            placeholder="输入设备区域"
            v-model="tanchuangData.deviceArea"
          ></b-form-input>
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
            v-model="tanchuangData.content"
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
            v-model="tanchuangData.experienceKindId"
            :options="typeList"
            required
          ></b-form-select>
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
// import CKEditor from "@ckeditor/ckeditor5-vue";

import {
  experienceadd,
  experiencechange,
  experiencedelet,
  experiencefindall,
} from "@/http/api/experience";
import { required } from "vuelidate/lib/validators";

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
      submitted: false,
      validations: {
        tanchuangData: {
          experienceKindId: { required },
          deviceName: { required },
          deviceArea: { required },
          testSign: { required },
          title: { required },
          content: { required },
        },
      },
      modalTitle: "",
      optionBtnText: "",
      editorData: "<p>请输入~~~~</p>",
      tableData: [],
      totalRows: 1,
      currentPage: 1,
      perPage: 10,
      pageOptions: [10, 25, 50, 100],
      filter: null,
      filterOn: [],
      sortBy: "",
      sortDesc: false,
      rawDatas: null,
      fields: [
        { key: "deviceName", label: "设备名", sortable: true },
        { key: "experienceKindId", label: "种类", sortable: true },
        { key: "deviceArea", label: "设备区域", sortable: true },
        { key: "title", label: "标题", sortable: true },
        { key: "publishDate", label: "日期", sortable: true },
        { key: "actions", label: "操作" },
      ],
      // typeList: ["端口", "线路故障", "主机故障", "软件问题"],
      typeList: [
        { value: 1, text: "线路故障" },
        { value: 2, text: "端口故障" },
        { value: 3, text: "主机逻辑故障" },
        { value: 4, text: "主机故障" },
        { value: 5, text: "软件问题" },
      ],
      content: "",
      rawAlls: null,

      tanchuangData: {
        id: 0,
        experienceKindId: 0,
        deviceName: "",
        deviceArea: "",
        testSign: "",
        title: "",
        content: "",
        publishDate: "",
        isDelete: 0,
      },

      selectMode: "single",
      selected: [],

      currentRole: "",
    };
  },

  computed: {},
  mounted() {
    // this.totalRows = this.items.length;
    this.displayData();
    this.currentRole = JSON.parse(window.localStorage.getItem("user")).role;
  },
  methods: {
    hideModaladd() {
      if (this.optionBtnText == "添加") {
        this.$refs["modal-add"].hide();
        this.$refs["modal-delete"].hide();

        //添加一条数据，调用后端接口
        const data = {
          id: this.tanchuangData.id,
          title: this.tanchuangData.title,
          experienceKindId: this.tanchuangData.experienceKindId,
          deviceName: this.tanchuangData.deviceName,
          deviceArea: this.tanchuangData.deviceArea,
          testSign: this.tanchuangData.testSign,
          content: this.tanchuangData.content,
          publishDate: this.tanchuangData.publishDate,
          isDelete: this.tanchuangData.isDelete,
        };
        console.log("data=====", data);
        experienceadd(data).then((resp) => {
          console.log(resp.data);
          if (resp.code == 200) {
            console.log("添加成功");
            this.displayData();
          }
        });
      } else {
        const Experience = {
          id: this.tanchuangData.id,
          title: this.tanchuangData.title,
          experienceKindId: this.tanchuangData.experienceKindId,
          deviceName: this.tanchuangData.deviceName,
          deviceArea: this.tanchuangData.deviceArea,
          testSign: this.tanchuangData.testSign,
          content: this.tanchuangData.content,
          publishDate: this.tanchuangData.publishDate,
          isDelete: this.tanchuangData.isDelete,
        };
        console.log("需要修改的数据有", Experience);
        experiencechange(Experience).then((resp) => {
          if (resp.code == 200) {
            console.log("更新成功");
            this.displayData();
          }
        });
        this.$refs["modal-add"].hide();
        this.$refs["modal-delete"].hide();
      }

      this.tanchuangData = {
        id: 0,
        experienceKindId: 0,
        deviceName: "",
        deviceArea: "",
        testSign: "",
        title: "",
        content: "",
        publishDate: "",
        isDelete: 0,
      };
    },

    editExperience(rawdatas) {
      this.tanchuangData = rawdatas;
      this.$refs["modal-add"].show();
      this.modalTitle = "编辑网络经验";
      this.optionBtnText = "保存";
      console.log("需要编辑的该行数据", rawdatas);
    },

    hideModalDelete() {
      const id = this.rawAlls.id;
      console.log(id);
      experiencedelet(id).then((resp) => {
        if (resp.code == 200) {
          console.log("删除成功");
          this.displayData();
        }
      });
      console.log(this.rawAlls);
      this.$refs["modal-add"].hide();
      this.$refs["modal-delete"].hide();
    },

    deleteExperienceId(raws) {
      this.$refs["modal-delete"].show();
      this.rawAlls = raws;
      console.log(this.rawAlls);
    },

    onFiltered(filteredItems) {
      this.totalRows = filteredItems.length;
      this.currentPage = 1;
    },
    back(items) {
      this.selected = items;
      var experienceId = items[0].id;
      console.log("this is id", experienceId);
      this.$router.push({
        name: "content",
        params: { experienceId: experienceId },
      });
    },
    addLog() {
      this.$refs["modal-add"].show();
      this.modalTitle = "添加网络经验";
      this.optionBtnText = "添加";
    },
    hideModal() {
      if (this.optionBtnText != "添加") {
        this.tanchuangData = {
          id: 0,
          experienceKindId: 0,
          deviceName: "",
          deviceArea: "",
          testSign: "",
          title: "",
          content: "",
          publishDate: "",
          isDelete: 0,
        };
      }
      this.$refs["modal-add"].hide();
      this.$refs["modal-delete"].hide();
    },

    displayData() {
      //页面渲染时调用查询所有数据的方法
      experiencefindall().then((resp) => {
        console.log(resp);
        if (resp.code == 200) {
          this.tableData = resp.data;
          this.totalRows = resp.data.length;
          console.log("从数据库获取到数据", resp.data);
        }
      });
    },
  },
};
</script>
<style lang="scss" scoped></style>
