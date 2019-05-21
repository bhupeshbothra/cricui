import { Component, OnInit } from '@angular/core';
import { Player } from '../../Player';
import { CardServiceService } from '../../service/card-service.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-favorite',
  templateUrl: './favorite.component.html',
  styleUrls: ['./favorite.component.css']
})
export class FavoriteComponent implements OnInit {

  favoritedata: boolean = true;
  players: Array<Player>;
  constructor(private cardService: CardServiceService, private snackbar: MatSnackBar) { }

  ngOnInit() {
    this.cardService.getAllTracksforFavoriteList().subscribe(data => {
      this.players = data;
    });
  }

  deleteFromFavoriteList(player) {
    console.log('inside the deleteFromFavoriteList', player);

    this.cardService.deleteTrackFromFavoriteList(player).subscribe(
      data => {
        console.log("deleted", data);
        const index = this.players.indexOf(player);
        this.players.splice(index, 1);
        this.snackbar.open("deleted", " ", {
          duration: 1000
        });
      });

    return this.players;

  }

}
