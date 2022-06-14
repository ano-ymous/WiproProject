import {Injectable, OnDestroy} from '@angular/core';
import {HttpClient, HttpRequest} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {User} from "../../user/login/login.model";
import {Router} from "@angular/router";
import {RequestService} from "./request.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService{
  public user: BehaviorSubject<User | null> = new BehaviorSubject<User | null>(null);
  private serverUrl: string = "http://localhost:8084/api/v1/";

  constructor(private http: HttpClient,
              private router: Router,
              private request: RequestService) {
  }

  public login(formData: FormData): Observable<any> {
    return this.http.post(this.serverUrl + 'login',
      {"email": formData.get("email"), 'password': formData.get("password")});
  }

  public autologin() {
    let data = localStorage.getItem('user')
    let user: User;
    if (data != null) {
      user = JSON.parse(data);
      if (!user.token && !user.expiryDate) {
        this.user.next(user);
      }
    }
  }

  public signUp(formdata: string, cred: string): Observable<any> {
    let single: FormData = new FormData();
    console.log(formdata);
    single.append('user', new Blob([formdata], {type: "application/json"}));
    single.append('cred', new Blob([cred], {type: "application/json"}));
    return this.http.post(this.serverUrl + "register", single);
  }

  public logout() {
    this.user.next(null);
  }
}

