import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Album } from '../model/album.model';

@Injectable({
  providedIn: 'root'
})
export class AlbumService {
  private apiUrl = 'http://localhost:8080/api/albums';
  private headers = new HttpHeaders({
    'Authorization': 'Basic ' + btoa('user:password')
  });

  constructor(private http: HttpClient) {}

  getAllAlbums(page: number, size: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}?page=${page}&size=${size}`, { headers: this.headers });
  }

  updateAlbum(album: Album): Observable<Album> {
    return this.http.put<Album>(`${this.apiUrl}/${album.id}`, album, { headers: this.headers });
  }

  addAlbum(album: Album): Observable<Album> {
    return this.http.post<Album>(this.apiUrl, album, { headers: this.headers });
  }

  deleteAlbum(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, { headers: this.headers });
  }

  getNumberOfSongs(albumId: number): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/getNumberOfSongs/${albumId}`, { headers: this.headers });
  }
}
