<template>
  <div>
    <div class="home-btn d-none d-sm-block">
      <a href="/">
        <i class="mdi mdi-home-variant h2 text-white"></i>
      </a>
    </div>
    <div>
      <div class="container-fluid p-0">
        <div class="row no-gutters">
          <div class="col-lg-8">
            <div class="authentication-bg">
              <div class="bg-overlay"></div>
            </div>
          </div>
          <div class="col-lg-4">
            <div
              class="authentication-page-content p-4 d-flex align-items-center min-vh-100"
            >
              <div class="w-100">
                <div class="row justify-content-center">
                  <div class="col-lg-9">
                    <div>
                      <div class="text-center">
                        <div>
                          <a href="/" class="logo">
                            <img
                              src="@/assets/images/logo-dark.png"
                              height="20"
                              alt="logo"
                            />
                          </a>
                        </div>

                        <h4 class="font-size-18 mt-4">
                          欢迎来到网络管理系统 !
                        </h4>
                        <p class="text-muted">登录系统.</p>
                      </div>

                      <b-alert
                        variant="danger"
                        class="mt-3"
                        v-if="logNotification"
                        show
                        dismissible
                        >{{ logNotification }}</b-alert
                      >
                      <b-alert
                        variant="success"
                        class="mt-3"
                        v-if="regNotification"
                        show
                        dismissible
                        >{{ regNotification }}</b-alert
                      >

                      <div class="p-2 mt-5">
                        <form
                          class="form-horizontal"
                          @submit.prevent="tryToLogIn"
                        >
                          <div class="form-group auth-form-group-custom mb-4">
                            <i class="ri-mail-line auti-custom-input-icon"></i>
                            <label for="email">邮箱</label>
                            <input
                              type="email"
                              v-model="email"
                              class="form-control"
                              id="email"
                              placeholder="输入邮箱"
                              :class="{
                                'is-invalid': submitted && $v.email.$error,
                              }"
                            />
                            <div
                              v-if="submitted && $v.email.$error"
                              class="invalid-feedback"
                            >
                              <span v-if="!$v.email.required"
                                >邮箱不能为空.</span
                              >
                              <span v-if="!$v.email.email"
                                >请输入有效的邮箱号.</span
                              >
                            </div>
                          </div>

                          <div class="form-group auth-form-group-custom mb-4">
                            <i
                              class="ri-lock-2-line auti-custom-input-icon"
                            ></i>
                            <label for="userpassword">密码</label>
                            <input
                              v-model="password"
                              type="password"
                              class="form-control"
                              id="userpassword"
                              placeholder="输入密码"
                              :class="{
                                'is-invalid': submitted && $v.password.$error,
                              }"
                            />
                            <div
                              v-if="submitted && !$v.password.required"
                              class="invalid-feedback"
                            >
                              密码不能为空.
                            </div>
                          </div>

                          <div class="custom-control custom-checkbox">
                            <input
                              type="checkbox"
                              class="custom-control-input"
                              id="customControlInline"
                            />
                            <label
                              class="custom-control-label"
                              for="customControlInline"
                              >记住密码</label
                            >
                          </div>

                          <div class="mt-4 text-center">
                            <button
                              class="btn btn-primary w-md waves-effect waves-light"
                              type="submit"
                            >
                              登录
                            </button>
                          </div>

                          <div class="mt-4 text-center">
                            <router-link
                              tag="a"
                              to="/forgot-password"
                              class="text-muted"
                            >
                              <i class="mdi mdi-lock mr-1"></i> 忘记密码 ?
                            </router-link>
                          </div>
                        </form>
                      </div>

                      <div class="mt-5 text-center">
                        <p>
                          还没有账户 ?
                          <router-link
                            tag="a"
                            to="/register"
                            class="font-weight-medium text-primary"
                            >注册</router-link
                          >
                        </p>
                        <p>
                          © 2023 吴彦组. Crafted with
                          <i class="mdi mdi-heart text-danger"></i> by 吴彦组
                        </p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { required, email } from "vuelidate/lib/validators";
import { login, findUserInfo } from "../../http/api/auth";

export default {
  data() {
    return {
      email: "jc.top@qq.com",
      password: "123456",
      submitted: false,
      logNotification: "",
      regNotification: "",
    };
  },
  computed: {},
  created() {
    document.body.classList.add("auth-body-bg");
  },
  validations: {
    email: { required, email },
    password: { required },
  },
  mounted() {
    this.regNotification = window.localStorage.getItem("regNotification");
    window.localStorage.removeItem("regNotification");
  },
  methods: {
    // 登录
    tryToLogIn() {
      this.submitted = true;
      // 表单无效则不进行下面的操作
      this.$v.$touch();

      if (this.$v.$invalid) {
        return;
      } else {
        //  发送登录请求
        const data = {
          email: this.email,
          password: this.password,
        };
        login(data).then((resp) => {
          if (resp.code == 200) {
            console.log("resppp", resp);
            window.localStorage.setItem("token", JSON.stringify(resp.data));
            this.getUserInfo();
            // 重定向到请求页 或 主页
            this.$router.push("/");
          } else {
            this.logNotification = resp.message;
          }
        });
      }
    },
    getUserInfo() {
      findUserInfo(this.email).then((resp) => {
        if (resp.code == 200) {
          window.localStorage.setItem("user", JSON.stringify(resp.data));
          // 设置用户数据，用于goEasy
          // this.globalData.currentUser = resp.data;
        }
      });
    },
  },
};
</script>
