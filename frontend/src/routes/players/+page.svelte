<script lang="ts">
	import Button from '$lib/components/button.svelte';
	import Divider from '$lib/components/divider.svelte';
	import Window from '$lib/components/window.svelte';
	import { playerStore } from '../../stores/player';

	let nameInput = '';
</script>

<Window>
	<h1 slot="header">Add Player</h1>
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
</Window>

<style scoped>
	.new-player {
		display: flex;
	}
	.player-entry {
		display: flex;
		align-items: center;
	}
</style>
