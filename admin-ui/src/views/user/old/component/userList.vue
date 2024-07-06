<template>
  <div>
    <div class="row">
      <div class="col-lg-12">
        <div class="card">
          <div class="card-body">
            <div class="row">
              <div class="col-lg-12">
                <div>
                  <a
                    href="javascript:void(0);"
                    class="btn btn-success mb-2"
                    @click="addUser"
                  >
                    <i class="mdi mdi-plus mr-2"></i> 添加用户
                  </a>
                </div>
                <div class="table-responsive">
                  <table
                    class="table table-centered datatable dt-responsive nowrap"
                    style="
                  'border-collapse': collapse;
                  'border-spacing': 0;
                  width: '100%';
                "
                  >
                    <thead class="thead-light">
                      <tr>
                        <th style="width: 20px">
                          <div class="custom-control custom-checkbox">
                            <input
                              type="checkbox"
                              class="custom-control-input"
                              id="customercheck"
                            />
                            <label
                              class="custom-control-label"
                              for="customercheck"
                              >&nbsp;</label
                            >
                          </div>
                        </th>
                        <th>用户登录名</th>
                        <th>登录密码</th>
                        <th>用户级别</th>
                        <!--                  <th>Wallet Balance</th>
                                        <th>Joining Date</th>-->
                        <th style="width: 120px">操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr v-for="(item, index) in customersData" :key="index">
                        <td>
                          <div class="custom-control custom-checkbox">
                            <input
                              type="checkbox"
                              class="custom-control-input"
                              :id="`customercheck${index}`"
                            />
                            <label
                              class="custom-control-label"
                              :for="`customercheck${index}`"
                              >&nbsp;</label
                            >
                          </div>
                        </td>
                        <td>{{ item.operator }}</td>
                        <td>{{ item.password }}</td>
                        <td>{{ item.level }}</td>
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
                              @click="editUserBtn"
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
                              @click="deleteUserBtn"
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
        <b-button variant="primary" @click="deleteUser">
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
      <form @submit.prevent="handleSubmit">
        <div class="form-group">
          <label for="name">用户登录名</label>
          <input
            id="operator"
            v-model="customers.operator"
            type="text"
            class="form-control"
            placeholder="输入用户登录名"
            :class="{ 'is-invalid': submitted && $v.customers.operator.$error }"
          />
          <div
            v-if="submitted && !$v.customers.operator.required"
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
          <label for="position">用户级别</label>
          <input
            id="position"
            v-model="customers.level"
            type="text"
            class="form-control"
            placeholder="输入用户级别"
            :class="{ 'is-invalid': submitted && $v.customers.level.$error }"
          />
          <div
            v-if="submitted && !$v.customers.level.required"
            class="invalid-feedback"
          >
            必须填写！！
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
  // email, minLength,
  required,
} from "vuelidate/lib/validators";

export default {
  name: "user_list",
  data() {
    return {
      title: "用户列表",
      items: [
        {
          text: "Ecommerce",
        },
        {
          text: "用户列表",
          active: true,
        },
      ],
      customersData: [
        {
          operator: "17748724641",
          password: "123456",
          level: "0",
        },
        {
          operator: "17748724641",
          password: "123456",
          level: "1",
        },
        {
          operator: "17748724641",
          password: "123456",
          level: "2",
        },
        {
          operator: "17748724641",
          password: "123456",
          level: "2",
        },
        {
          operator: "17748724641",
          password: "123456",
          level: "1",
        },
        {
          operator: "17748724641",
          password: "123456",
          level: "0",
        },
        {
          operator: "17748724641",
          password: "123456",
          level: "0",
        },
        {
          operator: "17748724641",
          password: "123456",
          level: "0",
        },
        {
          operator: "17748724641",
          password: "123456",
          level: "0",
        },
        {
          operator: "17748724641",
          password: "123456",
          level: "0",
        },
        {
          operator: "17748724641",
          password: "123456",
          level: "0",
        },
        {
          operator: "17748724641",
          password: "123456",
          level: "0",
        },
      ],
      customers: {
        operator: "",
        password: "",
        level: "",
      },
      submitted: false,
      showmodal: false,

      modalTitle: "发布、编辑",
      optionBtnText: "按钮",
    };
  },
  validations: {
    customers: {
      operator: { required },
      password: { required },
      // email: { required, email },
      level: { required },
      // date: { required },
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
    editUserBtn() {
      this.$refs["modal-add-edit"].show();
      this.modalTitle = "编辑用户";
      this.optionBtnText = "保存";
    },
    addEditUser() {
      // this.$refs["modal-add-edit"].hide();
      // 发送请求
    },
    deleteUser() {
      this.$refs["modal-delete"].hide();
      // 发送请求
    },
    deleteUserBtn() {
      this.$refs["modal-delete"].show();
    },
    hideDeleteModal() {
      this.$refs["modal-delete"].hide();
    },
    hideAddEditModal() {
      this.$refs["modal-add-edit"].hide();
    },
    // eslint-disable-next-line no-unused-vars
    handleSubmit(e) {
      this.submitted = true;

      this.$v.$touch();
      if (this.$v.$invalid) {
        return;
      } else {
        const operator = this.customers.operator;
        const password = this.customers.password;
        const level = this.customers.level;
        this.customersData.push({
          operator,
          password,
          level,
        });
        this.showmodal = false;
      }
      this.submitted = false;
      this.customers = {};
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
