import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RequestService {
  private username!: string |null;
  private baseUrl:string = "http://localhost:8084/api/v1/";
  private authKey!:string | null;
  private email !:string | null;
  constructor(private http:HttpClient,private router:Router) {
    this.getUsernameAndTokenAndEmail();
    console.log(this.username);
  }
  public getUserdetials():Observable<any>{
    let url = this.baseUrl+"getuserdetails/"+this.username;
    return this.http.get(url);
  }
  public setUserDetails(data: any):Observable<any>{
    let url = this.baseUrl+"setuserdetials/"+this.username;
    return this.http.post(url,data);
  }

  public changePassword(oldCredentials: string,newPassword:string){
    let oldCredentialsBlob:Blob = new Blob([oldCredentials],{type:"application/json"});
    let newPasswordBlob:Blob = new Blob([newPassword],{type:"application/json"});
    let single:FormData=new FormData();
    single.append("oldCredentials",oldCredentialsBlob);
    single.append("newPassword",newPasswordBlob);
    return this.http.post(this.baseUrl+"setpassword",single);
  }

  public changeEmail(oldCredentials: string,newEmail:string){
    console.log("change email");
    let url = this.baseUrl+"changeemail/"+this.username;
    let oldCredentialsBlob:Blob = new Blob([oldCredentials],{type:"application/json"});
    let newEmailBlob:Blob = new Blob([newEmail],{type:"application/json"});
    let single:FormData=new FormData();
    single.append("oldDetails",oldCredentialsBlob);
    single.append("newEmail",newEmailBlob);
    return this.http.post(url,single);
  }


  getLocalStorage():string|null{
    let store:string|null = localStorage.getItem('user');
    if(store!=null) return store;
    return null;
  }

  setUsername(username:string){
    let storeJson:string|null = this.getLocalStorage();
    let user:any;
    if(storeJson!=null){
      user = JSON.parse(storeJson);
      user['_username'] = username;
      this.clear();
      localStorage.setItem('user',JSON.stringify(user));
      this.getUsernameAndTokenAndEmail();
    }
    else this.router.navigate(['/login']);
  }

  getUsername():string|null{
    return this.username
  }
  getToken():string|null{
    this.getUsernameAndTokenAndEmail();
    return this.authKey;
  }
  getEmail():string{
    if(this.email!=null)
      return this.email;
    this.clear();
    this.router.navigate(['/login']);
    return "";
  }
  setEmail(email:string){
    let storeJson:string|null = this.getLocalStorage();
    let user:any;
    if(storeJson!=null){
      user = JSON.parse(storeJson);
      user['email'] = email;
      localStorage.clear();
      localStorage.setItem('user',JSON.stringify(user));
      this.email = email;
    }
    else this.router.navigate(['/login']);
  }

  getUsernameAndTokenAndEmail(){
    let storedJson:string|null = this.getLocalStorage();
    let user:any;
    if(storedJson!=null) {
      user = JSON.parse(storedJson);
      this.username = user['_username'];
      this.authKey  =user['_token'];
      this.email = user['email'];
    }
  }
  clear(){
    localStorage.clear();
    this.email = null;
    this.username = null;
    this.authKey = null;
  }
}
