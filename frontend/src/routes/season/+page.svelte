<script lang="ts">
	import ButtonLink from '$lib/components/button-link.svelte';
	import Button from '$lib/components/button.svelte';
	import Heading from '$lib/components/heading.svelte';
	import Window from '$lib/components/window.svelte';
	import { playerStore } from '../../stores/player';
	import { seasonStore } from '../../stores/season';
</script>

<Window>
	<Heading slot="header">New Season</Heading>
	<div slot="content" class="content">
		<Button
			onClick={() =>
				playerStore.refreshPlayers().then(() => seasonStore.nextSeason(0, 0, $playerStore ?? []))}
		>
			Create season with all players ({$playerStore?.length})
		</Button>
	</div>
	<ButtonLink slot="footer" href="/">Home</ButtonLink>
</Window>

<style scoped>
	.content {
		padding: var(--spacing-l);
	}
</style>
