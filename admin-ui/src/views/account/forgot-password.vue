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

                        <h4 class="font-size-18 mt-4">重置密码</h4>
                        <p class="text-muted">重新设置你账户的密码.</p>
                      </div>

                      <div class="p-2 mt-5">
                        <b-alert
                          v-model="isResetError"
                          class="mb-4"
                          variant="danger"
                          dismissible
                          >{{ error }}</b-alert
                        >

                        <form
                          class="form-horizontal"
                          @submit.prevent="tryToReset"
                        >
                          <div class="form-group auth-form-group-custom mb-4">
                            <i class="ri-mail-line auti-custom-input-icon"></i>
                            <label for="useremail">邮箱</label>
                            <input
                              v-model="email"
                              type="email"
                              class="form-control"
                              id="useremail"
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
                                >邮箱号不能为空.</span
                              >
                              <span v-if="!$v.email.email"
                                >请输入有效的邮箱号.</span
                              >
                            </div>
                          </div>

                          <div class="mt-4 text-center">
                            <button
                              class="btn btn-primary w-md waves-effect waves-light"
                              type="submit"
                            >
                              重置
                            </button>
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

export default {
  data() {
    return {
      email: "",
      submitted: false,
      error: null,
      tryingToReset: false,
      isResetError: false,
    };
  },
  validations: {
    email: { required, email },
  },
  created() {
    document.body.classList.add("auth-body-bg");
  },
  methods: {
    tryToReset() {
      this.submitted = true;

      this.$v.$touch();

      if (this.$v.$invalid) {
        return;
      } else {
        this.tryingToReset = true;

        this.error = null;
        return (
          this.resetPassword({
            email: this.email,
          })
            // eslint-disable-next-line no-unused-vars
            .then((token) => {
              this.tryingToReset = false;
              this.isResetError = false;
            })
            .catch((error) => {
              this.tryingToReset = false;
              this.error = error ? error : "";
              this.isResetError = true;
            })
        );
      }
    },
  },
};
</script>
