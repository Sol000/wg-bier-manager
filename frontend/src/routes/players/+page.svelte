<script lang="ts">
	import Button from '$lib/components/button.svelte';
	import Window from '$lib/components/window.svelte';
	import { playerStore } from '../../stores/player';

	let nameInput = '';
</script>

<Window>
	<h1 slot="header">Add Player</h1>
	<div slot="content" class="content">
		<code>{JSON.stringify($playerStore)}</code>
		<div class="new-player">
			<input bind:value={nameInput} />
			<Button onClick={() => playerStore.addPlayer({ photoUrl: '', nickname: nameInput })}>
				<span class="add-player-btn">Add Player</span>
			</Button>
		</div>
	</div>
</Window>

<style scoped>
	.content {
		display: flex;
		padding: 16px;
		flex-direction: column;
	}
	.new-player {
		display: flex;
	}
</style>
