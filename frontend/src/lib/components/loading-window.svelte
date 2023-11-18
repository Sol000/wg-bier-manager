<script lang="ts">
	import Window from './window.svelte';
	import Heading from './heading.svelte';

	export let segmentCount: number = 15;
	const segments = Array.from(Array(segmentCount).keys());

	let counter = 0;
	let clearTimer: NodeJS.Timeout;
	$: {
		clearInterval(clearTimer);
		clearTimer = setInterval(() => (counter = (counter + 1) % segmentCount), 50);
	}

	const isSegmentActive = (segmentIdx: number, segmentCounter: number) => {
		if (segmentIdx > segmentCounter) {
			segmentIdx -= segmentCount;
		}
		return Math.abs(segmentIdx - segmentCounter) < 5;
	};
</script>

<Window>
	<Heading slot="header">Loading...</Heading>
	<div slot="content" class="loading-indicator">
		{#each segments as segment, idx}
			<div class="segment segment-{isSegmentActive(idx, counter) ? 'active' : 'inactive'}" />
		{/each}
	</div>
</Window>

<style scoped>
	.loading-indicator {
		display: flex;
		padding: var(--spacing-s);
		gap: var(--spacing-s);
	}
	.segment {
		height: 32px;
		flex: 1;
	}
	.segment-active {
		background-color: #ffffff22;
	}
	.segment-inactive {
		background-color: #00000022;
	}
</style>
