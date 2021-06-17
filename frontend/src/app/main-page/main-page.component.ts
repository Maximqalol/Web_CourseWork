import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RestapiService} from "../restapi.service";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {
  last_name!: string
  first_name!: string
  middle_name!: string
  countEntry!: number
  photo!: string

  dateToday: number = Date.now();


  constructor(private http: HttpClient, private auth: RestapiService) {
  }

  ngOnInit(): void {
    this.auth.getCurrentUserData().subscribe(data => {
         this.last_name = data.last_name;
         this.first_name = data.first_name;
         this.middle_name = data.middle_name;
         this.countEntry = data.count;
         this.photo = 'data:image/png;base64,' + data.photo;
        console.log(data)
      },
      error => {
        if (error.status == 401)
          alert('Unauthorized');
        console.log(error)
      }
    );
  }

  logout() {
    sessionStorage.setItem('token', '');
  }


  deleteProfile() {

  }

}
