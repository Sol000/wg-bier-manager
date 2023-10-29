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

export const loadCurrentSeason = async (): Promise<Season> =>
	(await fetch(dataUrl`/season`)).json();
