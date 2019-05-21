import { Component, OnInit } from '@angular/core';
import { CardServiceService } from '../../service/card-service.service';
import { MatSnackBar } from '@angular/material';
import { Player } from '../../Player';
import { PlayerCount } from './PlayerCount';

@Component({
  selector: 'app-recommended',
  templateUrl: './recommended.component.html',
  styleUrls: ['./recommended.component.css']
})
export class RecommendedComponent implements OnInit {

  favoritedata: boolean = true;
  players: Array<PlayerCount>;
  constructor(private cardService: CardServiceService, private snackbar: MatSnackBar) { }

  ngOnInit() {
    this.cardService.getAllTracksfromRecommendedList().subscribe(data => {

      console.log("data" + data['playerList']);
      this.players = data['playerList'];
    });
    console.log(this.players);
  }





}
