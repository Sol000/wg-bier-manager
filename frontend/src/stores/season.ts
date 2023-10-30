import { loadCurrentSeason, nextSeason, type Player, type Season } from '$lib/dataaccess';
import { writable, type Readable } from 'svelte/store';

export type SeasonStore = Readable<Season | undefined> & {
	refreshSeason: () => Promise<void>;
	nextSeason: (seasonValue: number, tips: number, nextSeasonPlayerList: Player[]) => Promise<void>;
};

const createSeasonStore = (): SeasonStore => {
	const store = writable<Season | undefined>(undefined);
	const refreshSeason = async () => {
		const season = await loadCurrentSeason();
		console.log(season);
		store.set(season);
	};
	return {
		subscribe: store.subscribe,
		refreshSeason,
		nextSeason: async (seasonValue, tips, nextSeasonPlayerList) => {
			const id = 'intermediateId';
			store.set({
				id,
				number: 0,
				begin: new Date(),
				playerList: nextSeasonPlayerList
			});
			const success = await nextSeason(
				seasonValue,
				tips,
				nextSeasonPlayerList.map((p) => p.id)
			);
			if (!success) {
				throw new Error('could not create next season');
			}
			refreshSeason();
		}
	};
};

export const seasonStore = createSeasonStore();
