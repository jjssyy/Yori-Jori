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
const storage = firebase.storage()

const noticeAdd = (data) => {
  const now = Date.now()
  console.log("notice"+data.user)
  db.collection("notice"+data.user).doc(now.toString()).set({
    date:now,
    ReqUser: data.ReqUser,
    isRead: 0,
    msg:'',
  })
  .then((docRef) => {
    console.log("Document written");
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

const upLoad = (file,callback) => {
  var storageRef = storage.ref()
  var path = storageRef.child('image/'+file.name)
  var upload = path.put(file)

  upload.on('state_changed',
  null,
  (e)=>{
    console.log(e)
  },
  ()=>{
    upload.snapshot.ref.getDownloadURL().then((url)=>{
      console.log(`업로드된 경로: ${url}`)
      callback(url)
    })
  })
}
const upLoadProfile = (file,callback) => {
  var storageRef = storage.ref()
  var path = storageRef.child('profile/'+`${file.userId}/`+file.img.name)
  var upload = path.put(file.img)

  upload.on('state_changed',
  null,
  (e)=>{
    console.log(e)
  },
  ()=>{
    upload.snapshot.ref.getDownloadURL().then((url)=>{
      console.log(`업로드된 경로: ${url}`)
      callback(url)
    })
  })
}

const FirebaseApi = {
  noticeAdd:(data)=>noticeAdd(data),
  requestAdd:(data)=>requestAdd(data),
  upLoad:(file,callback)=>upLoad(file,callback),
  upLoadProfile:(file,callback)=>upLoadProfile(file,callback),

}
export default FirebaseApi