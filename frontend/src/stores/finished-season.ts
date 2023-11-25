import {
  getAllFinishedSeasons,
	type FinishedSeason
} from '$lib/dataaccess';
import { writable, type Writable } from 'svelte/store';
export type FinishedSeasonStore = Writable<FinishedSeason[] | undefined> & {
	refreshFinishedSeason: () => Promise<void>;
	//addFinishedSeason: (finishedSeason: FinishedSeason) => Promise<void>;
}

const createFinishedSeasonStore = (): FinishedSeasonStore => {
	const store = writable<FinishedSeason[] | undefined>(undefined);
	const refreshFinishedSeason = async () => {
		const finishedSeason = await getAllFinishedSeasons();
		store.set(finishedSeason);
	};
	return {
		...store,
		refreshFinishedSeason
	}
};

export const finishedSeasonStore = createFinishedSeasonStore();