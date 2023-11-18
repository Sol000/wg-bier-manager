<script lang="ts">
</script>

<div class="window">
	<slot name="header" />
	<div class="content">
		<slot name="content" />
	</div>
	<slot name="footer" />
</div>

<style scoped>
	.window {
		display: flex;
		flex-direction: column;
		align-items: stretch;

		gap: var(--spacing-l);
		padding: var(--spacing-l);

		border-width: var(--border-width);
		border-color: var(--border-bright) var(--border-dark) var(--border-dark) var(--border-bright);
		border-style: solid;
		background-color: var(--background-surface);
	}
	.content {
		border-width: var(--border-width);
		border-color: var(--border-dark) var(--border-bright) var(--border-bright) var(--border-dark);
		border-style: solid;
		background-color: var(--background-surface);
		flex: 1;
	}
</style>
