import { Injectable } from '@angular/core';
import { AuthenticationService } from '../authentication/authentication.service';
import { Router } from '@angular/router';
import { TOKEN_NAME } from '../authentication/components/login/login.component';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService {

  constructor(private authService: AuthenticationService, private router: Router) { }

  canActivate() {
    if (localStorage.getItem(TOKEN_NAME) && sessionStorage.getItem("username")) {
      console.log('in can activae');

      return true;
    }
    this.router.navigate(['/login']);
    return false;
  }
}
