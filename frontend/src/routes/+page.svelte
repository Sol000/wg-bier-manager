<script>
	import SevenSegNum from '$lib/components/seven-seg/seven-seg-num.svelte';
	import Button from '$lib/components/button.svelte';
	import Window from '$lib/components/window.svelte';
	import Divider from '$lib/components/divider.svelte';
	import LoadingWindow from '$lib/components/loading-window.svelte';

	export let data;
</script>

{#await data.streamed.currentSeason}
	<LoadingWindow />
{:then currentSeason}
	<Window>
		<h1 slot="header" class="season-heading">&gt; Season~{currentSeason.number}</h1>
		<div slot="content">
			{#each currentSeason.playerList as player, idx}
				<div class="scoreboard-entry">
					<span class="place">{idx + 1}. </span>
					<span class="player-label">{player.nickname}</span>
					<div class="score">
						<SevenSegNum input={player.seasonBeerCount} digitCount={4} />
					</div>
					<div class="operations">
						<Button onClick={() => undefined}>
							<div class="btn-txt incrementer">+</div>
						</Button>
						<Button onClick={() => undefined}>
							<div class="btn-txt incrementer">-</div>
						</Button>
					</div>
				</div>
				<Divider />
			{/each}
		</div>
		<div slot="footer">
			<Button onClick={() => undefined}>
				<div class="btn-txt new-player-btn-content">Add a new Player</div>
			</Button>
		</div>
	</Window>
{:catch error}
	<Window>
		<h1 slot="header">Error Loading Current Season</h1>
		<code slot="content">{JSON.stringify(error)}</code>
	</Window>
{/await}

<style scoped>
	.season-heading {
		margin: 0;
	}

	.scoreboard-entry {
		display: flex;
		align-items: center;
		gap: 16px;
		padding: 16px;
	}
	.score {
		border-width: 4px;
		border-color: var(--border-dark) var(--border-bright) var(--border-bright) var(--border-dark);
		border-style: solid;
		background-color: var(--background-container);
	}
	.player-label {
		flex: 1;
		color: var(--text-color);
		font-size: 32px;
	}

	.place {
		color: var(--text-color-emphasized);
		font-size: 32px;
	}
	.btn-txt {
		font-size: 32px;
		margin: 4px;
	}
	.incrementer {
		width: 32px;
		height: 32px;
	}
	.operations {
		display: flex;
		flex-direction: column;
		gap: 16px;
	}
	.new-player-btn-content {
		margin: 8px 32px;
	}
</style>
