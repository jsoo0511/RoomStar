export default {
  login(context, info) {
    console.log(info)
    // console.log(isUser, token, userId, userNickname)
    context.commit("setIsUser", info.one);
    context.commit("setToken", info.two);
    context.commit("setUserId", info.three);
    context.commit("setUserNickname", info.four);
  },
  logout(context) {
    context.commit("setIsUser", false);
    context.commit("setToken", null);
  },
  checkLogin(context, token) {
    context.commit("setToken", token);
  }
};
