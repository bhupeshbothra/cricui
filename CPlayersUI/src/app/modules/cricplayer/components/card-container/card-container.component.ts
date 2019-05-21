import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { Player } from './../../Player';
import { CardServiceService } from '../../service/card-service.service';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-card-container',
  templateUrl: './card-container.component.html',
  styleUrls: ['./card-container.component.css']
})
export class CardContainerComponent implements OnInit {

  playername: string;
  players: Array<Player> = [];
  searcPlayers: Array<Player> = [];
  playerObj: Player;
  id: number;
  errorStatus: string;
  statusCode: number;
  constructor(private cardService: CardServiceService
    , private routes: ActivatedRoute, private matSnackBar: MatSnackBar) {

    this.players = [];
  }

  ngOnInit() {
    this.cardService.getDefaultPlayerList()
      .subscribe(player => {
        console.log(player);
        const data = player['data'];

        this.id = 0;
        data.forEach(element => {

          this.id++;
          this.playerObj = new Player();

          this.playerObj.fullName = element["fullName"];
          this.playerObj.pid = element["pid"];
          this.playerObj.name = element["name"];


          this.players.push(this.playerObj);
          this.searcPlayers.push(this.playerObj);
        });
      });
  }

  onKey(event: any) {

    if (event.keyCode == 13) {
      this.players = [];
      this.playername = event.target.value;
      console.log('playername', this.playername);

      this.cardService.getPlayerList(this.playername)
        .subscribe(player => {
          console.log(player);
          const data = player['data'];

          this.id = 0;
          data.forEach(element => {

            this.id++;
            this.playerObj = new Player();

            this.playerObj.fullName = element["fullName"];
            this.playerObj.pid = element["pid"];
            this.playerObj.name = element["name"];


            this.players.push(this.playerObj);
            this.searcPlayers.push(this.playerObj);
          });
        });
    }

  }


  addToFavoriteList(player) {
    console.log('inside the card container', player);
    this.cardService.addPlayerToFavoriteList(player).subscribe(
      data => {

        console.log(data);

        this.statusCode = data.status;

        if (this.statusCode === 201) {
          console.log("Success", this.statusCode);
          this.matSnackBar.open('Track Successfully added !!!', "    ", {
            duration: 1000
          });
        }

      },
      error => {

        this.errorStatus = `${error.status}`;
        const errorMsg = `${error.error.message}`;
        this.statusCode = parseInt(this.errorStatus, 10);
        if (this.statusCode === 409) {
          this.matSnackBar.open(errorMsg, "", {
            duration: 1000
          });

          this.statusCode = 0;

        }

      }
    )
  }

}
