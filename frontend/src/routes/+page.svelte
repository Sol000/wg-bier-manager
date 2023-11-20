<script lang="ts">
	import ButtonLink from '$lib/components/button-link.svelte';
	import Button from '$lib/components/button.svelte';
	import Divider from '$lib/components/divider.svelte';
	import Heading from '$lib/components/heading.svelte';
	import SevenSegNum from '$lib/components/seven-seg/seven-seg-num.svelte';
	import Window from '$lib/components/window.svelte';
	import { seasonStore } from '../stores/season';
</script>

{#if $seasonStore === undefined}
	<Window>
		<div slot="content" class="no-season-content">
			<Heading>There is no season yet!</Heading>
			<ButtonLink href="/season">Create</ButtonLink>
		</div>
	</Window>
{:else}
	<Window>
		<Heading slot="header">&gt; Season~{$seasonStore.number} &lt;3</Heading>

		<div slot="content">
			{#each $seasonStore.playerList as player}
				<div class="scoreboard-entry">
					<h3 class="player-label">{player.nickname}</h3>
					<div class="score">
						<SevenSegNum height={32} input={player.seasonBeerCount} digitCount={4} />
					</div>
					<div class="operations">
						<Button onClick={() => seasonStore.increasePoints(player)}>
							<div class="btn-txt incrementer">+</div>
						</Button>
						<Button onClick={() => seasonStore.decreasePoints(player)}>
							<div class="btn-txt incrementer">-</div>
						</Button>
					</div>
				</div>
				<Divider />
			{/each}
		</div>
		<div slot="footer" class="footer">
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
	.scoreboard-entry {
		display: flex;
		align-items: center;
		gap: var(--spacing-s);
		padding: var(--spacing-s);
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
	}
	.incrementer {
		padding: 4px 8px;
	}
	.operations {
		display: flex;
		flex-direction: column;
		gap: var(--spacing-s);
	}

	.footer {
		display: flex;
		justify-content: space-between;
	}
	.no-season-content {
		padding: var(--spacing-s);
		display: flex;
		flex-direction: column;
		gap: var(--spacing-s);
	}
</style>
