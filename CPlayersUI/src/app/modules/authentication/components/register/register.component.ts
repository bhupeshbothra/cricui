import { Component, OnInit } from '@angular/core';
import { User } from '../../Users';
import { AuthenticationService } from '../../authentication.service';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: User;
  constructor(private authService: AuthenticationService, private snackbar: MatSnackBar,
    private router: Router) {
    this.user = new User();
  }
  register() {
    console.log(this.user);
    this.authService.registerUser(this.user)
      .subscribe(data => {
        if (data.status === 201) {
          this.snackbar.open("Successfully register", " ", {
            duration: 1000
          });
          // this.authService.saveUser(this.user).subscribe( savedata =>{
          //   console.log("save data", savedata);
          // });
        }
        this.router.navigate(["/login"]);

      },
        error => {
          console.log(error);
        })
  }
  ngOnInit() {
  }

}
