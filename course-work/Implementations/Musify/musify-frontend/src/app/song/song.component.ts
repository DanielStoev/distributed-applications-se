import { Component, OnInit } from '@angular/core';
import { NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SongService } from './song.service';
import { AlbumService } from '../album/album.service';
import { ArtistService } from '../artist/artist.service';
import { Song } from '../model/song.model';
import { Album } from '../model/album.model';
import { Artist } from '../model/artist.model';

@Component({
  selector: 'app-song',
  standalone: true,
  imports: [NgFor, NgIf, FormsModule],
  templateUrl: './song.component.html',
  styleUrls: ['./song.component.css'],
  providers: [SongService, AlbumService, ArtistService]
})
export class SongComponent implements OnInit {
  songs: Song[] = [];
  albums: Album[] = [];
  artists: Artist[] = [];
  selectedSong: Song | null = null;
  newSong: Song = { id: 0, title: '', duration: 0, genre: '', releaseDate: new Date(), artistId: 0, albumId: 0 };
  page: number = 0;
  size: number = 5;
  totalPages: number = 0;

  constructor(private songService: SongService, private albumService: AlbumService, private artistService: ArtistService) {}

  ngOnInit(): void {
    this.loadSongs();
    this.loadAlbums();
    this.loadArtists();
  }

  loadSongs(): void {
    this.songService.getAllSongs(this.page, this.size).subscribe(response => {
      if (response && Array.isArray(response.content)) {
        this.songs = response.content;
        this.totalPages = response.totalPages;
      }
    });
  }

  loadAlbums(): void {
    this.albumService.getAllAlbums(0, 100).subscribe(response => {
      if (response && Array.isArray(response.content)) {
        this.albums = response.content;
      }
    });
  }

  loadArtists(): void {
    this.artistService.getAllArtists(0, 100).subscribe(response => {
      if (response && Array.isArray(response.content)) {
        this.artists = response.content;
      }
    });
  }

  getArtistName(artistId: number): string {
    const artist = this.artists.find(a => a.id === artistId);
    return artist ? artist.name : 'Unknown Artist';
  }

  getAlbumTitle(albumId: number): string {
    const album = this.albums.find(a => a.id === albumId);
    return album ? album.title : 'Unknown Album';
  }

  selectSong(song: Song): void {
    this.selectedSong = song;
  }

  cancelEdit(): void {
    this.selectedSong = null;
  }

  updateSong(): void {
    if (this.selectedSong && this.selectedSong.id) {
      this.songService.updateSong(this.selectedSong).subscribe(() => {
        this.loadSongs();
        this.selectedSong = null;
      });
    }
  }

  deleteSong(id: number): void {
    this.songService.deleteSong(id).subscribe(() => {
      this.loadSongs();
      this.selectedSong = null;
    });
  }

  addSong(): void {
    this.songService.addSong(this.newSong).subscribe(() => {
      this.loadSongs();
      this.newSong = { id: 0, title: '', duration: 0, genre: '', releaseDate: new Date(), artistId: 0, albumId: 0 };
    });
  }

  goToPage(page: number): void {
    if (page >= 0 && page < this.totalPages) {
      this.page = page;
      this.loadSongs();
    }
  }
}
