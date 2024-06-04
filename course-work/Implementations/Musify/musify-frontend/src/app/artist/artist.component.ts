import { Component, OnInit } from '@angular/core';
import { NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ArtistService } from './artist.service';
import { Artist } from '../model/artist.model';

@Component({
  selector: 'app-artist',
  standalone: true,
  imports: [NgIf, NgFor, FormsModule],
  templateUrl: './artist.component.html',
  styleUrls: ['./artist.component.css'],
  providers: [ArtistService]
})
export class ArtistComponent implements OnInit {
  artists: Artist[] = [];
  selectedArtist: Artist | null = null;
  newArtist: Artist = { id: 0, name: '', country: '', birthDate: new Date(), songIds: [], albumIds: [] };
  page: number = 0;
  size: number = 5;
  totalPages: number = 0;

  constructor(private artistService: ArtistService) {}

  ngOnInit(): void {
    this.loadArtists(this.page, this.size);
  }

  loadArtists(page: number, size: number): void {
    this.artistService.getAllArtists(page, size).subscribe(response => {
      this.artists = response.content;
      this.totalPages = response.totalPages;
    });
  }

  selectArtist(artist: Artist): void {
    this.selectedArtist = artist;
  }

  cancelEdit(): void {
    this.selectedArtist = null;
  }

  updateArtist(): void {
    if (this.selectedArtist && this.selectedArtist.id) {
      this.artistService.updateArtist(this.selectedArtist).subscribe(() => {
        this.loadArtists(this.page, this.size);
        this.selectedArtist = null;
      });
    }
  }

  deleteArtist(id: number): void {
    this.artistService.deleteArtist(id).subscribe(() => {
      this.loadArtists(this.page, this.size);
      this.selectedArtist = null;
    });
  }

  addArtist(): void {
    this.artistService.addArtist(this.newArtist).subscribe(() => {
      this.loadArtists(this.page, this.size);
      this.newArtist = { id: 0, name: '', country: '', birthDate: new Date(), songIds: [], albumIds: [] };
    });
  }

  goToPage(page: number): void {
    if (page >= 0 && page < this.totalPages) {
      this.page = page;
      this.loadArtists(this.page, this.size);
    }
  }
}
