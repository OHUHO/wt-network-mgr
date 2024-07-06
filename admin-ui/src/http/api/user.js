import http from "../index";

export const getAllUser = () => {
  return http({
    url: "/api/v1/admin/user/find/all",
    method: "get",
  });
};

// 唐
export const addUser = (data) => {
  return http({
    url: "/api/v1/admin/user/add", //对应接口文档的路路径
    method: "post", // 对应接口文档的方法
    data,
  });
};

export const findAllUser = () => {
  return http({
    url: "/api/v1/admin/user/find/all",
    method: "get",
  });
};

export const findUsers = (keywords) => {
  return http({
    url: "/api/v1/admin/user/find?keywords=" + keywords,
    method: "get",
  });
};

export const deleteUsers = (id) => {
  return http({
    url: "/api/v1/admin/user/delete/" + id,
    method: "delete",
  });
};

export const updateUsers = (id, password, role) => {
  return http({
    url:
      "/api/v1/admin/user/update?id=" +
      id +
      "&password=" +
      password +
      "&role=" +
      role,
    method: "put",
  });
};
