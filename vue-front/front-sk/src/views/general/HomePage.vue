<template>
	<div class="fullscreen-bg">
	<VideoBg />
	<div class="text-center" id="map_height">
    <v-sheet color="rgba(0,0,0,0)" fixed dark>ROOM Star★</v-sheet>
	</div>
</div>

</template>
<script>
// import ImgBanner from '../components/ImgBanner'
// import PortfolioList from '../components/PortfolioList'
// import PostList from '../components/PostList'
// import RepositoryList from '../components/RepositoryList'
import VideoBg from './VideoBackground.vue'

export default {
	name: 'HomePage',
	components: {
		// ImgBanner,
		// PortfolioList,
		// PostList,
		// RepositoryList\\
		VideoBg
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
#map_height {
	margin-top: 100px;
}
</style>