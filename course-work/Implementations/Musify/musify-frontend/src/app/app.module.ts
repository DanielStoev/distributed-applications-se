import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app.routes';
import { AuthService } from './auth/auth.service';
import { AuthGuard } from './auth/auth.guard';
import { LoginComponent } from './auth/login.component';

@NgModule({
  declarations: [

  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    LoginComponent,
    AppComponent,
  ],
  providers: [AuthService, AuthGuard],
  bootstrap: []
})
export class AppModule { }
