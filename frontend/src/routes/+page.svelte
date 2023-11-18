<script lang="ts">
	import ButtonLink from '$lib/components/button-link.svelte';
	import Button from '$lib/components/button.svelte';
	import Divider from '$lib/components/divider.svelte';
	import LoadingWindow from '$lib/components/loading-window.svelte';
	import SevenSegNum from '$lib/components/seven-seg/seven-seg-num.svelte';
	import Window from '$lib/components/window.svelte';
	import { playerStore } from '../stores/player';
	import { seasonStore } from '../stores/season';
</script>

{#if $seasonStore === undefined}
	<Window>
		<div slot="content" class="no-season-content">
			<h1>There is no season yet!</h1>
			<ButtonLink href="/season">Create</ButtonLink>
		</div>
	</Window>
{:else}
	<Window>
		<div slot="header" class="season-heading">
			<h1>&gt; Season~{$seasonStore.number}</h1>
			<Button
				onClick={() => {
					playerStore.refreshPlayers();
					seasonStore.refreshSeason();
				}}>Refresh</Button
			>
		</div>
		<div slot="content">
			{#each $seasonStore.playerList as player, idx}
				<div class="scoreboard-entry">
					<span class="place">{idx + 1}. </span>
					<span class="player-label">{player.nickname}</span>
					<div class="score">
						<SevenSegNum input={player.seasonBeerCount} digitCount={4} />
					</div>
					<div class="operations">
						<Button onClick={() => playerStore.increasePoints(player)}>
							<div class="btn-txt incrementer">+</div>
						</Button>
						<Button onClick={() => playerStore.decreasePoints(player)}>
							<div class="btn-txt incrementer">-</div>
						</Button>
					</div>
				</div>
				<Divider />
			{/each}
		</div>
		<div slot="footer">
			<ButtonLink href="/players">
				<div class="btn-txt new-player-btn-content">Add a new Player</div>
			</ButtonLink>
			<ButtonLink href="/season">
				<div class="btn-txt new-player-btn-content">Start new season</div>
			</ButtonLink>
		</div>
	</Window>
{/if}

<style scoped>
	.season-heading {
		display: flex;
		justify-content: space-between;
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
	.footer {
		display: flex;
	}
	.no-season-content {
		padding: 32px;
		display: flex;
		flex-direction: column;
		gap: 32px;
	}
</style>
