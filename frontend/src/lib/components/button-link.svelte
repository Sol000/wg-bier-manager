<script lang="ts">
	export let href = '/';
</script>

<a {href} class="link">
	<slot />
</a>

<style scoped>
	.link {
		color: var(--text-color);
	}
</style>
