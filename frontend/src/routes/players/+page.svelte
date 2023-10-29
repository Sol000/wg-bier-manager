<script lang="ts">
	import Button from '$lib/components/button.svelte';
	import Input from '$lib/components/input.svelte';
	import Window from '$lib/components/window.svelte';

	const addPlayer = () => undefined;
</script>

<Window>
	<h1 slot="header">Add Player</h1>
	<div slot="content" class="content">
		<Input />
		<Button onClick={addPlayer}>
			<span class="add-player-btn">Add Player</span>
		</Button>
	</div>
</Window>

<style scoped>
	.content {
		display: flex;
		padding: 16px;
	}
</style>
