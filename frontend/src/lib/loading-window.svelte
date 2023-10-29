<script lang="ts">
	import Window from './window.svelte';
</script>

<Window>
	<h1 slot="header">Loading...</h1>
	<div slot="content" class="loading-indicator" />
</Window>

<style scoped>
	.loading-indicator {
	}
</style>
