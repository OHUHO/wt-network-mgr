import http from "../index";

// 唐
export const getAllNotice = (data) => {
  return http({
    url: "/api/v1/admin/notice/find/all",
    method: "get",
    data,
  });
};
export const addNotices = (data) => {
  return http({
    url: "/api/v1/admin/notice/add", //对应接口文档的路路径
    method: "post", // 对应接口文档的方法
    data,
  });
};
export const findNotices = (keywords) => {
  return http({
    url: "/api/v1/admin/notice/find/keywords?keywords=" + keywords,
    method: "get",
  });
};
export const updateNotices = (id, content, userId) => {
  return http({
    url:
      "/api/v1/admin/notice/update?id=" +
      id +
      "&content=" +
      content +
      "&userId=" +
      userId,
    method: "put",
  });
};
export const deleteNotices = (id) => {
  return http({
    url: "/api/v1/admin/notice/delete/" + id,
    method: "delete",
  });
};
