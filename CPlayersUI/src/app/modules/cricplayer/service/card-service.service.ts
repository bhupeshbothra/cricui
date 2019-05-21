import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { USER_NAME } from '../../authentication/authentication.service';
import { Player } from '../Player';
import { PlayerCount } from '../components/recommended/PlayerCount';

@Injectable({
  providedIn: 'root'
})
export class CardServiceService {

  thirdPartyApi: String;
  apiKey: String;
  springEndPoint: string;
  springEndPointRecommendation: string;
  username: string;

  constructor(private httpClient: HttpClient) {
    this.thirdPartyApi = 'https://cricapi.com/api/playerFinder?';
    this.apiKey = 'api_key=wKNXgf6g1DUBNEMEX2xf60JDiPs1';
    //this.springEndPoint = "http://localhost:7072/api/v1/favoriteservice/";
    //this.springEndPointRecommendation = "http://localhost:7074/api/v1/playerservice"
     this.springEndPoint = "http://localhost:8086/favoriteservice/api/v1/favoriteservice/";
    this.springEndPointRecommendation = "http://localhost:8086/cricrecommendationservice/api/v1/playerservice";
  }

  getDefaultPlayerList(): Observable<any> {
    this.thirdPartyApi = 'https://cricapi.com/api/playerFinder';
    this.apiKey = '?apikey=wKNXgf6g1DUBNEMEX2xf60JDiPs1&name=Sachin';

    const url = `${this.thirdPartyApi}${this.apiKey}`;

    //return this.httpClient.get('./assets/json/sachin.json');
 return this.httpClient.get(url);
  }

  getPlayerList(name: String): Observable<any> {
    this.thirdPartyApi = 'https://cricapi.com/api/playerFinder';
    this.apiKey = '?apikey=wKNXgf6g1DUBNEMEX2xf60JDiPs1&name=' + name;
    const url = `${this.thirdPartyApi}${this.apiKey}`;

    //return this.httpClient.get('./assets/json/sachin.json');
    return this.httpClient.get(url);

  }

  getPlayerDetails(pid: String): Observable<any> {

    this.thirdPartyApi = 'https://cricapi.com/api/playerStats';
    this.apiKey = '?apikey=wKNXgf6g1DUBNEMEX2xf60JDiPs1&pid=' + pid;
    const url = `${this.thirdPartyApi}${this.apiKey}`;

  //  return this.httpClient.get('./assets/json/sachindetails.json');
    return this.httpClient.get(url);

  }

  addPlayerToFavoriteList(player: Player) {
    this.username = sessionStorage.getItem(USER_NAME);
    const url = this.springEndPoint + "user/" + this.username + "/player";
    return this.httpClient.post(url, player, {
      observe: "response"
    })
  }


  getAllTracksforFavoriteList(): Observable<Player[]> {
    this.username = sessionStorage.getItem(USER_NAME);
    const url = this.springEndPoint + "user/" + this.username + "/player";
    //const url = this.springEndPoint+"tracks";
    return this.httpClient.get<Player[]>(url);

  }

  getAllTracksfromRecommendedList(): Observable<any> {
    const url = this.springEndPointRecommendation + "/list";
    //const url = this.springEndPoint+"tracks";
    console.log(url)
    return this.httpClient.get(url);

  }

  deleteTrackFromFavoriteList(player: Player) {
    this.username = sessionStorage.getItem(USER_NAME);
    const url = this.springEndPoint + "user/" + this.username + "/player";
    // const url = this.springEndPoint+"track/" + `${track.trackId}`;

    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      body: player
    }

    console.log("in delete", player);

    return this.httpClient.delete(url, options);
  }
}
