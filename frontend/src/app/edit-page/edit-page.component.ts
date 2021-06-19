import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {RestapiService} from "../restapi.service";
import {Router} from "@angular/router";
import {UserCreate, UserUpdate} from "../shared/models/user";
import {Observable} from "rxjs";
import {Picture} from "../shared/models/picture";

@Component({
  selector: 'app-edit-page',
  templateUrl: './edit-page.component.html',
  styleUrls: ['./edit-page.component.css']
})
export class EditPageComponent implements OnInit {

  form!: FormGroup
  selectedFiles1?: FileList;
  currentFile1?: File;

  fileInfos?: Observable<any>;
  oldUsername!: string

  constructor(private http: HttpClient, private auth: RestapiService,
              private service: RestapiService, private router: Router) {
  }

  ngOnInit(): void {
    this.form = new FormGroup({
      last_name: new FormControl(null, [Validators.required, Validators.maxLength(30)]),
      first_name: new FormControl(null, [Validators.required, Validators.maxLength(30)]),
      middle_name: new FormControl(null, [Validators.required, Validators.maxLength(30)]),
      username: new FormControl(null, [Validators.required, Validators.minLength(5), Validators.maxLength(30)]),
      password: new FormControl(null, [Validators.required, Validators.minLength(6), Validators.maxLength(30)]),
      photo: new FormControl(null)
    })

    this.auth.getCurrentUserData().subscribe(data => {
        let user = {
          last_name: data.last_name,
          first_name: data.first_name,
          middle_name: data.middle_name,
          username: data.username,
          password: "",
          photo: ""
        }
        this.form.setValue(user)
        this.oldUsername=  data.username
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

  onSubmit() {
    // @ts-ignore
    this.currentFile1 = this.selectedFiles1.item(0);
    // @ts-ignore
    this.service.uploadPicture(this.currentFile1).subscribe(
      (event: any) => {
        if (event.body != undefined) {
          let user = {
            picture_id: event.body.id,
            username: this.form.get('username')?.value,
            password: this.form.get('password')?.value,
            first_name: this.form.get('first_name')?.value,
            last_name: this.form.get('last_name')?.value,
            middle_name: this.form.get('middle_name')?.value,
            name: this.oldUsername
          } as unknown as UserUpdate;
          console.log(user)

          this.service.edit(user).subscribe(
            res => {
              sessionStorage.setItem('token', btoa(this.form.get('username')?.value + ':' + this.form.get('password')?.value))
              this.router.navigateByUrl('/mainpage')
              console.log(res);
            }
          )
        }
      },
      (err: any) => {
        console.log(err.body)
        this.currentFile1 = undefined;
      });
    this.selectedFiles1 = undefined;
  }

  selectFile(event1: any): void {
    this.selectedFiles1 = event1.target.files;
  }

}
