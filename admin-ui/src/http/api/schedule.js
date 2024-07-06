import http from "../index";

//查看日程(通过id查询)  get
export const schedulefind = (id) => {
  return http({
    url: "/api/v1/schedule/find" + id,
    method: "get",
  });
};

//查看所有日程  get
export const schedulefindall = () => {
  return http({
    url: "/api/v1/schedule/find/all",
    method: "get",
  });
};

//增加日程  add
export const scheduleadd = (data) => {
  return http({
    url: "/api/v1/schedule/add",
    method: "post",
    data,
  });
};

//删除日程（根据id删除）
export const scheduledelet = (id) => {
  return http({
    url: "/api/v1/schedule/delete?id=" + id,
    method: "delete",
  });
};

//编辑日程
export const schedulechange = (data) => {
  return http({
    url: "/api/v1/schedule/update",
    method: "put",
    data: data,
  });
};
