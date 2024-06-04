export interface Album {
  id: number;
  title: string;
  releaseDate: Date;
  artistId: number;
  songIds: number[];
}
