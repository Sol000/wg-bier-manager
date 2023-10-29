<script>
	import SevenSegNum from '$lib/seven-seg/seven-seg-num.svelte';
	import Button from '$lib/button.svelte';
	import Window from '$lib/window.svelte';
	import Divider from '$lib/divider.svelte';
	import LoadingWindow from '$lib/loading-window.svelte';

	export let data;
</script>

<div class="container">
	<div class="content">
		{#await data.currentSeason}
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
		{/await}
	</div>
	<div />
</div>

<style scoped>
	.container {
		height: 100%;
		widows: 100%;
		display: grid;
		grid-template: 10% 80% 10% / 10% 80% 10%;
		background-color: var(--background-page);
		font-family: monospace;
	}
	.season-heading {
		color: var(--text-color);
		font-family: monospace;
		margin: 0;
	}
	.content {
		grid-row: 2;
		grid-column: 2;
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
