export interface Artist {
  id: number;
  name: string;
  songIds: number[];
  albumIds: number[];
  country: string;
  birthDate: Date;
}
