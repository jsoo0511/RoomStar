import firebase from 'firebase/app'
import 'firebase/firestore'
import 'firebase/auth'
const POSTS = 'posts'
const PORTFOLIOS = 'portfolios'
// Setup Firebase
// Do change to your own firebase configuration
var firebaseConfig = {
	apiKey: "AIzaSyCPMfaJqgK_qNSVFgXBzhl3jODMstofOsc",
	authDomain: "test-ff9ab.firebaseapp.com",
	databaseURL: "https://test-ff9ab.firebaseio.com",
	projectId: "test-ff9ab",
	storageBucket: "test-ff9ab.appspot.com",
	messagingSenderId: "435505977544",
	appId: "1:435505977544:web:8e32ad96e034d858c3adfd",
	measurementId: "G-16ERRTZX3H"
};
export const app= firebase.initializeApp(firebaseConfig);
const firestore = firebase.firestore()
export default {
    getPosts() {
        const postsCollection = firestore.collection(POSTS)
        return postsCollection
                .orderBy('created_at', 'desc')
                .get()
                .then((docSnapshots) => {
                    return docSnapshots.docs.map((doc) => {  // docs를 합친다
                        let data = doc.data()
                        data.created_at = new Date(data.created_at.toDate())  // 만들어진 데이트 순
                        return data
                    })
                })
    },
    postPost(title, body) {
        return firestore.collection(POSTS).add({
            title,
            body,
            created_at: firebase.firestore.FieldValue.serverTimestamp()
        })
    },
    getInterView(){
        const interViewCollection = firestore.collection('interview')
        console.log("이건 됐어?");
        
        return interViewCollection
            .orderBy('created_at', 'desc')
            .get()
            .then((docSnapshots)=>{
                console.log(docSnapshots);
                console.log("docsnap");
                return docSnapshots.docs.map((doc)=>{
                    console.log("doc");
                    let data = doc.data()
                    return data
                })
            })
    },
    getPortfolios() {
        const postsCollection = firestore.collection(PORTFOLIOS)
        return postsCollection
                .orderBy('created_at', 'desc')
                .get()
                .then((docSnapshots) => {                   
                    return docSnapshots.docs.map((doc) => {
                        let data = doc.data()
                        data.created_at = new Date(data.created_at.toDate())
                        return data
                    })
                })
    },
    getResume() {
        const resumesCollection = firestore.collection("resumes")
        return resumesCollection
                .orderBy('created_at', 'desc')
                .get()
                .then((docSnapshots)=>{
                    return docSnapshots.docs.map((doc)=>{
                        let data = doc.data()
                        return data
                    })
                })
    },
    postPortfolio(title, body, img) {
        return firestore.collection(PORTFOLIOS).add({
            title,
            body,
            img,
            created_at: firebase.firestore.FieldValue.serverTimestamp()
        })
    },
    loginWithGoogle() {
        let provider = new firebase.auth.GoogleAuthProvider()
        return firebase.auth().signInWithPopup(provider).then(function(result) {
            let accessToken = result.credential.accessToken
            let user = result.user
            return result
        }).catch(function(error) {
            console.error('[Google Login Error]', error)
        })
    }
}