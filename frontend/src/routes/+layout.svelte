<script lang="ts">
</script>

<div class="container">
	<div class="content">
		<slot />
	</div>
</div>

<style scoped>
	.container {
		height: 100%;
		widows: 100%;
		display: grid;
		grid-template: 10% 80% 10% / 10% 80% 10%;
		background-color: var(--background-page);

		font-family: monospace;
		color: var(--text-color);
	}
	.content {
		grid-row: 2;
		grid-column: 2;
	}
</style>
