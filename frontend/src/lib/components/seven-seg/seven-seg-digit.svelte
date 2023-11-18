<script lang="ts">
	export let digitColor = '#ff0000ff';
	export let digitShadow = '#80808040';
	export let digitWeight = '8px';
	export let digitGap = '2px';

	export let input: bigint | number = 0b1111111n;

	const masks = [
		0b0111111n,
		0b0000110n,
		0b1011011n,
		0b1001111n,
		0b1100110n,
		0b1101101n,
		0b1111101n,
		0b0000111n,
		0b1111111n,
		0b1101111n
	];

	const bitmaskInput = (inp: bigint | number) =>
		typeof inp !== 'bigint' ? (inp = masks[Number(inp)] ?? undefined) : inp;

	const sevSegOnClass = (inp: bigint | number, bitnr: bigint) =>
		bitmaskInput(inp) & (1n << bitnr) ? 'seg-on' : 'seg-off';

	const sevSegCssVars = `
        --seg-weight: ${digitWeight};
        --seg-gap: ${digitGap};
		--seg-col-on: ${digitColor};
		--seg-col-off: ${digitShadow};
    `;
</script>

<div class="sev-seg-container" style={sevSegCssVars}>
	<div class="sev-seg seg-h seg-0 {sevSegOnClass(input, 0n)}" />
	<div class="sev-seg seg-v seg-1 {sevSegOnClass(input, 1n)}" />
	<div class="sev-seg seg-v seg-2 {sevSegOnClass(input, 2n)}" />
	<div class="sev-seg seg-h seg-3 {sevSegOnClass(input, 3n)}" />
	<div class="sev-seg seg-v seg-4 {sevSegOnClass(input, 4n)}" />
	<div class="sev-seg seg-v seg-5 {sevSegOnClass(input, 5n)}" />
	<div class="sev-seg seg-h seg-6 {sevSegOnClass(input, 6n)}" />
</div>

<style scoped>
	.sev-seg-container {
		position: relative;
		width: 100%;
		height: 100%;
	}
	.sev-seg {
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
	}

	.seg-on {
		background-color: var(--seg-col-on);
	}

	.seg-off {
		background-color: var(--seg-col-off);
	}

	.seg-h {
		clip-path: polygon(
			calc(var(--seg-gap) + var(--seg-weight)) var(--seg-weight),
			calc(var(--seg-gap) + var(--seg-weight) * 2) 0,
			calc(100% - var(--seg-gap) - var(--seg-weight) * 2) 0,
			calc(100% - var(--seg-gap) - var(--seg-weight)) var(--seg-weight),
			calc(100% - var(--seg-gap) - var(--seg-weight) * 2) calc(2 * var(--seg-weight)),
			calc(var(--seg-gap) + var(--seg-weight) * 2) calc(var(--seg-weight) * 2)
		);
	}

	.seg-v {
		clip-path: polygon(
			var(--seg-weight) calc(var(--seg-gap) + var(--seg-weight)),
			0 calc(var(--seg-gap) + var(--seg-weight) * 2),
			0 calc(50% - var(--seg-weight) - var(--seg-gap)),
			var(--seg-weight) calc(50% - var(--seg-gap)),
			calc(var(--seg-weight) * 2) calc(50% - var(--seg-weight) - var(--seg-gap)),
			calc(var(--seg-weight) * 2) calc(var(--seg-gap) + var(--seg-weight) * 2)
		);
	}

	.seg-1 {
		transform: rotateY(180deg);
	}

	.seg-4 {
		transform: rotateX(180deg);
	}

	.seg-2 {
		transform: rotateX(180deg) rotateY(180deg);
	}

	.seg-6 {
		transform: translateY(calc(50% - var(--seg-weight)));
	}

	.seg-3 {
		transform: rotateX(180deg);
	}
</style>
