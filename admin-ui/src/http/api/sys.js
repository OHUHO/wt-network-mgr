import http from "../index";

// 更新用户昵称
export const updateNickname = (id, nickname) => {
  return http({
    url: "/api/v1/admin/user/update/" + id + "/" + nickname,
    method: "put",
  });
};

export const updatePassword = (id, oldPassword, password) => {
  return http({
    url: "/api/v1/admin/user/update/" + id + "/" + oldPassword + "/" + password,
    method: "put",
  });
};

export const getLogByUserId = (userId) => {
  return http({
    url: "/api/v1/log/find/" + userId,
    method: "get",
  });
};

export const getScheduleByUserId = (userId) => {
  return http({
    url: "/api/v1/schedule/find/" + userId,
    method: "get",
  });
};
