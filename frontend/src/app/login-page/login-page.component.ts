import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {RestapiService} from "../restapi.service";
import {UserLogin} from "../shared/models/user";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  form!: FormGroup
  model: any = {}
  invalidData!: string

  constructor(private service: RestapiService, private router: Router) {

  }

  ngOnInit(): void {
    this.form = new FormGroup({
      username: new FormControl(null, [Validators.required, Validators.minLength(5), Validators.maxLength(30)]),
      password: new FormControl(null, [Validators.required, Validators.minLength(6), Validators.maxLength(30)])
    })
    sessionStorage.setItem('token', '');
  }

  onSubmit(form: UserLogin) {
    this.service.login(form).subscribe(isValid => {
      if (isValid) {
        sessionStorage.setItem('token', btoa(this.form.get('username')?.value + ':' + this.form.get('password')?.value));
        sessionStorage.setItem('username', this.form.get('username')?.value);
        console.log(isValid)
        this.router.navigate(['/mainpage']);
      } else {
        this.invalidData = "Неверное имя пользователя или пароль!";
      }
    },
      error => {
        this.invalidData = "Неверное имя пользователя или пароль!";
      }
    )

  }
}
