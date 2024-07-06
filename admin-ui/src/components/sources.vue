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

      <h4 class="card-title mb-3">图片</h4>

      <div>
        <div class="text-center">
          <p class="mb-2">总计</p>
          <h4>4569</h4>
          <div class="text-success">
            <i class="mdi mdi-menu-up font-size-14"></i>44.4 %
          </div>
        </div>

        <div class="table-responsive mt-4">
          <table class="table table-hover mb-0 table-centered table-nowrap">
            <tbody>
              <tr>
                <td style="width: 60px;">
                  <div class="avatar-xs">
                    <div class="avatar-title rounded-circle bg-light">
                      <img
                        src="@/assets/images/companies/img-1.png"
                        alt
                        height="20"
                      />
                    </div>
                  </div>
                </td>

                <td>
                  <h5 class="font-size-14 mb-0">合 1</h5>
                </td>
                <td>
                  <apexchart
                    class="apex-charts"
                    height="35"
                    dir="ltr"
                    :series="source1.series"
                    :options="source1.chartOptions"
                    type="line"
                    width="80"
                  ></apexchart>
                </td>
                <td>
                  <p class="text-muted mb-0">3495</p>
                </td>
              </tr>
              <tr>
                <td>
                  <div class="avatar-xs">
                    <div class="avatar-title rounded-circle bg-light">
                      <img
                        src="@/assets/images/companies/img-2.png"
                        alt
                        height="20"
                      />
                    </div>
                  </div>
                </td>
                <td>
                  <h5 class="font-size-14 mb-0">合 2</h5>
                </td>

                <td>
                  <apexchart
                    class="apex-charts"
                    height="35"
                    dir="ltr"
                    :series="source2.series"
                    :options="source2.chartOptions"
                    type="line"
                    width="80"
                  ></apexchart>
                </td>
                <td>
                  <p class="text-muted mb-0">3822</p>
                </td>
              </tr>
              <tr>
                <td>
                  <div class="avatar-xs">
                    <div class="avatar-title rounded-circle bg-light">
                      <img
                        src="@/assets/images/companies/img-3.png"
                        alt
                        height="20"
                      />
                    </div>
                  </div>
                </td>
                <td>
                  <h5 class="font-size-14 mb-0">合 3</h5>
                </td>
                <td>
                  <apexchart
                    class="apex-charts"
                    height="35"
                    dir="ltr"
                    :series="source3.series"
                    :options="source3.chartOptions"
                    type="line"
                    width="80"
                  ></apexchart>
                </td>
                <td>
                  <p class="text-muted mb-0">3498</p>
                </td>
              </tr>
            </tbody>
          </table>
        </div>

        <div class="text-center mt-4">
          <a href="#" class="btn btn-primary btn-sm">查看更多</a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      source1: {
        series: [
          {
            data: [23, 32, 27, 38, 27, 32, 27, 34, 26, 31, 28],
          },
        ],
        chartOptions: {
          chart: {
            sparkline: {
              enabled: true,
            },
          },
          stroke: {
            width: [3],
            curve: "smooth",
          },
          colors: ["#5664d2"],
          tooltip: {
            fixed: {
              enabled: false,
            },
            x: {
              show: false,
            },
            y: {
              title: {
                formatter: function() {
                  return "";
                },
              },
            },
            marker: {
              show: false,
            },
          },
        },
      },
      source2: {
        series: [
          {
            data: [24, 62, 42, 84, 63, 25, 44, 46, 54, 28, 54],
          },
        ],
        chartOptions: {
          chart: {
            sparkline: {
              enabled: true,
            },
          },
          stroke: {
            width: [3],
            curve: "smooth",
          },
          colors: ["#5664d2"],
          tooltip: {
            fixed: {
              enabled: false,
            },
            x: {
              show: false,
            },
            y: {
              title: {},
            },
            marker: {
              show: false,
            },
          },
        },
      },
      source3: {
        series: [
          {
            data: [42, 31, 42, 34, 46, 38, 44, 36, 42, 32, 54],
          },
        ],
        chartOptions: {
          chart: {
            sparkline: {
              enabled: true,
            },
          },
          stroke: {
            width: [3],
            curve: "smooth",
          },
          colors: ["#5664d2"],
          tooltip: {
            fixed: {
              enabled: false,
            },
            x: {
              show: false,
            },
            y: {
              title: {
                formatter: function() {
                  return "";
                },
              },
            },
            marker: {
              show: false,
            },
          },
        },
      },
    };
  },
};
</script>
