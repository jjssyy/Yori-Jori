import firebase from 'firebase';

var firebaseConfig = {
  apiKey: "AIzaSyByJs9HYDo22JOmuffA7s6aEYsILgHwDyc",
  authDomain: "websns-5f1d6.firebaseapp.com",
  projectId: "websns-5f1d6",
  storageBucket: "websns-5f1d6.appspot.com",
  messagingSenderId: "351362417253",
  appId: "1:351362417253:web:6854606b89e8f946767c8c"
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);

const db = firebase.firestore();

const noticeAdd = (data) => {
  console.log("notice"+data.user)
  db.collection("notice"+data.user).add({
    ReqUser: data.ReqUser,
    isRead: 0,
    msg:''
  })
  .then((docRef) => {
    console.log("Document written with ID: ", docRef.id);
  })
}

const requestAdd = (data) =>{
  db.collection("request"+data.user).add({
    user: data.permitUser,
    isRead: 0,
    msg:''
  })
  .then((docRef) => {
    console.log("Document written with ID: ", docRef.id);
  })
}

const FirebaseApi = {
  noticeAdd:(data)=>noticeAdd(data),
  requestAdd:(data)=>requestAdd(data),

}
export default FirebaseApi