<template>
  <div>
    <v-bottom-navigation
    fixed
    dark  
    background-color="rgba(255,255,255,0)"
    horizontal
    grow
    toggle
    max-height="5 0px"
  >
  <!--<p align="justify;" style="color:rgba(255,255,255,0.3); margin-left:49em;">Copyright ⓒ 2019 by 7조</p>-->
 
    <v-spacer></v-spacer>
    <v-btn max-width="5px">
      <v-icon style="color:rgba(0,0,0,0.5);">mdi-volume-high</v-icon>
    </v-btn>
    <v-btn href="#" max-width="5px">
      <v-icon style="color:rgba(0,0,0,0.5);">mdi-arrow-up-bold</v-icon>
    </v-btn>

    <!-- 간단한 연락 챗봇/ 오늘의 노래/ 멜론 인기순위 보여주기? 하고싶다 -->
  </v-bottom-navigation>
  </div>
</template>
 
<script>
import mdiVolumeHigh  from '@mdi/js'
import Vue from 'vue'
import Vuetify from 'vuetify/lib'
export default {
  name:"Footer",
  icons: {
    icon: 'mdiVolumeHigh',
  },
}
</script>
 
<style scoped>
</style>