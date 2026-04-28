import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../service/auth';

@Component({
  selector: 'app-login',
  templateUrl: './login.html',
  standalone: false
})
export class LoginComponent {

  username = '';
  password = '';
  errorMessage = '';
  isRegisterMode = false;

  constructor(private authService: AuthService, private router: Router) {}

  submit(): void {
    if (this.isRegisterMode) {
      this.authService.register(this.username, this.password).subscribe({
        next: () => {
          this.isRegisterMode = false;
          this.errorMessage = 'Registration successful! Please log in.';
        },
        error: () => this.errorMessage = 'Registration failed.'
      });
    } else {
      this.authService.login(this.username, this.password).subscribe({
        next: () => this.router.navigate(['/category-list']),
        error: () => this.errorMessage = 'Invalid username or password.'
      });
    }
  }

  toggleMode(): void {
    this.isRegisterMode = !this.isRegisterMode;
    this.errorMessage = '';
  }
}
