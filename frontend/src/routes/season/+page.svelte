<script lang="ts">
	import Button from '$lib/components/button.svelte';
	import Window from '$lib/components/window.svelte';
	import { playerStore } from '../../stores/player';
	import { seasonStore } from '../../stores/season';
</script>

<Window>
	<h1 slot="header">New Season</h1>
	<div slot="content" class="content">
		<Button
			onClick={() =>
				playerStore.refreshPlayers().then(() => seasonStore.nextSeason(0, 0, $playerStore ?? []))}
		>
			Create season with all players ({$playerStore?.length})
		</Button>
	</div>
</Window>

<style scoped>
	.content {
		padding: 32px;
	}
</style>
