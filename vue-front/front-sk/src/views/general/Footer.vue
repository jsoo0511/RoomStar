<template>
  <div>
    <v-bottom-navigation
    fixed
    dark  
    background-color="rgba(255,255,255,0)"
    horizontal
    grow
    narrow
    toggle
    max-height="40px"
  >
    <v-btn>
      <span>Recents</span>
      <v-icon>mdi-history</v-icon>
    </v-btn>

    <v-btn>
      <span>Favorites</span>
      <v-icon>mdi-heart</v-icon>
    </v-btn>

    <v-btn
    href="#">
      <span>Nearby</span>
      <v-icon>mdi-map-marker</v-icon>
    </v-btn>

    <!-- 간단한 연락 챗봇/ 오늘의 노래/ 멜론 인기순위 보여주기? 하고싶다 -->
  </v-bottom-navigation>
  </div>
</template>
 
<script>
export default {
  name:"Footer"
}
</script>
 
<style scoped>
</style>