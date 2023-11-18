import { getAllPlayers, newPlayer, type Player } from '$lib/dataaccess';
import { writable, type Writable } from 'svelte/store';

export type PlayerStore = Writable<Player[] | undefined> & {
	refreshPlayers: () => Promise<void>;
	addPlayer: (playerName: Pick<Player, 'nickname' | 'photoUrl'>) => Promise<void>;
};

const createPlayerStore = (): PlayerStore => {
	const store = writable<Player[] | undefined>(undefined);
	const refreshPlayers = async () => {
		const players = await getAllPlayers();
		store.set(players);
	};
	return {
		...store,
		refreshPlayers,
		addPlayer: async (player) => {
			const id = 'intermediateId';
			store.update((players) => [
				...(players ?? []),
				{
					...player,
					id,
					seasonBeerCount: 0,
					overallBeerCount: 0,
					crownCount: 0,
					payedOverall: 0,
					royal: false
				}
			]);
			const success = await newPlayer(player);
			if (!success) {
				store.update((players) => players?.filter((p) => p.id !== id));
				return;
			}
			refreshPlayers();
		}
	};
};

export const playerStore = createPlayerStore();
