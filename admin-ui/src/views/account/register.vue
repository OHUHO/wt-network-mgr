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

                        <h4 class="font-size-18 mt-4">欢迎来到网络管理系统.</h4>
                        <p class="text-muted">
                          立刻免费注册一个用户.
                        </p>
                      </div>

                      <div class="p-2 mt-5">
                        <b-alert
                          v-if="regNotification"
                          class="mt-3"
                          variant="danger"
                          show
                          dismissible
                          >{{ regNotification }}</b-alert
                        >

                        <form
                          class="form-horizontal"
                          @submit.prevent="tryToRegisterIn"
                        >
                          <div class="form-group auth-form-group-custom mb-4">
                            <i
                              class="ri-user-2-line auti-custom-input-icon"
                            ></i>
                            <label for="nickname">昵称</label>
                            <input
                              v-model="user.nickname"
                              type="text"
                              class="form-control"
                              id="nickname"
                              :class="{
                                'is-invalid':
                                  submitted && $v.user.nickname.$error,
                              }"
                              placeholder="输入您的昵称"
                            />
                            <div
                              v-if="submitted && !$v.user.nickname.required"
                              class="invalid-feedback"
                            >
                              用户名不能为空.
                            </div>
                          </div>

                          <div class="form-group auth-form-group-custom mb-4">
                            <i class="ri-mail-line auti-custom-input-icon"></i>
                            <label for="useremail">邮箱</label>
                            <input
                              v-model="user.email"
                              type="email"
                              class="form-control"
                              id="useremail"
                              placeholder="输入邮箱"
                              :class="{
                                'is-invalid': submitted && $v.user.email.$error,
                              }"
                            />
                            <div
                              v-if="submitted && $v.user.email.$error"
                              class="invalid-feedback"
                            >
                              <span v-if="!$v.user.email.required"
                                >邮箱号不能为空.</span
                              >
                              <span v-if="!$v.user.email.email"
                                >请输入正确的邮箱号.</span
                              >
                            </div>
                          </div>

                          <div class="form-group auth-form-group-custom mb-4">
                            <i
                              class="ri-lock-2-line auti-custom-input-icon"
                            ></i>
                            <label for="userpassword">密码</label>
                            <input
                              v-model="user.password"
                              type="password"
                              class="form-control"
                              id="userpassword"
                              placeholder="输入密码"
                              :class="{
                                'is-invalid':
                                  submitted && $v.user.password.$error,
                              }"
                            />
                            <div
                              v-if="submitted && !$v.user.password.required"
                              class="invalid-feedback"
                            >
                              密码不能为空.
                            </div>
                          </div>

                          <div class="form-group auth-form-group-custom mb-4">
                            <i
                              class=" ri-group-line auti-custom-input-icon"
                            ></i>
                            <label for="role">角色</label>
                            <b-form-select
                              id="role"
                              class="form-control"
                              v-model="user.role"
                              :options="roles"
                              size="sm"
                              :class="{
                                'is-invalid': submitted && $v.user.role.$error,
                              }"
                            ></b-form-select>
                            <div
                              v-if="submitted && !$v.user.role.required"
                              class="invalid-feedback"
                            >
                              角色不能为空.
                            </div>
                          </div>

                          <div class="text-center">
                            <button
                              class="btn btn-primary w-md waves-effect waves-light"
                              type="submit"
                            >
                              注册
                            </button>
                          </div>

                          <div class="mt-4 text-center">
                            <p class="mb-0">
                              一旦注册就默认你同意我们的
                              <a href="#" class="text-primary">用户协议</a>
                            </p>
                          </div>
                        </form>
                      </div>

                      <div class="mt-5 text-center">
                        <p>
                          已经有一个账户了 ?
                          <router-link
                            tag="a"
                            to="/login"
                            class="font-weight-medium text-primary"
                            >登录</router-link
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
import { register } from "../../http/api/auth";

export default {
  data() {
    return {
      user: { nickname: "", email: "", password: "", role: null },

      roles: [
        { value: null, text: "请选择您的角色", disabled: true },
        { value: "CHASER", text: "一线人员" },
        { value: "SUPERVISOR", text: "部门主管" },
        { value: "ADMIN", text: "系统管理员" },
        { value: "Administrator", text: "超级管理员", disabled: true },
      ],
      submitted: false,
      regNotification: "",
    };
  },
  computed: {},
  validations: {
    user: {
      nickname: { required },
      email: { required, email },
      password: { required },
      role: { required },
    },
  },
  created() {
    document.body.classList.add("auth-body-bg");
  },
  methods: {
    tryToRegisterIn() {
      this.submitted = true;
      this.$v.$touch();

      if (this.$v.$invalid) {
        return;
      } else {
        // console.log(this.user);
        register(this.user).then((resp) => {
          console.log(resp);
          if (resp.code == 200) {
            window.localStorage.setItem("regNotification", "注册成功！");
            this.$router.push("/login");
          } else {
            this.regNotification = resp.message;
            // console.log(resp.message);
          }
        });
      }
    },
  },
};
</script>
