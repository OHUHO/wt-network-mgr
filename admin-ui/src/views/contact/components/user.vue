<template>
  <div>
    <div class="row">
      <div class="col-lg-12">
        <div class="card">
          <div class="card-body">
            <div class="row">
              <div class="col-md-9">
                <a
                  href="javascript:void(0);"
                  class="btn btn-success mb-2"
                  @click="addPerson"
                >
                  <i class="mdi mdi-plus mr-2"></i> 添加联络人
                </a>
              </div>
              <div class="col-md-3">
                <div class="search-box mr-2">
                  <div class="position-relative">
                    <input
                      id="filter-input"
                      v-model="keywords"
                      @keyup.enter="getContact"
                      type="search"
                      class="form-control"
                      placeholder="输入关键词..."
                    />
                    <i class="mdi mdi-magnify search-icon"></i>
                  </div>
                </div>
              </div>
            </div>
            <div>
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
                      <th>ID</th>
                      <th>姓名</th>
                      <th>性别</th>
                      <th>电话</th>
                      <th>邮箱</th>
                      <th>QQ</th>
                      <th>微信</th>
                      <th>工作单位</th>
                      <th>住址</th>
                      <th>邮编</th>
                      <th>操作</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="(item, index) in customers" :key="index">
                      <td>{{ item.id }}</td>
                      <td>{{ item.name }}</td>
                      <td style="width: 54px">{{ handleSex(item.sex) }}</td>
                      <td>{{ item.phoneNumber }}</td>
                      <td>{{ item.email }}</td>
                      <td>{{ item.qq }}</td>
                      <td>{{ item.wechat }}</td>
                      <td>{{ item.employer }}</td>
                      <td>{{ item.address }}</td>
                      <td>{{ item.postcode }}</td>
                      <td>
                        <b-button
                          size="sm"
                          @click="editperson(item)"
                          class="mr-1"
                          variant="outline-success"
                        >
                          编 辑</b-button
                        >
                        <b-button
                          size="sm"
                          @click="deleteperson(item)"
                          class="mr-1"
                          variant="outline-danger"
                        >
                          删 除</b-button
                        >
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

    <b-modal
      id="modal-1"
      ref="modal-add-edit"
      :title="modalTitle"
      title-class="text-dark font-18"
      hide-footer
    >
      <!--  @submit.prevent="handleSubmit" -->
      <form>
        <div class="form-group">
          <label for="name">名字</label>
          <input
            id="name"
            v-model="modalCustomers.name"
            type="text"
            class="form-control"
            placeholder="请输入名字"
          />
        </div>
        <div class="form-group">
          <label for="sex">性别</label>

          <b-form-select
            id="sex"
            class="form-control"
            v-model="modalCustomers.sex"
            :options="sexoptions"
            size="sm"
          >
          </b-form-select>
        </div>
        <div class="form-group">
          <label for="phoneNumber">电话</label>
          <input
            id="phoneNumber"
            v-model="modalCustomers.phoneNumber"
            type="text"
            class="form-control"
            placeholder="请输入电话号码"
          />
        </div>
        <div class="form-group">
          <label for="email">邮箱</label>
          <input
            id="email"
            v-model="modalCustomers.email"
            type="text"
            class="form-control"
            placeholder="请输入邮箱"
          />
        </div>
        <div class="form-group">
          <label for="qq">QQ</label>
          <input
            id="qq"
            v-model="modalCustomers.qq"
            type="text"
            class="form-control"
            placeholder="请输入QQ"
          />
        </div>
        <div class="form-group">
          <label for="wechat">微信</label>
          <input
            id="wechat"
            v-model="modalCustomers.wechat"
            type="text"
            class="form-control"
            placeholder="请输入微信"
          />
        </div>
        <div class="form-group">
          <label for="employer">联络人工作单位</label>
          <input
            id="employer"
            v-model="modalCustomers.employer"
            type="text"
            class="form-control"
            placeholder="请输入联络人工作单位"
          />
        </div>
        <div class="form-group">
          <label for="address">联络人住址</label>
          <input
            id="address"
            v-model="modalCustomers.address"
            type="text"
            class="form-control"
            placeholder="请输入联络人住址"
          />
        </div>
        <div class="form-group">
          <label for="postcode">联络人邮编</label>
          <input
            id="postcode"
            v-model="modalCustomers.postcode"
            type="text"
            class="form-control"
            placeholder="请输入联络人邮编"
          />
        </div>

        <div class="text-right">
          <b-button
            type="submit"
            class="btn btn-success"
            @click="aditEditContact"
            >{{ optionBtnText }}</b-button
          >
          <b-button class="ml-1" variant="danger" @click="hideModal"
            >取消</b-button
          >
        </div>
      </form>
    </b-modal>
    <!-- 删除 -->
    <b-modal ref="modal-delete" title="确认删除？">
      <h6 v-if="rawData != null">{{ this.rawData.name }}</h6>
      <template v-slot:modal-footer>
        <b-button variant="secondary" @click="hideModal">取消</b-button>
        <b-button variant="primary" @click="hideModalDelete">
          确定
          <i class="fab fa-telegram-plane ml-1"></i>
        </b-button>
      </template>
    </b-modal>
  </div>
</template>

<script>
import {
  contactadds,
  contact_all,
  findContact,
  contact_add_edit,
  contact_delete,
} from "../../../http/api/contact";

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
      customers: null,
      submitted: false,
      showmodal: false,
      modalTitle: "",
      optionBtnText: "",

      modalCustomers: {
        name: "",
        sex: null,
        phoneNumber: "",
        email: "",
        qq: "",
        wechat: "",
        employer: "",
        address: "",
        postcode: "",
      },

      //性别选择
      sexoptions: [
        { value: null, text: "请选择你的性别", disabled: true },
        { value: "0", text: "未知" },
        { value: "1", text: "男" },
        { value: "2", text: "女" },
      ],
      list: {},
      rawData: null,
      editContact: null,
      keywords: null,
    };
  },

  //在页面启动的时候去获取数据,只在页面渲染成功的时候执行一次
  mounted() {
    this.getAllContact();
  },
  computed: {
    handleSex: () => {
      return (sex) => {
        if (sex == 0) return "未知";
        if (sex == 1) return "男";
        if (sex == 2) return "女";
      };
    },
  },
  methods: {
    // eslint-disable-next-line no-unused-vars
    hideModal() {
      if (this.optionBtnText != "添加") {
        this.modalCustomers = {
          name: "",
          sex: null,
          phoneNumber: "",
          email: "",
          qq: "",
          wechat: "",
          employer: "",
          address: "",
          postcode: "",
        };
      }
      this.$refs["modal-add-edit"].hide();
      this.$refs["modal-delete"].hide();
    },

    //查询全部
    getAllContact() {
      contact_all().then((resp) => {
        if (resp.code == 200) {
          this.customers = resp.data;
        }
      });
    },
    //通过id查询
    getContact() {
      findContact(this.keywords.trim()).then((resp) => {
        if (resp.code == 200) {
          console.log(resp.data);
          this.customers = resp.data;
        } else {
          console.log("没有找到此用户");
        }
      });
    },

    //编辑
    contact_add() {
      this.$refs["model-add-edit"].show();
      this.modalTitle = "添加用户";
      this.optionBtnText = "添加";
    },

    aditEditContact() {
      if (this.optionBtnText == "添加") {
        const data = {
          id: 0,
          userId: 0,
          name: this.modalCustomers.name,
          sex: this.modalCustomers.sex,
          phoneNumber: this.modalCustomers.phoneNumber,
          email: this.modalCustomers.email,
          qq: this.modalCustomers.qq,
          wechat: this.modalCustomers.wechat,
          employer: this.modalCustomers.employer,
          address: this.modalCustomers.address,
          postcode: this.modalCustomers.postcode,
          publishData: "",
          isDelete: 0,
        };
        console.log("da", data);
        contactadds(data).then((resp) => {
          if (resp.code == 200) {
            console.log(resp.data);
            console.log("添加成功");
            this.hideModal();
            this.getAllContact();
          } else {
            console.log("添加失败！");
          }
        });
      } else {
        //编辑
        const data = {
          id: this.modalCustomers.id,
          userId: this.modalCustomers.userId,
          name: this.modalCustomers.name,
          sex: this.modalCustomers.sex,
          phoneNumber: this.modalCustomers.phoneNumber,
          email: this.modalCustomers.email,
          qq: this.modalCustomers.qq,
          wechat: this.modalCustomers.wechat,
          employer: this.modalCustomers.employer,
          address: this.modalCustomers.address,
          postcode: this.modalCustomers.postcode,
          publishData: this.modalCustomers.publishData,
          isDelete: 0,
        };
        console.log("需要编辑的数据", data);
        contact_add_edit(data).then((resp) => {
          if (resp.code == 200) {
            console.log("编辑成功", resp.data);
            this.hideModal();
            this.getAllContact();
          }
        });
      }

      this.modalCustomers = {
        name: "",
        sex: null,
        phoneNumber: "",
        email: "",
        qq: "",
        wechat: "",
        employer: "",
        address: "",
        postcode: "",
      };
    },

    deleteperson(rawDatas) {
      this.rawData = rawDatas;
      this.$refs["modal-delete"].show();
    },
    // 删除
    hideModalDelete() {
      const id = this.rawData.id;
      console.log(id);
      contact_delete(id).then((resp) => {
        if (resp.code == 200) {
          console.log("删除成功");
          this.$refs["modal-delete"].hide();
          this.getAllContact();
        }
      });
    },

    addPerson() {
      this.$refs["modal-add-edit"].show();
      this.modalTitle = "添加联络人";
      this.optionBtnText = "添加";
    },
    editperson(contact) {
      this.$refs["modal-add-edit"].show();
      this.modalTitle = "编辑联络人";
      this.optionBtnText = "编辑";
      this.modalCustomers = contact;
    },
  },
};
</script>
