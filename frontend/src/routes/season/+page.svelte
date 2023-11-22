<script lang="ts">
	import ButtonLink from '$lib/components/button-link.svelte';
	import Button from '$lib/components/button.svelte';
	import Heading from '$lib/components/heading.svelte';
	import Window from '$lib/components/window.svelte';
	import type { Player } from '$lib/dataaccess';
	import { playerStore } from '../../stores/player';
	import { seasonStore } from '../../stores/season';
	let playerList: Player[] | any[] = [];
	let tips:number = 0;
	let costs:number = 0;
</script>

<Window>
	<Heading slot="header">New Season</Heading>
	<div slot="content" class="content">
		<table>
			<tr>
				<td>
					<label for="text-field-tips"> Costs:</label>
				</td>
				<td>
					<input type="text" id="text-field-costs" class="input-field" placeholder="(required)" bind:value={costs} />
				</td>
			</tr>
			<tr>
				<td>
					<label for="text-field-tips"> Tips:</label>
				</td>
				<td>
					<input type="text" id="text-field-tips" class="input-field" placeholder="(required)" bind:value={tips} />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<select name="player" class="select-box" multiple bind:value={playerList}>
						{#each $playerStore ?? [] as player}
							<option value={player}>{player.nickname}</option>
						{/each}
					</select>
				</td>
			</tr><tr>
				<td colspan="2">
					<Button
						onClick={() =>
							playerStore
								.refreshPlayers()
								.then(() => seasonStore.nextSeason(costs, tips, playerList ?? []))}
					>
						Create season with SELECTED players ({playerList?.length})
					</Button>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<Button
						onClick={() =>
							playerStore
								.refreshPlayers()
								.then(() => seasonStore.nextSeason(costs, tips, $playerStore ?? []))}
					>
						Create season with ALL players ({$playerStore?.length})
					</Button>
				</td>
			</tr>
		</table>
	</div>
	<div slot="footer" class="footer">
		<ButtonLink href="/">Home</ButtonLink>
		<ButtonLink href="/players">Players</ButtonLink>
	</div>
</Window>

<style scoped>
	.select-box {
		width: 100%;
	}
	td {
		width: 5rem;
	}
	.content {
		padding: var(--spacing-l);
	}
	.footer {
		display: flex;
		justify-content: space-between;
	}
</style>
