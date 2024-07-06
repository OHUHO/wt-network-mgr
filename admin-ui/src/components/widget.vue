<template>
  <div class="row">
    <div class="col-md-4" v-for="(data, index) in statData" :key="index">
      <div class="card">
        <div class="card-body">
          <div class="media">
            <div class="media-body overflow-hidden">
              <p class="text-truncate font-size-14 mb-2">{{ data.title }}</p>
              <h4 class="mb-0">{{ data.value }}</h4>
            </div>
            <div class="text-primary">
              <i :class="`${data.icon} font-size-24`"></i>
            </div>
          </div>
        </div>

        <div class="card-body border-top py-3">
          <div class="text-truncate">
            <span class="badge badge-soft-success font-size-11">
              <i class="mdi mdi-menu-up"></i>
              {{ data.subvalue }}
            </span>
            <span class="text-muted ml-2">较前周增量比例</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {
  getUserCount,
  getLogCount,
  getExperienceCount,
} from "../http/api/dashboard";

export default {
  data() {
    return {
      statData: [
        {
          title: "系统总人数",
          icon: "ri-stack-line",
          value: "1452",
          subvalue: " 4.4% ",
        },
        {
          title: "网络维护次数",
          icon: "ri-store-2-line",
          value: "38452",
          subvalue: " 0.4% ",
        },
        {
          title: "系统经验数",
          icon: "ri-briefcase-4-line",
          value: " 444",
          subvalue: " 0.04% ",
        },
      ],
    };
  },
  created() {
    this.getInitData();
    // console.log("this is created");
  },
  methods: {
    getInitData() {
      getUserCount().then((resp) => {
        if (resp.code == 200) {
          console.log("系统总人数：", resp.data);
          this.statData[0].value = resp.data;
        }
      });
      getLogCount().then((resp) => {
        if (resp.code == 200) {
          this.statData[1].value = resp.data;
        }
      });
      getExperienceCount().then((resp) => {
        if (resp.code == 200) {
          this.statData[2].value = resp.data;
        }
      });
    },
    // getLogCount() {},
    // getExperienceCount() {},
  },
};
</script>
