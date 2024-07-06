<template>
  <div>
    <div class="mt-4">
      <h5 class="font-size-14 mb-3">我的 :</h5>
      <div class="product-desc">
        <b-tabs class="nav-tabs-custom" content-class="border border-top-0 p-4">
          <b-tab title="我的日志" v-if="role == 'CHASER'">
            <div class="table-responsive">
              <table class="table table-nowrap mb-0">
                <tbody>
                  <tr v-for="(log, index) in logs" :key="index">
                    <th scope="row" style="width: 400px">{{ log.title }}</th>
                    <td>{{ log.publishDate }}</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </b-tab>

          <b-tab title="我的日程" v-if="role == 'SUPERVISOR'">
            <div class="table-responsive">
              <table class="table table-nowrap mb-0">
                <tbody>
                  <tr v-for="(schedule, index) in schedules" :key="index">
                    <th scope="row" style="width: 400px">
                      {{ schedule.title }}
                    </th>
                    <td>{{ schedule.publishDate }}</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </b-tab>
        </b-tabs>
      </div>
    </div>
  </div>
</template>

<script>
import { getLogByUserId, getScheduleByUserId } from "../../../http/api/sys";
export default {
  name: "my_create",
  data() {
    return {
      logs: [],
      schedules: [],
      role: "",
    };
  },
  mounted() {
    var userId = JSON.parse(window.localStorage.getItem("user")).id;
    this.findLog(userId);
    this.findSchedule(userId);
    this.role = JSON.parse(window.localStorage.getItem("user")).role;
  },
  methods: {
    findLog(userId) {
      getLogByUserId(userId).then((resp) => {
        if (resp.code == 200) {
          this.logs = resp.data;
          // console.log(resp.data);
        }
      });
    },
    findSchedule(userId) {
      getScheduleByUserId(userId).then((resp) => {
        if (resp.code == 200) {
          this.schedules = resp.data;
          // console.log(resp.data);
        }
      });
    },
  },
};
</script>

<style scoped></style>
