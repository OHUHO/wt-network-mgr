import http from "../index";

//查看网络经验(通过id查询)  get
export const experienceById = (id) => {
  return http({
    url: "/api/v1/experience/search/id?id=" + id,
    method: "get",
  });
};

//查看所有网络经验  get
export const experiencefindall = () => {
  return http({
    url: "/api/v1/experience/search/all",
    method: "get",
  });
};

//增加网络经验  add
export const experienceadd = (data) => {
  return http({
    url: "/api/v1/experience/add",
    method: "post",
    data,
  });
};

//删除网络经验（根据id删除）
export const experiencedelet = (id) => {
  return http({
    url: "/api/v1/experience/delete?id=" + id,
    method: "delete",
  });
};

//编辑网络经验
export const experiencechange = (data) => {
  return http({
    url: "/api/v1/log/update",
    method: "put",
    data: data,
  });
};
