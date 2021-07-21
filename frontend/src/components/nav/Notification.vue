<template>
  <a class="notification" @click="Add">
    <span>알림</span>
    <span class="badge">3</span>
  </a>
</template>

<script>
import firebase from 'firebase';
export default {
  methods:{
    Add(){
      const db = firebase.firestore();

      db.collection("users").add({
          first: "Ada",
          last: "Lovelace",
          born: 1815
      })
      .then((docRef) => {
          console.log("Document written with ID: ", docRef.id);
      })
      .catch((error) => {
          console.error("Error adding document: ", error);
      });
    }
  }
}
</script>

<style scoped>
.notification {
  background-color: #555;
  color: white;
  text-decoration: none;
  padding: 15px 26px;
  position: relative;
  display: inline-block;
  border-radius: 2px;
}

.notification:hover {
  background: red;
}

.notification .badge {
  position: absolute;
  top: -10px;
  right: -10px;
  border-radius: 50%;
  background: red;
  color: white;
}
</style>