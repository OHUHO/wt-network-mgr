import http from "../index";

export const register = (data) => {
  return http({
    url: "/api/v1/auth/register",
    method: "post",
    data,
  });
};

export const login = (data) => {
  return http({
    url: "/api/v1/auth/authenticate",
    method: "post",
    data,
  });
};

export const findUserInfo = (email) => {
  return http({
    url: "/api/v1/admin/user/find/email?email=" + email,
    methods: "get",
  });
};
