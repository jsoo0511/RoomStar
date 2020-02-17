<template>
  <div style="background:grey">
    임시
    <section id="feed">
      <feed>
        <slot />
      </feed>
    </section>
  </div>
</template>

<script>
export default {
  components: {
    Feed: () => import("./MixFeed.vue")
  }
};
</script>


<style scoped>

</style>