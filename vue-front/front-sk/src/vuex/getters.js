export default {
    // state의 값을 꺼내서 확인
    // 상태관리 및 유지
    // getUserInfo
    getIsUser: state => state.isUser,
    getToken: state => state.token,
    getUserId: state => state.userId,
    getUserNickname: state => state.userNickname,
    getSingerOrWatcherStatus: status => status.singerOrWatcherStatus,
}