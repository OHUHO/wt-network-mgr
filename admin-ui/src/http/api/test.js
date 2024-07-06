import http from "../index";

// 前端调用后端接口参考案例

// var data = {
//   id: 0,
//   userId: 417,
//   year: 2023,
//   month: 5,
//   day: 12,
//   title: "标题",
//   content: "内容",
//   publishDate: "2023-05-12 16:30:33",
//   isDelete: 0,
// };

// 参考案例 增加日志
export const test1 = (data) => {
  return http({
    url: "/api/v1/log/add", //对应接口文档的路路径
    method: "post", // 对应接口文档的方法
    data,
  });
};

// 参考案例 通过id查询日志
export const test2 = (id) => {
  return http({
    url: "/api/v1/log/find?id=" + id, //对应接口文档的路路径
    method: "get", // 对应接口文档的方法
  });
};

// 参考案例 删除日志
export const test3 = (id) => {
  return http({
    url: "/api/v1/log/delete?id=" + id, //对应接口文档的路路径
    method: "delete", // 对应接口文档的方法
  });
};

// 参考案例 编辑日志
export const test4 = (data) => {
  return http({
    url: "/api/v1/log/update", //对应接口文档的路路径
    method: "post", // 对应接口文档的方法
    data: data,
  });
};
