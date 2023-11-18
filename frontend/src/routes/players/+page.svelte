<script lang="ts">
	import Button from '$lib/components/button.svelte';
	import Divider from '$lib/components/divider.svelte';
	import Heading from '$lib/components/heading.svelte';
	import Window from '$lib/components/window.svelte';
	import ButtonLink from '$lib/components/button-link.svelte';
	import { playerStore } from '../../stores/player';

	let nameInput = '';
</script>

<Window>
	<Heading slot="header">Add Player</Heading>
	<div slot="content">
		{#each $playerStore ?? [] as player}
			<div class="player-entry"><span>{player.nickname}</span></div>
			<Divider />
		{/each}
		<div class="new-player">
			<input bind:value={nameInput} />
			<Button onClick={() => playerStore.addPlayer({ photoUrl: '', nickname: nameInput })}>
				<span class="add-player-btn">Add Player</span>
			</Button>
		</div>
	</div>
	<ButtonLink slot="footer" href="/">Home</ButtonLink>
</Window>

<style scoped>
	.new-player {
		display: flex;
		padding: var(--spacing-s);
	}
	.player-entry {
		display: flex;
		align-items: center;
		padding: var(--spacing-s);
	}
</style>
