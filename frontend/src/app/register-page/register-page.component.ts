import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {RestapiService} from "../restapi.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {

  form!: FormGroup

  constructor(private service: RestapiService, private router: Router) {
  }

  ngOnInit(): void {
    this.form = new FormGroup({
      last_name: new FormControl(null, [Validators.required, Validators.maxLength(30)]),
      first_name: new FormControl(null, [Validators.required, Validators.maxLength(30)]),
      middle_name: new FormControl(null, [Validators.required, Validators.maxLength(30)]),
      username: new FormControl(null, [Validators.required, Validators.minLength(5), Validators.maxLength(30)]),
      password: new FormControl(null, [Validators.required, Validators.minLength(6), Validators.maxLength(30)]),
      photo: new FormControl(null, [Validators.required])
    })
  }

  onSubmit() {
    this.form.disable()
      this.service.register(this.form.value).subscribe(() => {
        this.router.navigate(['/login']);
      },
        error => {
        console.warn(error)
          this.form.enable()
        })
  }

}
