import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  form!: FormGroup
  hidePassword = true;
  constructor() {
  }

  ngOnInit(): void {
    this.form = new FormGroup({
      username: new FormControl(null, [Validators.required, Validators.minLength(5), Validators.maxLength(30)]),
      password: new FormControl(null, [Validators.required, Validators.minLength(6), Validators.maxLength(30)])
    })
  }
  onSubmit() {

  }
}
