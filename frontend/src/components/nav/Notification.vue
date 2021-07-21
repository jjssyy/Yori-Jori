<template>
  <a class="notification">
    <span>알림</span>
    <span class="badge">3</span>
  </a>
</template>

<script>
export default {

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