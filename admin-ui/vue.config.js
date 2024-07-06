module.exports = {
  pages: {
    index: {
      entry: "src/main.js",
    },
  },
  lintOnSave: false, //关闭语法检查

  // publicPath: "./", //  打包使用

  devServer: {
    port: 8888,
    proxy: {
      "/api": {
        target: "http://localhost:8417",
        // target: "",  //  打包使用
        ws: true,
        changeOrigin: true,
        pathRewrite: {
          "^/api": "/api",
        },
      },
    },
    overlay: {
      warnings: false,
      errors: true,
    },
  },
};
