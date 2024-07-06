class RestApi {
  //用户数据示例
  users = [
    {
      id: "1",
      nickname: "张三",
      email: "Mattie@goeasy.io",
    },
    {
      id: "2",
      nickname: "李四",
      email: "Wallace@goeasy.io",
    },
    {
      id: "3",
      nickname: "王五",
      email: "jc.top@qq.com",
    },
    {
      id: "4",
      nickname: "赵六",
      email: "Juanita@goeasy.io",
    },
  ];

  findUsers() {
    return this.users;
  }

  findFriends(user) {
    return this.users.filter((v) => v.id !== user.id);
  }

  findUserById(id) {
    return this.users.find((user) => user.id === id);
  }
}

export default new RestApi();
