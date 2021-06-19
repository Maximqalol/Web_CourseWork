import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {RestapiService} from "../restapi.service";
import {Router} from "@angular/router";
import {Observable} from "rxjs";
import {Picture} from "../shared/models/picture";
import {UserCreate} from "../shared/models/user";

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {

  form!: FormGroup
  selectedFiles?: FileList;
  currentFile?: File;
  fileInfos?: Observable<any>;
  invalidData!: string
  pic!: Picture;

  constructor(private service: RestapiService, private router: Router) {
  }

  ngOnInit(): void {
    this.form = new FormGroup({
      last_name: new FormControl(null, [Validators.required, Validators.maxLength(30)]),
      first_name: new FormControl(null, [Validators.required, Validators.maxLength(30)]),
      middle_name: new FormControl(null, [Validators.required, Validators.maxLength(30)]),
      username: new FormControl(null, [Validators.required, Validators.minLength(5), Validators.maxLength(30)]),
      password: new FormControl(null, [Validators.required, Validators.minLength(6), Validators.maxLength(30)]),
      picture: new FormControl(null)
    })
  }

  onSubmit() {
    // @ts-ignore
    this.currentFile = this.selectedFiles.item(0);
      // @ts-ignore
    this.service.uploadPicture(this.currentFile).subscribe(
        (event: any) => {
          if (event.body != undefined) {
            let user = {
              picture: event.body,
              username: this.form.get('username')?.value,
              password: this.form.get('password')?.value,
              first_name: this.form.get('first_name')?.value,
              last_name: this.form.get('last_name')?.value,
              middle_name: this.form.get('middle_name')?.value
            } as unknown as UserCreate;
            console.log(user)
            this.service.register(user).subscribe(
              res => {
                this.router.navigateByUrl('/login')
                console.log(res);
              }
            )
          }
        },
        (err: any) => {
          console.log(err);
          this.currentFile = undefined;
          this.invalidData = "Такое имя пользователя уже существует!";
        });
      this.selectedFiles = undefined;

  }

  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }

}
