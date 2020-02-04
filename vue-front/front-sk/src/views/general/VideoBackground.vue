<template>
  <div>
  <video autoplay loop muted>
    <source src = "../../../../../../../IU_SKD.mp4" type = "video/mp4">
  </video>
  </div>
</template>


<script>
  export default {
  name: 'bgVideo',
	components: {
	},
	methods: {
	},
}

// new Vue({
//   el: 'v-container',
//   components: { VideoBg }
// })
</script>
<style>
@media (min-aspect-ratio: 16/9) {
  .fullscreen-bg__video {
    width: 100%;
    height: auto;
  }
}

@media (max-aspect-ratio: 16/9) {
  .fullscreen-bg__video {
    width: auto;
    height: 100%;
  }
}
</style>