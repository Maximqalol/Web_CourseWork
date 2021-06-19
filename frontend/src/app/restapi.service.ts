import {Injectable} from '@angular/core';
import {HttpClient, HttpEvent, HttpHeaders, HttpParams, HttpRequest} from "@angular/common/http";
import {User, UserCreate, UserLogin, UserUpdate} from "./shared/models/user";
import {Observable} from "rxjs";
import {Picture} from "./shared/models/picture";

@Injectable({
  providedIn: 'root'
})
export class RestapiService {

  constructor(private http: HttpClient) {
  }

  login(user: UserLogin): Observable<boolean> {
    return this.http.post<boolean>("/api/auth/login", user);
  }

  register(user: UserCreate): Observable<User> {
    return this.http.post<User>("/api/auth/register", user);
  }

  edit(user: UserUpdate): Observable<UserUpdate> {
    let headers: HttpHeaders = new HttpHeaders({
      'Authorization': 'Basic ' + sessionStorage.getItem('token')
    });
    let options = {headers: headers};
    return this.http.post<UserUpdate>("/api/auth/edit", user, options);
  }

  delete(username: string) {
    return this.http.post("/api/auth/delete", username);
  }

  getCurrentUserData(): Observable<User> {
    let headers: HttpHeaders = new HttpHeaders({
      'Authorization': 'Basic ' + sessionStorage.getItem('token')
    });
    let options = {headers: headers};
    return this.http.post<User>("/api/auth/user", {}, options)
  }

  getTokenFromSessionStorage() {
    return sessionStorage.getItem('token')
  }

  uploadPicture(file: File): Observable<HttpEvent<Picture>> {
    const formData: FormData = new FormData();
    // @ts-ignore
    formData.append('file', file);
    const req = new HttpRequest('POST', `api/picture`, formData, {
      reportProgress: true,
      responseType: 'json'
    });
    return this.http.request(req);
  }

  editPicture(file: File): Observable<HttpEvent<Picture>> {
    const formData: FormData = new FormData();
    // @ts-ignore
    formData.append('file', file);
    const req = new HttpRequest('POST', `api/picture/edit`, formData, {
      reportProgress: true,
      responseType: 'json'
    });
    return this.http.request(req);
  }

}
