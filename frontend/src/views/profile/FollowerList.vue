<template lang="">
  <div>
    <p @click="backBack">뒤로가기</p>
  </div>
</template>
<script>
export default {
  methods: {
    backBack: function() {
      history.back();
    }
  },
}
</script>
