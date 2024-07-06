<template>
  <div>
    <div class="row">
      <div class="col-md-9">
        <b-button v-b-modal.modal-prevent-closing @click="addNotice"
          >发 布
        </b-button>
      </div>

      <div class="=col-md-3">
        <div class="search-box mr-2">
          <div class="position-relative">
            <input
              id="filter-input"
              v-model="keywords"
              @keyup.enter="getNotices"
              type="search"
              class="form-control"
              placeholder="输入关键词..."
            />
            <i class="mdi mdi-magnify search-icon"></i>
          </div>
        </div>
      </div>
    </div>
    <b-table :items="items" :fields="fields" striped responsive="sm">
      <template #cell(actions)="row">
        <b-button
          size="sm"
          @click="editNotice(row.item)"
          class="mr-1"
          variant="outline-success"
        >
          编 辑
        </b-button>
        <b-button
          size="sm"
          @click="deleteNoticeBtn(row.item.id)"
          class="mr-1"
          variant="outline-danger"
        >
          删 除
        </b-button>
      </template>
    </b-table>
    <b-modal
      ref="modal-add-edit"
      size="xl"
      :title="modalTitle"
      title-class="font-18"
    >
      <form>
        <b-form-group
          id="content"
          label-cols-sm="1"
          label-cols-lg="1"
          label="内容"
          label-for="text"
        >
          <b-form-input
            for="text"
            v-model="notice.content"
            placeholder="输入内容"
          ></b-form-input>
        </b-form-group>
        <!-- <b-form-group
          id="publishDate"
          label-cols-sm="1"
          label-cols-lg="1"
          label="发布时间"
          label-for="text"
        >
          <b-form-input
            id="date"
            v-model="notice.publishDate"
            type="date"
          ></b-form-input>
        </b-form-group>
        <b-form-group
          id="noticeUser"
          label-cols-sm="1"
          label-cols-lg="1"
          label="发布者"
          label-for="text"
        >
          <b-form-input
            for="text"
            v-model="notice.userId"
            placeholder="输入用户名"
          ></b-form-input>
        </b-form-group> -->
      </form>
      <template v-slot:modal-footer>
        <b-button variant="secondary" @click="hideModal">取消</b-button>
        <b-button variant="primary" @click="addEditNotice">
          {{ optionBtnText }}
          <i class="fab fa-telegram-plane ml-1"></i>
        </b-button>
      </template>
    </b-modal>
    <b-modal ref="modal-delete" title="确认删除？">
      <h6>删除</h6>
      <template v-slot:modal-footer>
        <b-button variant="secondary" @click="hideDeleteModal">取消</b-button>
        <b-button variant="primary" @click="deleteNotice">
          确定
          <i class="fab fa-telegram-plane ml-1"></i>
        </b-button>
      </template>
    </b-modal>
  </div>
</template>

<script>
import { getAllNotice } from "../../../http/api/notice";
import {
  addNotices,
  findNotices,
  updateNotices,
  deleteNotices,
} from "../../../http/api/notice";
export default {
  name: "noticeList",

  data() {
    return {
      fields: [
        { key: "id", label: "id" },
        { key: "content", label: "公告内容" },
        { key: "publishDate", label: "发布时间" },
        { key: "userId", label: "发布者" },
        { key: "actions", label: "操作" },
      ],
      items: null,
      notice: {
        id: 0,
        content: " ",
        publishDate: " ",
        userId: 0,
        idDelete: 0,
      },
      modalTitle: "发布,编辑",
      optionBtnText: null,
      keywords: "",
      deleteId: null,
    };
  },
  mounted() {
    this.getAllNotices();
  },
  methods: {
    addEditNotice() {
      if (this.optionBtnText == "发布") {
        console.log("ann ===", this.optionBtnText);
        const data = {
          id: 0,
          content: this.notice.content,
          publishDate: "",
          userId: JSON.parse(window.localStorage.getItem("user")).id,
          isDelete: 0,
        };
        console.log("data", data);
        addNotices(data).then((resp) => {
          if (resp.code == 200) {
            this.$refs["modal-add-edit"].hide();
            this.getAllNotices();
          }
        });
      } else {
        console.log("ann ===", this.optionBtnText);
        var id = this.notice.id;
        var content = this.notice.content;
        var userId = this.notice.userId;
        updateNotices(id, content, userId).then((resp) => {
          console.log("resp  ===", resp);
          if (resp.code == 200) {
            console.log("111===");
            console.log(resp.data);
            this.$refs["modal-add-edit"].hide();
            this.getAllNotices();
          }
        });
      }
      this.notice = this.notice;
    },

    addNotice() {
      this.$refs["modal-add-edit"].show();
      this.modalTitle = "发布";
      this.optionBtnText = "发布";
    },
    editNotice(row) {
      this.$refs["modal-add-edit"].show();
      this.modalTitle = "编辑";
      this.optionBtnText = "保存";
      this.notice = row;
      console.log("row", row);
    },
    deleteNotice() {
      deleteNotices(this.deleteId).then((resp) => {
        if (resp.code == 200) {
          console.log("删除成功！");
          this.getAllNotices();
          this.$refs["modal-delete"].hide();
        } else {
          console.log("删除失败，请重试");
        }
      });
    },
    deleteNoticeBtn(deleteId) {
      this.$refs["modal-delete"].show();
      this.deleteId = deleteId;
    },
    getAllNotices() {
      getAllNotice().then((resp) => {
        if (resp.code == 200) {
          console.log(resp.data);
          this.items = resp.data;
        }
        console.log("items", this.items);
      });
    },
    getNotices() {
      findNotices(this.keywords.trim()).then((resp) => {
        console.log("keywords", this.keywords);
        if (resp.code == 200) {
          console.log(resp.data);
          console.log("查找到以上用户！");
          this.items = resp.data;
        } else {
          console.log("没有找到此用户");
        }
      });
    },

    hideModal() {
      this.$refs["modal-add-edit"].hide();
      // this.$refs["modal-delete"].hide();
    },
    hideDeleteModal() {
      this.$refs["modal-delete"].hide();
    },
  },
};
</script>
<style scoped>
.add {
  border: 2px solid #b1d579;
  border-radius: 5px;
  background-color: #a1e8a4;
  display: flex;
  text-align: right;
}
</style>
