<script lang="ts">
	export let onClick: (ev: MouseEvent) => void;
</script>

<button class="btn" on:click={onClick}>
	<slot />
</button>

<style scoped>
	.btn {
		border-width: var(--border-width);
		border-color: var(--border-bright) var(--border-dark) var(--border-dark) var(--border-bright);
		border-style: solid;
		background-color: var(--background-surface);
		color: var(--text-color);
		margin: 0;
		padding: 0;
	}
	.btn:hover {
		cursor: pointer;
		border-color: var(--border-dark) var(--border-bright) var(--border-bright) var(--border-dark);
	}
</style>
