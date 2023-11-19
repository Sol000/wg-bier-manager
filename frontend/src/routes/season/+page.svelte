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
	<div slot="footer" class="footer">
		<ButtonLink href="/">Home</ButtonLink>
		<ButtonLink href="/players">Players</ButtonLink>
	</div>
</Window>

<style scoped>
	.content {
		padding: var(--spacing-l);
	}
	.footer {
		display: flex;
		justify-content: space-between;
	}
</style>
