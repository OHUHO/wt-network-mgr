<template>
  <Layout>
    <PageHeader :title="title" :items="items" />
    <!-- @contextmenu.prevent="" -->
    <div class="row">
      <div class="col-lg-12">
        <div class="card">
          <div class="card-body">
            <div class="row" style="min-height: 544px;">
              <div class="col-md-1">
                <div class="row justify-content-center">
                  <div class="">
                    <img
                      class="user-avatar rounded-circle"
                      src="@/assets/images/users/avatar-2.jpg"
                    />
                    <div class="user-profile">
                      <div class="user-profile-main">
                        <div class="user-profile-header">
                          <img
                            src="@/assets/images/users/avatar-2.jpg"
                            class="avatar-xs rounded-circle"
                            alt=""
                          />
                          <div>{{ currentUser.nickname }}</div>
                        </div>
                        <div class="user-profile-info">
                          <div class="user-profile-info-title">邮箱</div>
                          <div>{{ currentUser.email }}</div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="row justify-content-center p-4">
                  <div class="">
                    <router-link
                      tag="div"
                      class="menu-item text-center"
                      to="/message/conversations"
                      replace
                    >
                      <i class="ri-message-2-line font-size-24"></i>
                      <span class="mt-2 d-none d-sm-block">聊 天</span>
                      <span v-if="unreadAmount" class="menu-unread">{{
                        unreadAmount
                      }}</span>
                    </router-link>
                  </div>
                </div>
                <div class="row justify-content-center p-4">
                  <div class="">
                    <router-link
                      tag="div"
                      class="menu-item text-center"
                      to="/message/contacts"
                      replace
                    >
                      <i class="ri-group-line font-size-24"></i>
                      <span class="mt-2 d-none d-sm-block">联系人</span>
                    </router-link>
                  </div>
                </div>
              </div>
              <div class="col-md-11">
                <router-view />
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
// import Message from "@/views/message/components/message";

export default {
  components: { Layout, PageHeader },

  data() {
    return {
      title: "短信管理",
      items: [
        {
          text: "吴彦组网络管理系统",
        },
        {
          text: "短信管理",
          active: true,
        },
      ],
      chatData: [],
      currentUser: null,
      unreadAmount: null,
    };
  },

  created() {
    this.currentUser = JSON.parse(window.localStorage.getItem("user"));
    if (this.goEasy.getConnectionStatus() == "disconnected") {
      this.connectGoEasy(); //连接goeasy
    }
    this.goEasy.im.on(
      this.GoEasy.IM_EVENT.CONVERSATIONS_UPDATED,
      this.setUnreadNumber
    );
  },
  methods: {
    connectGoEasy() {
      this.goEasy.connect({
        id: this.currentUser.id,
        data: { nickname: this.currentUser.nickname },
        onSuccess: function() {
          //连接成功
          console.log("GoEasy connect successfully."); //连接成功
        },
        onFailed: function(error) {
          //连接失败
          console.log(
            "Failed to connect GoEasy, code:" +
              error.code +
              ",error:" +
              error.content
          );
        },
        onProgress: function(attempts) {
          //连接或自动重连中
          console.log("GoEasy is connecting", attempts);
        },
      });
    },
    setUnreadNumber(content) {
      this.unreadAmount = content.unreadTotal;
    },
  },
};
</script>

<style lang="scss" scoped>
.home {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.user-avatar {
  width: 40px;
  height: 40px;
  border-radius: 10px;
  cursor: pointer;
}

.user-avatar:hover + .user-profile {
  display: block;
}

.user-profile {
  display: none;
  color: #ffffff;
  position: absolute;
  top: 0;
  left: 70px;
  width: 250px;
  height: 200px;
  background: #ffffff;
  z-index: 999;
}

.user-profile-main {
  border: 1px solid #ebeef5;
  background-color: #fff;
  color: #303133;
  border-radius: 4px;
}

.user-profile-header {
  padding: 18px 20px;
  border-bottom: 1px solid #ebeef5;
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 15px;
  font-weight: bold;
}

.user-profile-header img {
  width: 45px;
  height: 45px;
}

.user-profile-info {
  display: flex;
  padding: 10px 20px;
  font-size: 14px;
  color: #666666;
  line-height: 28px;
}

.user-profile-info-title {
  width: 70px;
}

.menu-item {
  color: #303133;
  cursor: pointer;
  height: 56px;
  position: relative;
}

.menu-unread {
  position: absolute;
  top: -5px;
  right: 5px;
  width: 18px;
  height: 18px;
  line-height: 18px;
  text-align: center;
  border-radius: 50%;
  background-color: #5664d2;
  color: #ffffff;
}

.router-link-active i {
  color: #5664d2 !important;
}

.iconfont {
  padding: 15px;
  font-size: 28px;
  color: #606266;
  cursor: pointer;
}

.iconfont:active {
  color: #5664d2;
}
</style>
