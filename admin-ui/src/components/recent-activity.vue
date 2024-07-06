<template>
  <div class="card">
    <div class="card-body">
      <b-dropdown
        right
        toggle-class="arrow-none card-drop"
        class="float-right"
        variant="white"
      >
        <template v-slot:button-content>
          <i class="mdi mdi-dots-vertical"></i>
        </template>

        <b-dropdown-item>选项 A</b-dropdown-item>

        <b-dropdown-item>选项 B</b-dropdown-item>

        <b-dropdown-item>选项 C</b-dropdown-item>

        <b-dropdown-item>选项 E</b-dropdown-item>
      </b-dropdown>

      <h4 class="card-title mb-4">一个流程</h4>

      <div data-simplebar style="max-height: 330px;">
        <ul class="list-unstyled activity-wid">
          <li
            class="activity-list"
            v-for="(data, index) in activityData"
            :key="index"
          >
            <div class="activity-icon avatar-xs">
              <span
                class="avatar-title bg-soft-primary text-primary rounded-circle"
              >
                <i :class="`${data.icon}`"></i>
              </span>
            </div>
            <div>
              <div>
                <h5 class="font-size-13">
                  {{ data.date }}
                  <small class="text-muted">{{ data.time }}</small>
                </h5>
              </div>

              <div>
                <p class="text-muted mb-0">{{ data.title }}</p>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activityData: [
        {
          icon: "ri-edit-2-fill",
          date: "23 3月, 2023年 ",
          time: "12:07 am",
          title: "继续摆烂~~~~",
        },
        {
          icon: "ri-user-2-fill",
          date: "21 3月, 2023年 ",
          time: "08:01 pm",
          title: "故障不能解决，选择摆烂",
        },
        {
          icon: "ri-bar-chart-fill",
          date: "17 3月, 2023年 ",
          time: "05:10 pm",
          title: "发起了一次会话，处理的故障",
        },
        {
          icon: "ri-calendar-2-fill",
          date: "07 3月, 2023年",
          time: "12:47 pm",
          title: "参与网络维护",
        },
        {
          icon: "ri-edit-2-fill",
          date: "05 3月, 2023年 ",
          time: "03:09 pm",
          title: "开通管理员",
        },
        {
          icon: "ri-user-2-fill",
          date: "02 3月, 2023年 ",
          time: "12:07 am",
          title: "注册用户",
        },
      ],
    };
  },
};
</script>
