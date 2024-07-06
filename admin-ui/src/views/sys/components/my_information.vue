<template>
  <div>
    <b-list-group style="max-width: 100%;">
      <b-list-group-item class="d-flex align-items-center">
        <b-avatar class="mr-3"></b-avatar>
        <span class="mr-auto" v-if="currentUser != null">{{
          currentUser.nickname
        }}</span>

        <div>
          <b-button
            size="sm"
            @click="editSchedule"
            class="mr-1"
            variant="outline-success"
          >
            编 辑
          </b-button>
        </div>

        <b-modal
          ref="modal-edit"
          size="xl"
          title="修改信息"
          title-class="font-18"
        >
          <form>
            <b-form-group
              id="example text"
              label-cols-sm="1"
              label-cols-lg="1"
              label="昵称："
              label-for="text"
            >
              <b-form-input
                for="text"
                v-model="newNickname"
                placeholder="输入新的昵称"
              ></b-form-input>
            </b-form-group>
            <!-- <b-button v-b-modal.modal-1>注销</b-button>
            <b-modal id="modal-1" title="注销">
              <p class="my-4">您确定要注销您的账号 !</p>
            </b-modal> -->

            <b-button v-b-modal.modal-2 @click="showTrue">修改密码</b-button>
            <repasstry v-show="showing" />
          </form>
          <template v-slot:modal-footer>
            <b-button variant="secondary" @click="hideModal">取消</b-button>
            <b-button variant="primary" @click="editNickname">
              保存
              <i class="fab fa-telegram-plane ml-1"></i>
            </b-button>
          </template>
        </b-modal>
      </b-list-group-item>
    </b-list-group>
  </div>
</template>

<script>
import repasstry from "./repasstry";
import { updateNickname } from "../../../http/api/sys";

export default {
  name: "my_information",
  components: {
    repasstry,
  },
  data() {
    return {
      modalTitle: "",
      optionBtnText: "",
      showing: false,
      currentUser: null,
      newNickname: "",
    };
  },
  mounted() {
    this.currentUser = JSON.parse(window.localStorage.getItem("user"));
  },
  methods: {
    editSchedule() {
      this.$refs["modal-edit"].show();
    },
    hideModal() {
      this.$refs["modal-edit"].hide();
    },
    showTrue() {
      this.showing = !this.showing;
    },

    // 修改昵称
    editNickname() {
      var id = this.currentUser.id;
      var nickname = this.newNickname;

      if (nickname != "") {
        updateNickname(id, nickname).then((resp) => {
          if (resp.code == 200) {
            this.hideModal();
            this.newNickname = "";
            this.currentUser.nickname = nickname;
            // 更新 localStorage 中的数据
            const tempUser = JSON.parse(window.localStorage.getItem("user"));
            tempUser.nickname = nickname;
            window.localStorage.setItem("user", JSON.stringify(tempUser));
          }
        });
      }
    },
  },
};
</script>

<style scoped></style>
