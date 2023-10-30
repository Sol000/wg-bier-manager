import { getAllPlayers, increase, newPlayer, type Player } from '$lib/dataaccess';
import { writable, type Readable } from 'svelte/store';

export type PlayerStore = Readable<Player[] | undefined> & {
	refreshPlayers: () => Promise<void>;
	increasePoints: (player: Player) => Promise<void>;
	decreasePoints: (player: Player) => Promise<void>;
	addPlayer: (playerName: Pick<Player, 'nickname' | 'photoUrl'>) => Promise<void>;
};

const createBeerCountUpdater = (player: Player, delta: number) => (players: Player[] | undefined) =>
	players?.map((p) =>
		p.id !== player.id
			? p
			: {
					...p,
					overallBeerCount: p.overallBeerCount + delta,
					seasonBeerCount: p.seasonBeerCount + delta
			  }
	);

const createPlayerStore = (): PlayerStore => {
	const store = writable<Player[] | undefined>(undefined);
	const refreshPlayers = async () => {
		const players = await getAllPlayers();
		store.set(players);
	};
	return {
		refreshPlayers,
		subscribe: store.subscribe,
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
