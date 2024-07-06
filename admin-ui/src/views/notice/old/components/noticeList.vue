<template>
  <div>
    <b-table :items="items" :fields="fields" striped responsive="sm">
      <template #cell(show_details)="row">
        <b-button size="sm" @click="row.toggleDetails" class="mr-2">
          {{ row.detailsShowing ? "Hide" : "Show" }} Details
        </b-button>

        <!-- As `row.showDetails` is one-way, we call the toggleDetails function on @change -->
        <b-form-checkbox
          v-model="row.detailsShowing"
          @change="row.toggleDetails"
        >
          显示详细信息
        </b-form-checkbox>
      </template>

      <!-- <template #cell(options)="row"> -->
      <template #cell(options)="row">
        <!-- <b-button size="sm" class="mr-2" @click="deleteNotice(row.item.id)">
          删除
        </b-button> -->
        <b-button
          v-b-modal.modal-1
          size="sm"
          class="mr-2"
          @click="deleteNotice(row.item.id)"
          >删除</b-button
        >
        <!-- @click="deleteNotice(row.item.id)" -->
        <b-modal id="modal-1" title="删除">
          <p class="my-4">您确定要删除 !</p>
        </b-modal>
      </template>

      <template #row-details="row">
        <b-card>
          <b-row class="mb-2">
            <b-col sm="3" class="text-sm-right"><b>公告发布时间：</b></b-col>
            <b-col>{{ row.item.time }}</b-col>
          </b-row>

          <b-row class="mb-2">
            <b-col sm="3" class="text-sm-right"><b>公告内容:</b></b-col>
            <b-col>{{ row.item.content }}</b-col>
          </b-row>

          <!--  <b-button size="sm" @click="row.toggleDetails">详情</b-button> -->
        </b-card>
      </template>
    </b-table>
  </div>
</template>

<script>
export default {
  name: "noticeList",
  data() {
    return {
      fields: ["公告名称", "发布部门", "show_details", "options"],
      items: [
        {
          id: 1,
          time: "2023/04/09",
          content: "因每面例行检查，预于2023/04/10局部停电",
          公告名称: "停电！！",
          发布部门: "系统管理员",
          options: "",
        },
        {
          id: 2,
          time: "2023/04/09",
          content: "因每面例行检查，预于2023/04/10局部停电",
          公告名称: "关于劳动节放假通知",
          发布部门: "一线",
          options: "",
        },
        {
          id: 3,
          time: "2023/04/09",
          content: "因每面例行检查，预于2023/04/10局部停电",
          公告名称: "停电！！",
          发布部门: "系统管理员",
          options: "",
        },
        {
          id: 4,
          time: "2023/04/09",
          content: "因每面例行检查，预于2023/04/10局部停电",
          公告名称: "获奖人员",
          发布部门: "主管部门",
          options: "",
        },
      ],
    };
  },
  methods: {
    /*    fullName(value) {
      return `${value.first} ${value.last}`
    }*/
    deleteNotice(id) {
      console.log(id);
    },
  },
};
</script>

<style scoped></style>
