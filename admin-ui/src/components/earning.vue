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
        <b-dropdown-item>选项 D</b-dropdown-item>
      </b-dropdown>

      <h4 class="card-title mb-4">网络维护排行榜</h4>
      <div class="text-center">
        <div class="row">
          <div class="col-sm-6">
            <div>
              <div class="mb-3">
                <apexchart
                  class="apex-charts"
                  height="60"
                  wight="60"
                  dir="ltr"
                  :series="WeeklyEarning.series"
                  :options="WeeklyEarning.chartOptions"
                ></apexchart>
              </div>
              <p class="text-muted text-truncate mb-2">周排行</p>
              <h5 class="mb-0">{{ week }}</h5>
            </div>
          </div>

          <div class="col-sm-6">
            <div class="mt-5 mt-sm-0">
              <div class="mb-3">
                <apexchart
                  class="apex-charts"
                  height="60"
                  wight="60"
                  dir="ltr"
                  :series="monthlyEarning.series"
                  :options="monthlyEarning.chartOptions"
                ></apexchart>
              </div>

              <p class="text-muted text-truncate mb-2">月排行</p>
              <h5 class="mb-0">{{ month }}</h5>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getWeeklyRank, getMonthlyRank } from "../http/api/dashboard";

export default {
  data() {
    return {
      WeeklyEarning: {
        series: [72],
        chartOptions: {
          chart: {
            type: "radialBar",
            wight: 60,
            height: 60,
            sparkline: {
              enabled: true,
            },
          },
          dataLabels: {
            enabled: false,
          },
          colors: ["#5664d2"],
          stroke: {
            lineCap: "round",
          },
          plotOptions: {
            radialBar: {
              hollow: {
                margin: 0,
                size: "70%",
              },
              track: {
                margin: 0,
              },
              dataLabels: {
                show: false,
              },
            },
          },
        },
      },
      monthlyEarning: {
        series: [94],
        chartOptions: {
          chart: {
            type: "radialBar",
            wight: 60,
            height: 60,
            sparkline: {
              enabled: true,
            },
          },
          dataLabels: {
            enabled: false,
          },
          colors: ["#1cbb8c"],
          stroke: {
            lineCap: "round",
          },
          plotOptions: {
            radialBar: {
              hollow: {
                margin: 0,
                size: "70%",
              },
              track: {
                margin: 0,
              },

              dataLabels: {
                show: false,
              },
            },
          },
        },
      },

      week: null,
      month: null,
    };
  },
  created() {
    this.getInitData();
  },

  methods: {
    getInitData() {
      getMonthlyRank().then((resp) => {
        if (resp.code == 200) {
          var temp = resp.data;
          var key = Object.keys(temp)[0];
          var value = temp[key];
          this.month = key + ": " + value;
        }
      });

      getWeeklyRank().then((resp) => {
        if (resp.code == 200) {
          var temp = resp.data;
          var key = Object.keys(temp)[0];
          var value = temp[key];
          this.week = key + ": " + value;
        }
      });
    },
  },
};
</script>
