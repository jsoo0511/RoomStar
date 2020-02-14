export default {
    // state의 값을 꺼내서 확인
    // 상태관리 및 유지
    // getUserInfo
    getIsUser: state => state.isUser,
    getToken: state => state.token,
    getUserId: state => state.userId,
    getUserNickname: state => state.userNickname,
    // options(state) {
    //     return {
    //         headers: {
    //             Authorization: 'JWT ' + state.token
    //         }
    //     }
    // },
    // userId(state) {
    //     return state.token ? jwtDecode(state.token).user_id : null
    // },
    // isLoggedIn(state) {
    //     // 삼항연산자 있다면 true 없다면 false
    //     return state.token ? true : false
}