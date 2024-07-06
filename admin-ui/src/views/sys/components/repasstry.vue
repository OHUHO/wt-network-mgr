<template>
  <div
    id="editpwd"
    closed="true"
    class="easyui-window"
    title="修改密码"
    style="width:700px;padding:5px;"
  >
    <div style="color:red">
      {{ info }}
    </div>
    <!-- <center> -->
    <!-- <form id="detailForm" style="height:100%; margin-top:10px;"> -->
    <table
      class="table table-bordered"
      style="height:200px; width:90%; margin-top:10px;"
    >
      <tr>
        <td class="col-sm-2" style="background-color:#3FA9D0;">
          <div
            class="table-form-label"
            style="padding-top: 10px"
            align="center"
          >
            <label for="old_pwd" class="control-label">旧密码</label>
          </div>
        </td>
        <td>
          <div class="col-sm-5">
            <input
              type="password"
              class="form-control"
              id="old_pwd"
              name="old_pwd"
              v-model="oldPassword"
            />
          </div>
        </td>
      </tr>
      <tr>
        <td class="col-sm-2" style="background-color:#3FA9D0;">
          <div
            class="table-form-label"
            style="padding-top: 10px"
            align="center"
          >
            <label for="new_pwd" class="control-label">新密码</label>
          </div>
        </td>
        <td>
          <div class="col-sm-5">
            <input
              type="password"
              class="form-control"
              id="new_pwd"
              name="new_pwd"
              v-model="password"
            />
          </div>
        </td>
      </tr>
      <tr>
        <td class="col-sm-2" style="background-color:#3FA9D0;">
          <div
            class="table-form-label"
            style="padding-top: 10px"
            align="center"
          >
            <label for="confirm_pwd" class="control-label">确认密码</label>
          </div>
        </td>
        <td>
          <div class="col-sm-5">
            <input
              type="password"
              id="confirm_pwd"
              name="confirm_pwd"
              class="form-control"
              v-model="rePassword"
            />
          </div>
        </td>
      </tr>
      <tr>
        <td colspan="2">
          <div class="col-sm-5 col-md-offset-2">
            <button
              class="btn btn-success fa fa-check"
              style="background-color:#3FA9D0"
              @click.prevent="resetPassword"
            >
              提交
            </button>
          </div>
        </td>
      </tr>
    </table>

    <!-- </center> -->
  </div>
</template>

<script>
import { updatePassword } from "../../../http/api/sys";
export default {
  name: "repasstry",
  data() {
    return {
      info: "",
      oldPassword: "",
      password: "",
      rePassword: "",
    };
  },
  methods: {
    resetPassword() {
      if (this.oldPassword == "" || this.password == "") {
        this.info = "输入不能为空！";
        return;
      }
      if (this.password != this.rePassword) {
        this.info = "请保持密码一致！";
        return;
      }
      var id = JSON.parse(window.localStorage.getItem("user")).id;
      updatePassword(id, this.oldPassword, this.password).then((resp) => {
        if (resp.code == 500) {
          this.info = resp.message;
        }
        if (resp.code == 200) {
          window.localStorage.removeItem("token");
          this.$router.go(0);
        }
      });
    },
  },
};
</script>

<style scoped></style>
