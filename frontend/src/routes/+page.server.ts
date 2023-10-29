import { loadCurrentSeason } from '$lib/dataaccess';
import type { PageServerLoad } from './$types';

export const load: PageServerLoad = () => {
	return {
		streamed: {
			currentSeason: loadCurrentSeason()
		}
	};
};
