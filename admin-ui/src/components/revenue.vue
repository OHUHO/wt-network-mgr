<template>
  <div class="card">
    <div class="card-body">
      <div class="float-right d-none d-md-inline-block">
        <div class="btn-group mb-2">
          <button type="button" class="btn btn-sm btn-light">每天</button>
          <button type="button" class="btn btn-sm btn-light active">
            每周
          </button>
          <button type="button" class="btn btn-sm btn-light">每月</button>
        </div>
      </div>
      <h4 class="card-title mb-4">系统维护数据分析</h4>
      <div v-if="series[0].data.length > 0 && series[1].data.length > 0">
        <div id="line-column-chart" class="apex-charts" dir="ltr"></div>
        <apexchart
          class="apex-charts"
          height="280"
          dir="ltr"
          :series="series"
          :options="chartOptions"
        ></apexchart>
      </div>
    </div>

    <div class="card-body border-top text-center">
      <div class="row">
        <div class="col-sm-4">
          <div class="d-inline-flex">
            <h5 class="mr-2">{{ logNumber + scheduleNumber }}</h5>
            <div class="text-success">
              <i class="mdi mdi-menu-up font-size-14"></i>24.2 %
            </div>
          </div>
          <p class="text-muted text-truncate mb-0">本月</p>
        </div>

        <div class="col-sm-4">
          <div class="mt-4 mt-sm-0">
            <p class="mb-2 text-muted text-truncate">
              <i class="mdi mdi-circle text-primary font-size-10 mr-1"></i> 日程
            </p>
            <div class="d-inline-flex">
              <h5 class="mb-0 mr-2">{{ scheduleNumber }}</h5>
              <div class="text-success">
                <i class="mdi mdi-menu-up font-size-14"></i>28.4 %
              </div>
            </div>
          </div>
        </div>
        <div class="col-sm-4">
          <div class="mt-4 mt-sm-0">
            <p class="mb-2 text-muted text-truncate">
              <i class="mdi mdi-circle text-success font-size-10 mr-1"></i>
              日志
            </p>
            <div class="d-inline-flex">
              <h5 class="mb-0">{{ logNumber }}</h5>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {
  getLogData,
  getScheduleData,
  getLogCount,
  getScheduleCount,
} from "../http/api/dashboard";

export default {
  data() {
    return {
      series: [
        {
          name: "日程",
          type: "column",
          // data: [23, 42, 35, 27, 43, 22, 17, 31, 45, 23, 34, 23],
          data: [],
        },
        {
          name: "日志",
          type: "line",
          // data: [23, 32, 27, 38, 27, 32, 27, 38, 23, 45, 23, 23],
          data: [],
        },
      ],
      chartOptions: {
        chart: {
          height: 280,
          type: "line",
          toolbar: {
            show: false,
          },
        },
        stroke: {
          width: [0, 3],
          curve: "smooth",
        },
        plotOptions: {
          bar: {
            horizontal: false,
            columnWidth: "20%",
          },
        },
        dataLabels: {
          enabled: false,
        },
        legend: {
          show: false,
        },
        colors: ["#5664d2", "#1cbb8c"],
        labels: [],
      },
      scheduleNumber: 0,
      logNumber: 0,
    };
  },
  created() {
    this.getInitData();
    this.chartOptions.labels = this.getRecentMonth(12);
  },
  // mounted() {
  //   this.getInitData();
  // },
  methods: {
    getRecentMonth(n) {
      let nowDate = new Date();
      let array = [];
      let year = nowDate.getFullYear();
      let ch = [
        "一月",
        "二月",
        "三月",
        "四月",
        "五月",
        "六月",
        "七月",
        "八月",
        "九月",
        "十月",
        "十一月",
        "十二月",
      ];
      let mon = nowDate.getMonth() + 1;
      for (let i = 0; i < n; i++) {
        mon = mon - 1;
        if (mon < 0) {
          year = year - 1;
          mon = mon + 12;
        }
        array[i] = ch[mon];
      }
      return array.reverse();
    },

    getInitData() {
      getLogData().then((resp) => {
        if (resp.code == 200) {
          this.series[0].data = resp.data;
        }
      });

      getScheduleData().then((resp) => {
        if (resp.code == 200) {
          this.series[1].data = resp.data;
        }
      });

      getLogCount().then((resp) => {
        if (resp.code == 200) {
          this.logNumber = resp.data;
        }
      });

      getScheduleCount().then((resp) => {
        if (resp.code == 200) {
          this.scheduleNumber = resp.data;
        }
      });
    },
  },
};
</script>
