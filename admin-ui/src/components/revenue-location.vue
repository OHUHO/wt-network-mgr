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

      <h4 class="card-title mb-4">就叫波浪图吧</h4>
      <apexchart
        type="area"
        height="182"
        :options="chartOptions"
        :series="series"
      ></apexchart>

      <div class="row justify-content-center">
        <div class="col-xl-5 col-md-6">
          <div class="mt-2">
            <div class="clearfix py-2">
              <h5 class="float-right font-size-16 m-0">$ 2542</h5>
              <p class="text-muted mb-0 text-truncate">对 1 :</p>
            </div>
            <div class="clearfix py-2">
              <h5 class="float-right font-size-16 m-0">$ 2245</h5>
              <p class="text-muted mb-0 text-truncate">对 2 :</p>
            </div>
          </div>
        </div>
        <div class="col-xl-5 offset-xl-1 col-md-6">
          <div class="mt-2">
            <div class="clearfix py-2">
              <h5 class="float-right font-size-16 m-0">$ 2156</h5>
              <p class="text-muted mb-0 text-truncate">对 3 :</p>
            </div>
            <div class="clearfix py-2">
              <h5 class="float-right font-size-16 m-0">$ 1845</h5>
              <p class="text-muted mb-0 text-truncate">对 4 :</p>
            </div>
          </div>
        </div>
      </div>
      <div class="text-center mt-4">
        <a href="#" class="btn btn-primary btn-sm">查看更多</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      series: [
        {
          name: "哈哈",
          data: [12, 32, 10, 15, 20, 32],
        },
        {
          name: "哈哈哈",
          data: [18, 12, 25, 25, 15, 20],
        },
        {
          name: "哈哈哈哈",
          data: [12, 30, 20, 25, 27, 25],
        },
      ],
      chartOptions: {
        chart: {
          type: "area",
          height: 196,
          stacked: true,
          toolbar: {
            show: false,
          },
        },
        colors: ["#5438DC", "#564ab1", "#5664d2"],
        dataLabels: {
          enabled: false,
        },
        stroke: {
          width: 1,
          curve: "smooth",
        },
        fill: {
          type: "gradient",
          gradient: {
            opacityFrom: 0.6,
            opacityTo: 0.8,
          },
        },
        legend: {
          show: false,
        },
        labels: {
          show: false,
        },
        xaxis: {
          labels: {
            show: false,
          },
        },
        yaxis: {
          labels: {
            show: false,
          },
        },
      },
    };
  },
};
</script>
