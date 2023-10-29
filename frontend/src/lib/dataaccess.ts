const dataUrl = (str: TemplateStringsArray) => `http://backend:8080/${str}`;

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

export const loadCurrentSeason = async (): Promise<Season> => {
	const res = await fetch(dataUrl(`/season`));
	if (res.status !== 200) {
		throw new Error('Could not get current season!');
	}
	const season = await res.json();
	if (season === undefined) {
		throw new Error('Could not get current season!');
	}
	return season;
};

export const increase = async (player: Player): Promise<boolean> => {
	const result = await fetch(dataUrl(`/player/count-up?id=${player.id}`));
	return result.status === 200;
};

export const decreasee = async (player: Player): Promise<boolean> => {
	const result = await fetch(dataUrl(`/player/count-down?id=${player.id}`));
	return result.status === 200;
};

export const newPlayer = async (player: Omit<Player, 'id'>) => {
	const result = await fetch(dataUrl(`/player/new`), {
		method: 'POST',
		body: JSON.stringify(player)
	});
	return result.status === 200;
};

export const getAllPlayers = async (): Promise<Player[]> =>
	(await fetch(dataUrl(`/players/all`))).json();

export const nextSeason = async (
	seasonValue: number,
	tips: number,
	nextSeasonPlayerIds: string[]
): Promise<boolean> => {
	const result = await fetch(dataUrl(`/player/new`), {
		method: 'POST',
		body: JSON.stringify({
			seasonValue,
			tips,
			nextSeasonPlayerList: nextSeasonPlayerIds
		})
	});
	return result.status === 200;
};
