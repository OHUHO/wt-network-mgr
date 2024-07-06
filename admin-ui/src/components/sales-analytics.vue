<template>
  <div class="card">
    <div class="card-body">
      <div class="float-right">
        <select class="custom-select custom-select-sm">
          <option value="1">二月</option>
          <option value="2">三月</option>
          <option value="3" selected>四月</option>
          <option value="4">五月</option>
        </select>
      </div>
      <h4 class="card-title mb-4">系统人员数量图</h4>

      <div id="donut-chart" class="apex-charts"></div>
      <apexchart
        v-if="series != null"
        class="apex-charts"
        height="230"
        dir="ltr"
        :series="series"
        :options="chartOptions"
      ></apexchart>
      <div class="row" v-if="rate != null">
        <div class="col-4">
          <div class="text-center mt-4">
            <p class="mb-2 text-truncate">
              <i class="mdi mdi-circle text-primary font-size-10 mr-1"></i>
              一线人员
            </p>
            <h5>{{ rate[0] }} %</h5>
          </div>
        </div>
        <div class="col-4">
          <div class="text-center mt-4">
            <p class="mb-2 text-truncate">
              <i class="mdi mdi-circle text-success font-size-10 mr-1"></i>
              部门主管
            </p>
            <h5>{{ rate[1] }} %</h5>
          </div>
        </div>
        <div class="col-4">
          <div class="text-center mt-4">
            <p class="mb-2 text-truncate">
              <i class="mdi mdi-circle text-warning font-size-10 mr-1"></i>
              系统管理员
            </p>
            <h5>{{ rate[2] }} %</h5>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getRoleCount } from "../http/api/dashboard";

export default {
  data() {
    return {
      series: null,
      rate: null,
      chartOptions: {
        chart: {
          height: 230,
          type: "donut",
        },
        labels: ["一线人员", "部门主管", "系统管理员"],
        plotOptions: {
          pie: {
            donut: {
              size: "75%",
            },
          },
        },
        dataLabels: {
          enabled: false,
        },
        legend: {
          show: false,
        },
        colors: ["#5664d2", "#1cbb8c", "#eeb902"],
      },
    };
  },
  created() {
    this.getInitData();
  },
  methods: {
    getInitData() {
      getRoleCount().then((resp) => {
        if (resp.code == 200) {
          const res = resp.data;
          const series = [];
          series.push(res.CHASER);
          series.push(res.SUPERVISOR);
          series.push(res.ADMIN);
          this.series = series;

          var count = res.CHASER + res.SUPERVISOR + res.ADMIN;
          const rate = [];
          rate.push(Math.round((res.CHASER / count) * 10000) / 100);
          rate.push(Math.round((res.SUPERVISOR / count) * 10000) / 100);
          rate.push(Math.round((res.ADMIN / count) * 10000) / 100);
          this.rate = rate;
        }
      });
      console.log(this.rate);
    },
  },
};
</script>
