<script lang="ts">
	import { npm_execpath } from '$env/static/private';
	import ButtonLink from '$lib/components/button-link.svelte';
	import Heading from '$lib/components/heading.svelte';
	import Window from '$lib/components/window.svelte';
	import { getAllFinishedSeasons } from '$lib/dataaccess';
	import { finishedSeasonStore } from '../../stores/finished-season';

	getAllFinishedSeasons().then((finishedSeasons) => finishedSeasonStore.set(finishedSeasons));
</script>

{#if $finishedSeasonStore === undefined}
	<Window>
		<div slot="content" class="no-season-content">
			<Heading>There is no invoice yet!</Heading>
			<ButtonLink href="/season">Create</ButtonLink>
		</div>
	</Window>
{:else}
	<Window>
		<div slot="content">
			{$finishedSeasonStore.at($finishedSeasonStore.length-1)?.number}
      {$finishedSeasonStore.at($finishedSeasonStore.length-1)?.begin}
      {$finishedSeasonStore.at($finishedSeasonStore.length-1)?.end}
      {$finishedSeasonStore.at($finishedSeasonStore.length-1)?.seasonValue}
      {$finishedSeasonStore.at($finishedSeasonStore.length-1)?.tips}
      {$finishedSeasonStore.at($finishedSeasonStore.length-1)?.winner}
      {$finishedSeasonStore.at($finishedSeasonStore.length-1)?.result}
      {#each $finishedSeasonStore.at($finishedSeasonStore.length-1)?.invoice.calcPerPlayer as player}
        player
      {/each}

		</div>
		<div slot="footer" class="footer">
			<ButtonLink href="/season">
				<div class="btn-txt new-player-btn-content">Back</div>
			</ButtonLink>
		</div>
	</Window>
{/if}
