import {Data} from "@angular/router";

export class User{
  constructor(private _username:string,private email:string,
              private _token:string,
              private _tokenExpiryDate:Date
  ) {}
  get username():string{
    return this._username;
  }
  get token():string|null{
    if(!this._tokenExpiryDate || new Date() > this._tokenExpiryDate)
      return null;
    return this._token;
  }
  get expiryDate():Data{
    return this._tokenExpiryDate;
  }
}
