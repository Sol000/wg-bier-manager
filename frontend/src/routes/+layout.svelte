<script lang="ts">
	import ErrorWindow from '$lib/components/error-window.svelte';
	import LoadingWindow from '$lib/components/loading-window.svelte';
	import { getAllPlayers, loadCurrentSeason } from '$lib/dataaccess';
	import { playerStore } from '../stores/player';
	import { seasonStore } from '../stores/season';

	const dataReady = Promise.all([
		loadCurrentSeason().then(seasonStore.set),
		getAllPlayers().then(playerStore.set)
	]);
</script>

<div class="container">
	<div class="content">
		{#await dataReady}
			<LoadingWindow />
		{:then}
			<slot />
		{:catch error}
			<ErrorWindow {error} />
		{/await}
	</div>
</div>

<style scoped>
	.container {
		height: 100%;
		widows: 100%;
		display: grid;
		grid-template: 10% 80% 10% / 10% 80% 10%;
		background-color: var(--background-page);

		font-family: monospace;
		color: var(--text-color);
	}
	.content {
		grid-row: 2;
		grid-column: 2;
	}
</style>
