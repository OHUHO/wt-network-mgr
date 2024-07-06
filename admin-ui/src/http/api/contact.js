import http from "../index";

export const contact_all = () => {
  return http({
    url: "/api/v1/contact/find/all",
    method: "get",
  });
};

//添加
export const contactadds = (data) => {
  return http({
    url: "/api/v1/contact/add",
    method: "post",
    data,
  });
};
//编辑
export const contact_add_edit = (data) => {
  console.log("this is data11", data);
  return http({
    url: "/api/v1/contact/update",
    method: "put",
    data: data,
  });
};

export const findContact = (keywords) => {
  return http({
    url: "/api/v1/contact/find/keywords?keywords=" + keywords,
    method: "get",
    data: keywords,
  });
};

//删除
export const contact_delete = (id) => {
  return http({
    url: "/api/v1/contact/delete?id=" + id,
    method: "delete",
  });
};
