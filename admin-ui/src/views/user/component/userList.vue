<template>
  <div>
    <div class="row">
      <div class="col-lg-12">
        <div class="card">
          <div class="card-body">
            <div class="row">
              <div class="col-lg-12">
                <div class="row">
                  <div class="col-md-9">
                    <a
                      href="javascript:void(0);"
                      class="btn btn-success mb-2"
                      @click="addUser"
                    >
                      <i class="mdi mdi-plus mr-2"></i> 添加用户
                    </a>
                  </div>

                  <div class="col-md-3">
                    <div class="search-box mr-2">
                      <div class="position-relative">
                        <input
                          id="filter-input"
                          v-model="keywords"
                          @keyup.enter="getUsers"
                          type="search"
                          class="form-control"
                          placeholder="输入关键词..."
                        />
                        <i class="mdi mdi-magnify search-icon"></i>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="table-responsive">
                  <table
                    class="table table-centered datatable dt-responsive nowrap"
                    style="
                      'border-collapse':collapse ;
                      'border-spacing':0 ;
                      width: '100%';
                    "
                  >
                    <thead class="thead-light">
                      <tr>
                        <th>id</th>
                        <th>用户名</th>
                        <th>用户邮箱</th>
                        <th>用户角色</th>
                        <!--                  <th>Wallet Balance</th>
                                        <th>Joining Date</th>-->
                        <th style="width: 120px">操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr v-for="(item, index) in customersData" :key="index">
                        <td>{{ item.id }}</td>
                        <td>{{ item.nickname }}</td>
                        <td>{{ item.email }}</td>
                        <td>{{ handleRole(item.role) }}</td>
                        <td>
                          <a
                            href="javascript:void(0);"
                            class="mr-3 text-primary"
                            v-b-tooltip.hover
                            title="Edit"
                          >
                            <i
                              class="mdi mdi-pencil font-size-18"
                              size="sm"
                              @click="editUserBtn(item)"
                              variant="outline-success"
                            ></i>
                          </a>
                          <a
                            href="javascript:void(0);"
                            class="text-danger"
                            v-b-tooltip.hover
                            title="Delete"
                          >
                            <i
                              class="mdi mdi-trash-can font-size-18"
                              size="sm"
                              @click="deleteUserBtn(item.id)"
                              variant="outline-danger"
                            >
                            </i>
                          </a>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 删除 -->
    <b-modal ref="modal-delete" title="确认删除？">
      <h6>删除</h6>
      <template v-slot:modal-footer>
        <b-button variant="secondary" @click="hideDeleteModal">取消</b-button>
        <b-button variant="primary" @click="deleteUsers">
          确定
          <i class="fab fa-telegram-plane ml-1"></i>
        </b-button>
      </template>
    </b-modal>

    <!-- 添加 编辑弹窗 -->
    <b-modal
      id="modal-1"
      ref="modal-add-edit"
      :title="modalTitle"
      title-class="text-dark font-18"
      hide-footer
    >
      <!--  @submit.prevent="tryToRegisterIn" -->
      <form>
        <div class="form-group">
          <label for="email">邮箱</label>
          <input
            id="email"
            v-model="customers.email"
            type="text"
            class="form-control"
            :readonly="optionBtnText != '添加'"
            placeholder="请输入邮箱"
            :class="{ 'is-invalid': submitted && $v.customers.email.$error }"
          />
          <div
            v-if="submitted && !$v.customers.email.required"
            class="invalid-feedback"
          >
            必须填写！！
          </div>
        </div>
        <div class="form-group">
          <label for="name">用户登录名</label>
          <input
            id="nickname"
            v-model="customers.nickname"
            type="text"
            class="form-control"
            :readonly="optionBtnText != '添加'"
            placeholder="输入用户登录名"
            :class="{ 'is-invalid': submitted && $v.customers.nickname.$error }"
          />
          <div
            v-if="submitted && !$v.customers.nickname.required"
            class="invalid-feedback"
          >
            必须填写！！
          </div>
        </div>
        <div class="form-group">
          <label for="company">密码</label>
          <input
            id="password"
            v-model="customers.password"
            type="text"
            class="form-control"
            placeholder="输入密码"
            :class="{ 'is-invalid': submitted && $v.customers.password.$error }"
          />
          <div
            v-if="submitted && !$v.customers.password.required"
            class="invalid-feedback"
          >
            必须填写
          </div>
        </div>

        <div class="form-group">
          <label for="position">用户角色</label>

          <b-form-select
            id="role"
            class="form-control"
            v-model="customers.role"
            :options="roles"
            size="sm"
            :class="{ 'is-invalid': submitted && $v.customers.role.$error }"
          ></b-form-select>
          <div
            v-if="submitted && !$v.customers.role.required"
            class="invalid-feedback"
          >
            角色不能为空.
          </div>
        </div>
        <div class="text-right">
          <button type="submit" class="btn btn-success" @click="addEditUser">
            {{ optionBtnText }}
          </button>
          <b-button class="ml-1" variant="danger" @click="hideAddEditModal"
            >取消</b-button
          >
        </div>
      </form>
    </b-modal>
  </div>
</template>

<script>
import {
  addUser,
  findAllUser,
  findUsers,
  deleteUsers,
  updateUsers,
} from "../../../http/api/user";

import {
  // email, minLength
  required,
} from "vuelidate/lib/validators";

export default {
  name: "user_list",
  data() {
    return {
      title: "用户列表",
      keywords: "",
      deleteId: null,
      optionBtnText: null,
      items: [
        {
          text: "Ecommerce",
        },
        {
          text: "用户列表",
          active: true,
        },
      ],
      submitted: false,
      customersData: [],
      customers: {
        email: "",
        nickname: "",
        password: "",
        role: null,
      },
      showmodal: false,

      modalTitle: "发布、编辑",

      // 用户角色
      roles: [
        { value: null, text: "请选择您的角色", disabled: true },
        { value: "CHASER", text: "一线人员" },
        { value: "SUPERVISOR", text: "部门主管" },
        { value: "ADMIN", text: "系统管理员" },
        { value: "Administrator", text: "超级管理员", disabled: true },
      ],

      editUser: null,
    };
  },
  validations: {
    customers: {
      email: { required },
      nickname: { required },
      password: { required },
      // email: { required, email },
      role: { required },
      // date: { required },
    },
  },
  mounted() {
    this.getAllUsers();
  },
  computed: {
    handleRole: () => {
      return (role) => {
        if (role == "ADMIN") return "系统管理员";
        if (role == "SUPERVISOR") return "部门主管";
        if (role == "CHASER") return "一线人员";
      };
    },
  },

  methods: {
    // 删除
    deleteperson() {
      this.$refs["modal-delete"].show();
    },
    addUser() {
      this.$refs["modal-add-edit"].show();
      this.modalTitle = "添加用户";
      this.optionBtnText = "添加";
    },
    editUserBtn(user) {
      this.$refs["modal-add-edit"].show();
      this.modalTitle = "编辑用户";
      this.optionBtnText = "保存";
      this.customers = user;
    },
    addEditUser() {
      console.log(this.optionBtnText);
      if (this.optionBtnText == "添加") {
        const data = {
          email: this.customers.email,
          password: this.customers.password,
          nickname: this.customers.nickname,
          role: this.customers.role,
        };
        console.log("data", data);
        addUser(data).then((resp) => {
          if (resp.code == 200) {
            console.log(resp.data);
            console.log("添加成功！");
            this.hideAddEditModal();
            this.getAllUsers();
          } else {
            console.log("添加失败！");
          }
        });
      } else {
        var id = this.customers.id;
        var password = this.customers.password;
        var role = this.customers.role;

        updateUsers(id, password, role).then((resp) => {
          if (resp.code == 200) {
            console.log(resp.data);
            this.hideAddEditModal();
            this.getAllUsers();
          }
        });
      }

      this.customers = {
        email: "",
        nickname: "",
        password: "",
        role: null,
      };
    },
    // tryToRegisterIn() {
    //   this.submitted = true;
    //   this.$v.$touch();

    //   if (this.$v.$invalid) {
    //     return;
    //   } else {
    //     // return;
    //     // this.addEditUser();
    //   }
    // },

    deleteUserBtn(deleteId) {
      this.$refs["modal-delete"].show();
      this.deleteId = deleteId;
    },
    hideDeleteModal() {
      this.$refs["modal-delete"].hide();
    },
    hideAddEditModal() {
      if (this.optionBtnText != "添加") {
        this.customers = {
          email: "",
          nickname: "",
          password: "",
          role: null,
        };
      }
      this.$refs["modal-add-edit"].hide();
    },
    getAllUsers() {
      findAllUser().then((resp) => {
        if (resp.code == 200) {
          console.log(resp.data);
          this.customersData = resp.data;
        }
      });
    },
    getUsers() {
      findUsers(this.keywords.trim()).then((resp) => {
        console.log("keywords", this.keywords);
        console.log("resp", resp.data);
        if (resp.code == 200) {
          console.log(resp.data);
          console.log("查找到以上用户！");
          this.customersData = resp.data;
        } else {
          console.log("没有找到此用户");
        }
      });
    },
    deleteUsers() {
      deleteUsers(this.deleteId).then((resp) => {
        if (resp.code == 200) {
          console.log("删除成功！");
          this.getAllUsers();
          this.$refs["modal-delete"].hide();
        } else {
          console.log("删除失败，请重试");
        }
      });
    },

    // eslint-disable-next-line no-unused-vars

    onFiltered(filteredItems) {
      this.totalRows = filteredItems.length;
      this.currentPage = 1;
    },
  },
};
</script>

<style scoped></style>
