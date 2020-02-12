<template>
  <div>
    <v-bottom-navigation
    fixed
    :value="activeBtn"
    dark
    color="rgba(99,99,99)"
    background-color="rgba(255,255,255,0)"
    horizontal
    elevation="0"
  >
    <v-btn>
      <span>Recents</span>
      <v-icon>mdi-history</v-icon>
    </v-btn>

    <v-btn>
      <span>Favorites</span>
      <v-icon>mdi-heart</v-icon>
    </v-btn>

    <v-btn>
      <span>Nearby</span>
      <v-icon>mdi-map-marker</v-icon>
    </v-btn>
  </v-bottom-navigation>
  </div>
</template>
 
<script>
export default {
}
</script>
 
<style scoped>
</style>