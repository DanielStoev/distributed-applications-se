import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth/auth.guard';
import { LoginComponent } from './auth/login.component';
import { AlbumComponent } from './album/album.component';
import { ArtistComponent } from './artist/artist.component';
import { SongComponent } from './song/song.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'albums', component: AlbumComponent, canActivate: [AuthGuard] },
  { path: 'artists', component: ArtistComponent, canActivate: [AuthGuard] },
  { path: 'songs', component: SongComponent, canActivate: [AuthGuard] },
  { path: '', redirectTo: '/albums', pathMatch: 'full' },
  { path: '**', redirectTo: '/albums' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
