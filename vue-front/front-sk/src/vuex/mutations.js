export default {
  // state의 값을 비교하여 변경
  setIsUser(state, isUser) {
    state.isUser = isUser;
  },
  setToken(state, token) {
    state.token = token;
  },
  setUserId(state, userId) {
    state.userId = userId;
  },
  setUserNickname(state, userNickname) {
    state.userNickname = userNickname;
  },
  setSingerOrWatcherStatus(state, newSingerOrWatcherStatus) {
    state.singerOrWatcherStatus = newSingerOrWatcherStatus
  }
};