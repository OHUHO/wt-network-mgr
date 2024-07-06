export const menuItems = [
  {
    id: 1,
    label: "menuitems.menu.text",
    isTitle: true,
  },
  {
    id: 2,
    label: "menuitems.dashboard.text",
    icon: "ri-dashboard-line",
    // badge: {
    //   variant: "success",
    //   text: "menuitems.dashboard.badge",
    // },
    link: "/",
    role: "CHASER_SUPERVISOR_ADMIN", // 一线人员 和 部门主管 和 系统管理员
  },

  {
    id: 3,
    label: "menuitems.network.text",
    icon: "ri-store-2-line",
    role: "CHASER_SUPERVISOR", // 一线人员 和 部门主管
    subItems: [
      {
        id: 31,
        label: "menuitems.network.list.schedule",
        link: "/network/schedule",
        role: "SUPERVISOR", // 部门主管
      },
      {
        id: 32,
        label: "menuitems.network.list.log",
        link: "/network/log",
        role: "CHASER", // 一线人员
      },
    ],
  },
  {
    id: 4,
    label: "menuitems.contact.text",
    icon: "ri-calendar-2-line",
    link: "/contact",
    role: "CHASER_SUPERVISOR", // 一线人员 和 部门主管
  },
  {
    id: 5,
    label: "menuitems.message.text",
    icon: "ri-chat-1-line",
    link: "/message",
    role: "CHASER_SUPERVISOR", // 一线人员 和 部门主管
  },
  {
    id: 6,
    label: "menuitems.experience.text",
    icon: "ri-chat-1-line",
    link: "/experience",
    role: "CHASER_SUPERVISOR", // 一线人员 和 部门主管
  },

  {
    id: 7,
    label: "menuitems.notice.text",
    icon: "ri-mail-send-line",
    link: "/notice",
    role: "ADMIN", // 管理员
  },
  {
    id: 8,
    label: "menuitems.user.text",
    icon: "ri-artboard-2-line",
    link: "/user",
    role: "ADMIN", // 管理员
  },
];
