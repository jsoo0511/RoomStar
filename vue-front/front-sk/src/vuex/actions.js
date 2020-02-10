export default {
  login(context, info) {
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
  },
  changeToDefault(context, info) {
    context.commit("setSingerOrWatcherStatus", 0);
  },
  changeToSinger(context, info) {
    context.commit("setSingerOrWatcherStatus", 1);
  },
  changeToWatcher(context, info) {
    console.log('###########', info)
    context.commit("setSingerOrWatcherStatus", 2);
  },
};
