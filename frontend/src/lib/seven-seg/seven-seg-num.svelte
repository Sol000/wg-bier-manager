<script lang="ts">
	import SevenSegDigit from './seven-seg-digit.svelte';

	export let digitColor = '#ff0000ff';
	export let digitShadow = '#80808020';

	export let height: number = 64;
	export let input: number;

	export let digitCount: number;
	export let padZeroes = false;

	let inputStr = String(input).padStart(digitCount, padZeroes ? '0' : ' ');

	const digits: (bigint | number)[] = Array.from(inputStr).map((c) =>
		c === ' ' ? 0n : parseInt(c)
	);
</script>

<div class="sev-seg-display" style="--sev-seg-height: {height}px">
	{#each digits as digit}
		<div class="digit">
			<SevenSegDigit
				digitWeight="{height / 20}px"
				digitGap="{height / 64}px"
				{digitColor}
				{digitShadow}
				input={digit}
			/>
		</div>
	{/each}
</div>

<style scoped>
	.sev-seg-display {
		height: var(--sev-seg-height);
		display: flex;
		justify-content: space-between;
		gap: calc(var(--sev-seg-height) * 0.1);
		padding: calc(var(--sev-seg-height) * 0.2);
	}
	.digit {
		width: calc(var(--sev-seg-height) * 0.5);
		height: var(--sev-seg-height);
	}
</style>
