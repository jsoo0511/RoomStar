<template>
  <div>
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
    Feed: () => import("./Feed.vue")
  }
};
</script>