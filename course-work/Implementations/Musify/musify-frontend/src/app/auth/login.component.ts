import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  template: `
    <div class="container">
      <h2>Login</h2>
      <form (ngSubmit)="login()">
        <div class="form-group">
          <label for="username">Username:</label>
          <input type="text" id="username" [(ngModel)]="username" name="username" class="form-control" required />
        </div>
        <div class="form-group">
          <label for="password">Password:</label>
          <input type="password" id="password" [(ngModel)]="password" name="password" class="form-control" required />
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
      </form>
    </div>
  `,
  standalone: true,
  imports: [FormsModule],
  providers: [AuthService],
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  login(): void {
    this.authService.login(this.username, this.password).subscribe(() => {
      this.router.navigate(['/']);
    });
  }
}
