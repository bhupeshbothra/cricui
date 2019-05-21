import { Batting } from './../../../Batting';
import { Bowling } from './../../../Bowling';
import { Component, OnInit, Inject } from '@angular/core';
import { PlayerDetails } from '../../../PlayerDetails';
import { CardServiceService } from '../../../service/card-service.service';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar, MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Player } from '../../../Player';
import { DetailsData } from '../../../DetailsData';
import { deserialize, serialize } from 'json-typescript-mapper';
@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {
  playerDetails: PlayerDetails;
  detailsData: DetailsData;
  bowling: Bowling;
  batting: Batting;
  pid: String
  constructor(private cardService: CardServiceService
    , private routes: ActivatedRoute, private matSnackBar: MatSnackBar,
    public dialogRef: MatDialogRef<DetailsComponent>,
    @Inject(MAT_DIALOG_DATA) public player: Player) {

    this.pid = this.player.pid;

  }

  ngOnInit() {
    console.log("pid" + this.pid);
    this.cardService.getPlayerDetails(this.pid)
      .subscribe(data1 => {



        // this.playerDetails = data1;

        // this.playerDetails

        this.playerDetails = deserialize(PlayerDetails, data1);
        console.log(this.playerDetails);
        // serialize(<Object>);
        /*   this.id = 0;
           data.forEach(element => {
     
             this.id++;
             this.playerObj = new Player();
     
             this.playerObj.fullName = element["fullName"];
             this.playerObj.pid = element["pid"];
             this.playerObj.name = element["name"];
     
     
             this.players.push(this.playerObj);
             this.searcPlayers.push(this.playerObj);
      });*/
      });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }


}
