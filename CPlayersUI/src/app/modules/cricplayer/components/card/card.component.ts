import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Player } from '../../Player';
import { MatDialog } from '@angular/material';
import { DetailsComponent } from '../details/details/details.component';
@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  @Input()
  player: Player;

  @Input()
  favoritedata: boolean;

  @Output()
  addToFavoriteList = new EventEmitter();

  @Output()
  deleteFromFavoriteList = new EventEmitter();

  constructor(public dialog: MatDialog) { }

  ngOnInit() {
  }

  addButtonClick(player: Player) {
    console.log('card player', player);
    this.addToFavoriteList.emit(player);
  }

  deleteTrack(player: Player) {
    console.log('card player', player);
    this.deleteFromFavoriteList.emit(player);
  }

  getDetails() {
    console.log("this.player.pid" + this.player.pid);
    const dialogRef = this.dialog.open(DetailsComponent, {
      height: '75vh',
      width: '60vw',
      data: { pid: this.player.pid }
    });

    /* dialogRef.afterClosed().subscribe(result => {
       console.log('result', result);
       this.track.comment = result;
       this.updateComments.emit(this.track);
     });*/
  }
}
