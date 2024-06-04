import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Artist } from '../model/artist.model';

@Injectable({
  providedIn: 'root'
})
export class ArtistService {
  private apiUrl = 'http://localhost:8080/api/artist';
  private headers = new HttpHeaders({
    'Authorization': 'Basic ' + btoa('user:password')
  });

  constructor(private http: HttpClient) {}

  getAllArtists(page: number, size: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}?page=${page}&size=${size}`);
  }

  updateArtist(artist: Artist): Observable<Artist> {
    return this.http.put<Artist>(`${this.apiUrl}/${artist.id}`, artist);
  }

  addArtist(artist: Artist): Observable<Artist> {
    return this.http.post<Artist>(this.apiUrl, artist);
  }

  deleteArtist(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
