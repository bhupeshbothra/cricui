import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
export const USER_NAME = "username";
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private SpringRegistrationEndPoint: string;
  private SpringLoginEndPoint: string;

  constructor(private httpclient: HttpClient) {

  //  this.SpringRegistrationEndPoint = "http://localhost:7071/api/v1/usercricservice/register";
  //  this.SpringLoginEndPoint = "http://localhost:7071/api/v1/usercricservice/login";
	
	 this.SpringRegistrationEndPoint = "http://localhost:8086/cricuserservice/api/v1/usercricservice/register";
     this.SpringLoginEndPoint = "http://localhost:8086/cricuserservice/api/v1/usercricservice/login";

  }

  registerUser(newUser) {
    console.log("newUser" + newUser);
    const url = this.SpringRegistrationEndPoint
    // const url = this.SpringRegistrationEndPoint +"user";
    return this.httpclient.post(url, newUser, {
      observe: "response"
    })
  }

  loginUser(newUser) {
    //const url = this.SpringSaveEndPoint +"login";
    const url = this.SpringLoginEndPoint;
    sessionStorage.setItem(USER_NAME, newUser.username);
    return this.httpclient.post(url, newUser, {
      observe: "response"
    })
  }

  logout() {
    sessionStorage.removeItem(USER_NAME);
    sessionStorage.clear();
    localStorage.removeItem("token_name");
    localStorage.clear();
    console.log("logged out");
  }
}
