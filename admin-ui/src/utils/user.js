export const isLogin = () => {
  var user = window.localStorage.getItem("token");
  return user != null && user != "";
};
