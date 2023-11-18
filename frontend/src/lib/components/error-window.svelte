<script lang="ts">
	import Window from './window.svelte';

	export let error: Error;
</script>

<Window>
	<div slot="content">
		{JSON.stringify(error)}
	</div>
</Window>

<style scoped>
</style>
