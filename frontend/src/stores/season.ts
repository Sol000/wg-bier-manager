import { increase, loadCurrentSeason, nextSeason, type Player, type Season } from '$lib/dataaccess';
import { writable, type Writable } from 'svelte/store';

export type SeasonStore = Writable<Season | undefined> & {
	refreshSeason: () => Promise<void>;
	increasePoints: (player: Player) => Promise<void>;
	decreasePoints: (player: Player) => Promise<void>;
	nextSeason: (seasonValue: number, tips: number, nextSeasonPlayerList: Player[]) => Promise<void>;
};

const createBeerCountUpdater =
	(player: Player, delta: number) =>
	(season: Season | undefined): Season | undefined => {
		if (!season) {
			return undefined;
		}
		const playerToUpdate = season.playerList.find((p) => p.id === player.id);
		if (playerToUpdate) {
			playerToUpdate.overallBeerCount += delta;
			playerToUpdate.seasonBeerCount += delta;
		}
		return { ...season };
	};

const createSeasonStore = (): SeasonStore => {
	const store = writable<Season | undefined>(undefined);
	const refreshSeason = async () => {
		const season = await loadCurrentSeason();
		store.set(season);
	};
	return {
		...store,
		refreshSeason,
		increasePoints: async (player) => {
			store.update(createBeerCountUpdater(player, +1));
			const success = await increase(player);
			if (!success) {
				store.update(createBeerCountUpdater(player, -1));
			}
		},
		decreasePoints: async (player) => {
			store.update(createBeerCountUpdater(player, -1));
			const success = await increase(player);
			if (!success) {
				store.update(createBeerCountUpdater(player, +1));
			}
		},
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
