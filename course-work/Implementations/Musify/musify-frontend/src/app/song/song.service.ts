import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Song } from '../model/song.model';

@Injectable({
  providedIn: 'root'
})
export class SongService {
  private apiUrl = 'http://localhost:8080/api/songs';
  private headers = new HttpHeaders({
    'Authorization': 'Basic ' + btoa('user:password')
  });

  constructor(private http: HttpClient) {}

  getAllSongs(page: number, size: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}?page=${page}&size=${size}`);
  }

  updateSong(song: Song): Observable<Song> {
    return this.http.put<Song>(`${this.apiUrl}/${song.id}`, song);
  }

  addSong(song: Song): Observable<Song> {
    return this.http.post<Song>(this.apiUrl, song);
  }

  deleteSong(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
