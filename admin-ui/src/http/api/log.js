import http from "../index";

//日志增加
export const logadd = (data) => {
  return http({
    url: "/api/v1/log/add",
    method: "post",
    data,
  });
};

//通过id查询日志
export const logfindkeywords = (keywords) => {
  return http({
    url: "/api/v1/log/find/keywords?keywords=" + keywords,
    method: "get",
  });
};

//查寻所有日志
export const logfindAll = () => {
  return http({
    url: "/api/v1/log/find/all",
    method: "get",
  });
};

//删除日志
export const logdelete = (id) => {
  return http({
    url: "/api/v1/log/delete?id=" + id,
    method: "delete",
  });
};

//编辑日志
export const logchange = (data) => {
  return http({
    url: "/api/v1/log/update",
    method: "put",
    data: data,
  });
};

export const logfindId = (id) => {
  return http({
    url: "/api/v1/log/find?id=" + id,
    method: "get",
  });
};
