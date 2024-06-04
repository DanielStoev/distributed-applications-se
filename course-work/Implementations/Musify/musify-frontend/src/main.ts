import {  importProvidersFrom } from '@angular/core';
import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app/app.component';
import { routes } from './app/app.routes';
import { AuthService } from './app/auth/auth.service';
import { AuthGuard } from './app/auth/auth.guard';

bootstrapApplication(AppComponent, {
  providers: [
    importProvidersFrom(HttpClientModule),
    importProvidersFrom(FormsModule),
    provideRouter(routes),
    AuthService,
    AuthGuard,
  ]
}).catch(err => console.error(err));
