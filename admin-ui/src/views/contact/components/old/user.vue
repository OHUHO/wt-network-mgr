<script>
import { required, minLength } from "vuelidate/lib/validators";
export default {
  components: {},
  data() {
    return {
      title: "Customers",
      items: [
        {
          text: "Ecommerce",
        },
        {
          text: "Customers",
          active: true,
        },
      ],
      customersData: [
        {
          name: "韩梅",
          phone: "180-424-3680",
        },
        {
          name: "小夏",
          phone: "592-164-4194",
        },
        {
          name: "柳柳",
          phone: "146-364-8654",
        },
        {
          name: "小花",
          phone: "180-464-4954",
        },
        {
          name: "李华",
          phone: "113-248-2690",
        },
      ],
      customers: {
        name: "",
        phone: "",
      },
      submitted: false,
      showmodal: false,
    };
  },
  validations: {
    customers: {
      name: { required },
      phone: { required, minLength: minLength(10) },
    },
  },
  methods: {
    /**
     * Modal form submit
     */
    // eslint-disable-next-line no-unused-vars
    handleSubmit(e) {
      this.submitted = true;

      // stop here if form is invalid
      this.$v.$touch();
      if (this.$v.$invalid) {
        return;
      } else {
        const name = this.customers.name;
        const phone = this.customers.phone;
        this.customersData.push({
          name,
          phone,
        });
        this.showmodal = false;
      }
      this.submitted = false;
      this.customers = {};
    },
    // eslint-disable-next-line no-unused-vars
    hideModal(e) {
      this.submitted = false;
      this.showmodal = false;
      this.contacts = {};
    },

    onFiltered(filteredItems) {
      // Trigger pagination to update the number of buttons/pages due to filtering
      this.totalRows = filteredItems.length;
      this.currentPage = 1;
    },
    // 删除
    deleteperson() {
      this.$refs["modal-delete"].show();
    },
    hideModal1() {
      this.$refs["modal-add"].hide();
      this.$refs["modal-delete"].hide();
    },
    editperson() {
      this.$refs["modal-add"].show();
      this.modalTitle = "编辑";
      this.optionBtnText = "保存";
    },
  },
};
</script>

<template>
  <div>
    <div class="row">
      <div class="col-lg-12">
        <div class="card">
          <div class="card-body">
            <div>
              <a
                href="javascript:void(0);"
                class="btn btn-success mb-2"
                @click="showmodal = true"
              >
                <i class="mdi mdi-plus mr-2"></i> 添加联络人
              </a>
            </div>
            <div class="table-responsive mt-3">
              <table
                class="table table-centered datatable dt-responsive nowrap"
                style="
                        border-collapse: collapse;
                        border-spacing: 0;
                        width: 100%;"
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
                        <label class="custom-control-label" for="customercheck"
                          >&nbsp;</label
                        >
                      </div>
                    </th>
                    <th>姓名</th>
                    <th>电话</th>
                    <th style="width: 80px">操作</th>
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
                    <td>{{ item.name }}</td>
                    <td>{{ item.phone }}</td>
                    <td>
                      <b-button
                        size="sm"
                        @click="editperson"
                        class="mr-1"
                        variant="outline-success"
                        >编 辑
                      </b-button>
                      <b-button
                        size="sm"
                        @click="deleteperson"
                        class="mr-1"
                        variant="outline-danger"
                        >删 除
                      </b-button>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>

    <b-modal
      id="modal-1"
      v-model="showmodal"
      title="添加联络人"
      title-class="text-dark font-18"
      hide-footer
    >
      <form @submit.prevent="handleSubmit">
        <div class="form-group">
          <label for="name">名字</label>
          <input
            id="name"
            v-model="customers.name"
            type="text"
            class="form-control"
            placeholder="请输入名字"
            :class="{ 'is-invalid': submitted && $v.customers.name.$error }"
          />
          <div
            v-if="submitted && !$v.customers.name.required"
            class="invalid-feedback"
          >
            Name is required
          </div>
        </div>
        <div class="form-group">
          <label for="position">电话</label>
          <input
            id="position"
            v-model="customers.phone"
            type="text"
            class="form-control"
            placeholder="请输入电话号码"
            :class="{ 'is-invalid': submitted && $v.customers.phone.$error }"
          />
          <div
            v-if="submitted && !$v.customers.phone.required"
            class="invalid-feedback"
          >
            Phone is required
          </div>
        </div>
        <div class="text-right">
          <button type="submit" class="btn btn-success">保存</button>
          <b-button class="ml-1" variant="danger" @click="hideModal"
            >取消</b-button
          >
        </div>
      </form>
    </b-modal>
    <!-- 删除 -->
    <b-modal ref="modal-delete" title="确认删除？">
      <h6>删除</h6>
      <template v-slot:modal-footer>
        <b-button variant="secondary" @click="hideModal1">取消</b-button>
        <b-button variant="primary" @click="hideModal1">
          确定
          <i class="fab fa-telegram-plane ml-1"></i>
        </b-button>
      </template>
    </b-modal>
    <!-- 添加 编辑 -->
    <b-modal
      ref="modal-add"
      size="xl"
      :title="modalTitle"
      title-class="font-18"
    >
      <form>
        <b-form-group
          id="example text"
          label-cols-sm="1"
          label-cols-lg="1"
          label="名字"
          label-for="text"
        >
          <b-form-input for="text" value="" placeholder="名字"></b-form-input>
        </b-form-group>
        <b-form-group
          id="example text"
          label-cols-sm="1"
          label-cols-lg="1"
          label="电话号码"
          label-for="number"
        >
          <b-form-input
            for="number"
            value=""
            placeholder="电话号码"
          ></b-form-input>
        </b-form-group>
      </form>
      <template v-slot:modal-footer>
        <b-button variant="secondary" @click="hideModal1">取消</b-button>
        <b-button variant="primary" @click="hideModal1">
          {{ optionBtnText }}
          <i class="fab fa-telegram-plane ml-1"></i>
        </b-button>
      </template>
    </b-modal>
  </div>
</template>
