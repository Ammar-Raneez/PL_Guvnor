import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FootballClub } from 'src/app/models/FootballClub.model';
import { SelectedClubService } from 'src/app/services/selected-club/selected-club.service';

@Component({
  selector: 'app-selected-club',
  templateUrl: './selected-club.component.html',
  styleUrls: ['./selected-club.component.css']
})
export class SelectedClubComponent implements OnInit {
  private selectedClub : FootballClub;
  private clubName : string;
  private positionBackgroundColor: string[] = [];
  private starRatingCount: number[] = [];
  private playerImageLink: string[] = [];

  constructor(private selectedClubService : SelectedClubService, private route : ActivatedRoute) { }

  ngOnInit(): void {
    this.clubName = this.route.snapshot.params["clubName"];
    this.getSelectedClub();
  }

  public getSelectedClub() : void {
    this.selectedClubService.getSelectedClub(this.clubName).subscribe(
      response => this.handleSuccessfulResponse(response),
      error => this.handleErrorResponse(error)
    )
  }
  private handleSuccessfulResponse(response : any) : void {
    this.selectedClub = response;
    this.determinePositionBackgroundColor();
    this.determineStarRatingCount();
    this.determinePlayerImageLink();
    console.log(response);
  }
  private handleErrorResponse(error : any) : void {
    this.selectedClub = error.message;
    console.log(error);
  }

  private determinePositionBackgroundColor() : void {
    const goldenRod = [ 'SW', 'LM', 'RM', 'CM', 'DM', 'AM' ];
    const red = ['CF', 'SS', 'S', 'WF'];
    const green = ['LB', 'RB', 'CB', 'LWB', 'RWB'];
    // const purple = ['GK']

    if (this.selectedClub) {
      for(let player of this.selectedClub.allPlayers) {
        if(goldenRod.includes(player.position)) {
          this.positionBackgroundColor.push('goldenrod');
        } else if(red.includes(player.position)) {
          this.positionBackgroundColor.push('red');
        } else if(green.includes(player.position)) {
          this.positionBackgroundColor.push('green');
        } else {
          this.positionBackgroundColor.push('purple');
        }
      }
    }
  }
  private determineStarRatingCount() : void {
    if(this.selectedClub) {
      for(let player of this.selectedClub.allPlayers) {
        if(player.playerStats.overall < 50) {
          this.starRatingCount.push(3);
        } else if(player.playerStats.overall < 75) {
          this.starRatingCount.push(4);
        } else {
          this.starRatingCount.push(5);
        }
      }
    }
  }
  private determinePlayerImageLink() : void {
    let imagePaths: string[] = ['assets/images/PL-player.png', 'assets/images/PL-player2.png', 'assets/images/PL-player3.png', 'assets/images/PL-player4.png', 'assets/images/PL-player5.png', 'assets/images/PL-player6.png', 'assets/images/PL-player7.png', 'assets/images/PL-player8.png'];
    if(this.selectedClub) {
      for(let _ of this.selectedClub.allPlayers) {
        let randomNum = Math.floor(Math.random() * imagePaths.length);
        this.playerImageLink.push(imagePaths[randomNum]);
      }
    }
  }

  public getTheSelectedClub() : FootballClub {
    return this.selectedClub;
  }

  public getPositionBgColor() : string[] {
    return this.positionBackgroundColor;
  }

  public getImageLink() : string[] {
    return this.playerImageLink;
  }
}