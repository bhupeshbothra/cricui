import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TOKEN_NAME } from '../authentication/components/login/login.component';
import { AuthenticationService } from '../authentication/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem(TOKEN_NAME);
    console.log('In intercept');

    console.log(token);
	
	
	 if (request.url.indexOf('/cricapi.com') != -1) {
      return next.handle(request); // do nothing
    }
	
    request = request.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    })
    console.log(request);

    return next.handle(request);
  }

  constructor(private auth: AuthenticationService) { }
}
