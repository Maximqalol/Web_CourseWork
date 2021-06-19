import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RestapiService} from "../restapi.service";
import {Router} from "@angular/router";
import {User} from "../shared/models/user";
import {FormGroup} from "@angular/forms";
import {Picture} from "../shared/models/picture";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  currentUser!: User

  form!: FormGroup

  dateToday: number = Date.now();
  logoutMessage!: string


  constructor(private http: HttpClient, private auth: RestapiService, private router: Router) {
  }

  ngOnInit(): void {
    this.setCurrentUser()
  }

  setCurrentUser() {
    this.auth.getCurrentUserData().subscribe(
      res => {
        this.currentUser = res;
      }, error => {
        console.log('Не удалось загрузить пользователя');
      }
    )
  }

  logout() {
    sessionStorage.setItem('token', '');
    this.logoutMessage = "Вы успешно вышли из аккаунта!"
  }


  deleteProfile() {
    this.auth.delete(this.currentUser.username).subscribe(data =>{}, error => {console.log(error)});
    sessionStorage.setItem('token', '');
    this.router.navigateByUrl("/login")
  }

}
