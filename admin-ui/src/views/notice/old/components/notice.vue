<template>
  <div>
      <b-button v-b-modal.modal-prevent-closing>发布</b-button>
<!--      <div class="mt-3">
        公告标题:
        <div v-if="submittedNames.length === 0">&#45;&#45;</div>
        <ul v-else class="mb-0 pl-3">
          <li v-for="name in submittedNames" :key="name">{{ name }}</li>
        </ul>
      </div>-->


      <b-modal
          id="modal-prevent-closing"
          ref="modal"
          title="编辑公告"
          @show="resetModal"
          @hidden="resetModal"
          @ok="handleOk"
      >
        <form ref="form" @submit.stop.prevent="handleSubmit">
          <b-form-group
              label="公告标题"
              label-for="name-input"
              invalid-feedback="Name is required"
              :state="nameState"
          >
            <b-form-input
                id="name-input"
                v-model="name"
                :state="nameState"
                required
            ></b-form-input>
          </b-form-group>
        </form>

        <form ref="form" @submit.stop.prevent="handleSubmit">
          <b-form-group
              label="公告内容"
              label-for="name-input"
              invalid-feedback="Name is required"
              :state="nameState"
          >
            <b-form-input
                id="name-input"
                v-model="name"
                :state="nameState"
                required
            ></b-form-input>
          </b-form-group>
        </form>


      </b-modal>




    <notice-list></notice-list>
  </div>
</template>

<script>


// import home from "@/views/user/component/home";

import noticeList from "@/views/notice/components/noticeList";

export default {
  name: "notice",
  components:{
    // home
    noticeList
  },
  data() {
    return {
      name: '',
      nameState: null,
      submittedNames: []
    }
  },
  methods: {
    checkFormValidity() {
      const valid = this.$refs.form.checkValidity()
      this.nameState = valid
      return valid
    },
    resetModal() {
      this.name = ''
      this.nameState = null
    },
    handleOk(bvModalEvent) {
      // Prevent modal from closing
      bvModalEvent.preventDefault()
      // Trigger submit handler
      this.handleSubmit()
    },
    handleSubmit() {
      // Exit when the form isn't valid
      if (!this.checkFormValidity()) {
        return
      }
      // Push the name to submitted names
      this.submittedNames.push(this.name)
      // Hide the modal manually
      this.$nextTick(() => {
        this.$bvModal.hide('modal-prevent-closing')
      })
    }
  }
}
</script>

<style scoped>
  .add{
    border: 2px solid #b1d579;
    border-radius: 5px;
    background-color: #a1e8a4;
    display: flex;
    text-align: right;
  }
</style>
