<template>
  <div class="row">
    <div class="col-md-3">
      <h5 class="font-size-14 px-3 mb-3">联系人</h5>

      <simplebar style="height: 530px">
        <ul class="list-unstyled chat-list">
          <li
            v-for="(friend, key) in friends || []"
            :key="key"
            class="friend-item"
            :class="{
              actived: profile.friend && profile.friend.id == friend.id,
            }"
            @click="showFriendProfile(friend)"
          >
            <a href="#">
              <div class="media align-items-center">
                <div class="avatar-xs mr-3" style="width:44px;height:44px">
                  <span
                    class="avatar-title rounded-circle bg-light text-body"
                    >{{ friend.nickname.substr(-2, 2) }}</span
                  >
                </div>
                <div class="media-body">
                  <h5 class="font-size-14 mb-0">{{ friend.nickname }}</h5>
                  <h5 class="font-size-10 mb-0">{{ friend.email }}</h5>
                </div>
              </div>
            </a>
          </li>
        </ul>
      </simplebar>
    </div>
    <div class="col-md-9">
      <div class="card">
        <div class="card-body" v-if="profile.friend">
          <div class="text-center">
            <div class="d-inline-flex align-items-center">
              <div class="avatar-sm mt-2 mb-4" style="width:66px;height:66px">
                <span class="avatar-title rounded-circle bg-light text-body">{{
                  profile.friend.nickname.substr(-2, 2)
                }}</span>
              </div>
            </div>
            <div class="media-body">
              <h5 class="text-truncate">
                <a href="#" class="text-dark">{{ profile.friend.nickname }}</a>
              </h5>
              <!-- <p class="text-muted">
                <i class="mdi mdi-account mr-1"></i> {{ profile.friend.role }}
              </p> -->
            </div>
          </div>
          <hr class="my-4" />
          <div class="row text-center">
            <div class="col-6">
              <p class="text-muted mb-2">用户角色</p>
              <h5>{{ profile.friend.role }}</h5>
            </div>
            <div class="col-6">
              <p class="text-muted mb-2">联系方式</p>
              <h5>{{ profile.friend.email }}</h5>
            </div>
          </div>
          <hr class="my-4" />
          <div class="row text-center">
            <div class="col-12">
              <div class="button-box">
                <button class="card-button" @click="privateChat">发消息</button>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- <div class="profile-card" v-if="profile.friend">
        <div class="profile-card-title">
          <div class="profile-name">
            <div>{{ profile.friend.nickname }}</div>
          </div>
        </div>
        <div class="friend-info">
          <div class="info-name">邮 箱</div>
          <div class="info-text">{{ profile.friend.email }}</div>
        </div>
        <div class="button-box">
          <button class="card-button" @click="privateChat">发消息</button>
        </div>
      </div> -->
    </div>
  </div>
</template>

<script>
import simplebar from "simplebar-vue";
import { getAllUser } from "../../../http/api/user";

export default {
  components: { simplebar },
  data() {
    return {
      friends: [],
      profile: {
        friend: null,
      },
    };
  },
  mounted() {
    // const currentUser = JSON.parse(window.localStorage.getItem("user"));
    // this.friends = restApi.findFriends(currentUser);
    this.findAllUser();
  },
  methods: {
    findAllUser() {
      getAllUser().then((resp) => {
        if (resp.code == 200) {
          const users = resp.data;
          this.filterUser(users);
        }
      });
    },
    filterUser(users) {
      const currentUser = JSON.parse(window.localStorage.getItem("user"));
      this.friends = users.filter((v) => v.id !== currentUser.id);
    },
    showFriendProfile(friend) {
      this.profile.friend = friend;
    },

    privateChat() {
      this.$router.replace({
        path: "/message/conversations/privatechat/" + this.profile.friend.id,
        query: {
          nickname: this.profile.friend.nickname,
        },
      });
    },
  },
};
</script>

<style scoped>
.contact-main {
  flex: 1;
  background: #ffffff;
}

.profile-card {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.profile-card-title {
  flex: 1;
  border-bottom: 1px solid #eeeeee;
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.profile-name {
  width: 300px;
  font-size: 18px;
  display: flex;
  align-items: center;
}

.icon-zhanghu {
  font-size: 26px;
  color: #eeeeee;
  margin-right: 10px;
}

.profile-avatar {
  width: 80px;
}

.profile-avatar img {
  width: 80px;
  height: 80px;
  border-radius: 10%;
}

.friend-info {
  padding: 10px 30px;
  display: flex;
  justify-content: space-around;
  text-align: left;
  font-size: 14px;
  line-height: 45px;
}

.info-name {
  width: 100px;
}

.info-text {
  width: 200px;
}

.group-profile-name {
  font-size: 18px;
  padding: 20px 70px;
  border-bottom: 1px solid #eeeeee;
}

.group-members {
  width: 400px;
  min-height: 200px;
  margin: 20px auto;
  display: flex;
  flex-wrap: wrap;
  align-content: flex-start;
}

.group-members .member {
  width: 25%;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.group-members .member-avatar {
  width: 58px;
  margin-top: 20px;
  border-radius: 5%;
}

.group-members .member-name {
  color: gray;
  margin-top: 10px;
  font-size: 12px;
}

.button-box {
  padding: 40px 0;
}

.card-button {
  background: #eeeeee;
  color: #000000;
  font-size: 14px;
  border: none;
  display: flex;
  width: 120px;
  height: 35px;
  cursor: pointer;
  border-radius: 5px;
  margin: 0 auto;
  align-items: center;
  justify-content: center;
}
</style>
