<script lang="ts">
	export let level = 1;
</script>

{#if level === 1}
	<h1 class="heading"><slot /></h1>
{/if}
{#if level === 2}
	<h2 class="heading"><slot /></h2>
{/if}
{#if level === 3}
	<h3 class="heading"><slot /></h3>
{/if}
{#if level === 4}
	<h4 class="heading"><slot /></h4>
{/if}

<style scoped>
	.heading {
		margin: 0;
	}
	h1 {
		font-size: 20px;
	}
	h2 {
		font-size: 16px;
	}
	h3 {
		font-size: 12px;
	}
</style>
