const dataUrl = (str: string) => `/api${str}`;

export interface Player {
	id: string;
	photoUrl: string;
	nickname: string;
	crownCount: number;
	seasonBeerCount: number;
	overallBeerCount: number;
	payedOverall: number;
	royal: boolean;
}

export interface Season {
	id: string;
	number: number;
	begin: Date;
	playerList: Player[];
}

export const loadCurrentSeason = async (fet = fetch): Promise<Season> => {
	const res = await fet(dataUrl(`/season`));
	if (res.status !== 200) {
		throw new Error('Could not get current season!');
	}
	const season = await res.json();
	if (season === undefined) {
		throw new Error('Could not get current season!');
	}
	return season;
};

export const increase = async (player: Player, fet = fetch): Promise<boolean> => {
	const result = await fet(dataUrl(`/player/count-up?id=${player.id}`));
	return result.status === 200;
};

export const decreasee = async (player: Player, fet = fetch): Promise<boolean> => {
	const result = await fet(dataUrl(`/player/count-down?id=${player.id}`));
	return result.status === 200;
};

export const newPlayer = async (player: Pick<Player, 'nickname' | 'photoUrl'>, fet = fetch) => {
	const result = await fet(dataUrl(`/player/new`), {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(player)
	});
	return result.status === 200;
};

export const getAllPlayers = async (fet = fetch): Promise<Player[]> =>
	(await fet(dataUrl(`/player/all`))).json();

export const nextSeason = async (
	seasonValue: number,
	tips: number,
	nextSeasonPlayerIds: string[],
	fet = fetch
): Promise<boolean> => {
	const result = await fet(dataUrl(`/season/start`), {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			seasionValue: seasonValue,
			tips,
			nextSeasonPlayerList: nextSeasonPlayerIds
		})
	});
	return result.status === 200;
};
