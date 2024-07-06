import http from "../index";

// 获取系统总人数
export const getUserCount = () => {
  return http({
    url: "/api/v1/admin/user/count",
    method: "get",
  });
};

// 获取网络维护次数
export const getLogCount = () => {
  return http({
    url: "/api/v1/log/count",
    method: "get",
  });
};

// 获取系统经验数
export const getExperienceCount = () => {
  return http({
    url: "/api/v1/experience/count",
    method: "get",
  });
};

// 获取系统维护数据分析 日程
export const getLogData = () => {
  return http({
    url: "/api/v1/schedule/analysis",
    method: "get",
  });
};

// 获取系统维护数据分析 日志
export const getScheduleData = () => {
  return http({
    url: "/api/v1/log/analysis",
    method: "get",
  });
};

// 获取日程数量
export const getScheduleCount = () => {
  return http({
    url: "/api/v1/schedule/count",
    method: "get",
  });
};

// 获取系统人员数量图数据
export const getRoleCount = () => {
  return http({
    url: "/api/v1/admin/user/role",
    method: "get",
  });
};

// 获取网络维护排行榜 周排行
export const getWeeklyRank = () => {
  return http({
    url: "/api/v1/log/weekly",
    method: "get",
  });
};

// 获取网络维护排行榜 周排行
export const getMonthlyRank = () => {
  return http({
    url: "/api/v1/log/monthly",
    method: "get",
  });
};
