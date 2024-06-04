import { Component, OnInit } from '@angular/core';
import { NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ArtistService } from '../artist/artist.service';
import { AlbumService } from './album.service';
import { Album } from '../model/album.model';
import { Artist } from '../model/artist.model';

@Component({
  selector: 'app-album',
  standalone: true,
  imports: [NgFor, NgIf, FormsModule],
  templateUrl: './album.component.html',
  styleUrls: ['./album.component.css'],
  providers: [AlbumService, ArtistService]
})
export class AlbumComponent implements OnInit {
  albums: Album[] = [];
  artists: Artist[] = [];
  selectedAlbum: Album | null = null;
  newAlbum: Album = {
    id: 0,
    title: '',
    releaseDate: new Date(),
    artistId: 0,
    songIds: []
  };
  page: number = 0;
  size: number = 5;
  totalPages: number = 0;
  songCounts: { [albumId: number]: number } = {};

  constructor(private albumService: AlbumService, private artistService: ArtistService) {}

  ngOnInit(): void {
    this.loadAlbums();
    this.loadArtists(this.page, this.size);
  }

  loadAlbums(): void {
    this.albumService.getAllAlbums(this.page, this.size).subscribe(response => {
      if (response && Array.isArray(response.content)) {
        this.albums = response.content;
        this.totalPages = response.totalPages || 0;
        this.albums.forEach(album => this.loadSongCount(album.id));
      }
    });
  }

  loadArtists(page: number, size: number): void {
    this.artistService.getAllArtists(page, size).subscribe(response => {
      if (response && Array.isArray(response.content)) {
        this.artists = response.content;
      }
    });
  }

  getArtistName(artistId: number): string {
    const artist = this.artists.find(a => a.id === artistId);
    return artist ? artist.name : 'Unknown Artist';
  }

  loadSongCount(albumId: number): void {
    this.albumService.getNumberOfSongs(albumId).subscribe(count => {
      this.songCounts[albumId] = count;
    });
  }

  selectAlbum(album: Album): void {
    this.selectedAlbum = album;
  }

  cancelEdit(): void {
    this.selectedAlbum = null;
  }

  updateAlbum(): void {
    if (this.selectedAlbum && this.selectedAlbum.id) {
      this.albumService.updateAlbum(this.selectedAlbum).subscribe(() => {
        this.loadAlbums();
        this.selectedAlbum = null;
      });
    }
  }

  deleteAlbum(id: number): void {
    this.albumService.deleteAlbum(id).subscribe(() => {
      this.loadAlbums();
      this.selectedAlbum = null;
    });
  }

  addAlbum(): void {
    this.albumService.addAlbum(this.newAlbum).subscribe(() => {
      this.loadAlbums();
      this.newAlbum = { id: 0, title: '', releaseDate: new Date(), artistId: 0, songIds: [] };
    });
  }

  goToPage(page: number): void {
    if (page >= 0 && page < this.totalPages) {
      this.page = page;
      this.loadAlbums();
    }
  }
}
