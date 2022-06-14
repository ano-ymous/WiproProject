import {Component, OnInit, ViewChild} from '@angular/core';
import {NgForm} from "@angular/forms";
import {formatDate} from "@angular/common";
import {AuthService} from "../../shared/services/auth.service";
import {DynamicComponentService} from "../../shared/services/dynamic-component.service";
import {PlaceholderDirective} from "../../shared/directives/placeholder.directive";
import {AlertModel} from "../../shared/components/alert/alert.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  @ViewChild(PlaceholderDirective,{static: true}) host!: PlaceholderDirective;
  constructor(private signup: AuthService,
              private alert:DynamicComponentService,
              private router: Router) {
  }

  ngOnInit(): void {

  }

  signUp(signupform: NgForm) {
    console.log(signupform);
    if(!signupform.form.valid) return ;
    let val = signupform.form.value;
    let data: object = {
      username:val['username'],
      email:val['email'],
      dateOfBirth: formatDate(val['dob'],'yyyy-MM-dd','en','+0530'),
      phonenumber:val['phonenumber'],
      gender:val['gender'],
      nation:val['nation'],
      firstname:val['firstname'],
      lastname:val['lastname']
    };
    let cred:object ={
      email:val['email'],
      password:val['password'],
    }
    this.signup.signUp(JSON.stringify(data),JSON.stringify(cred)).subscribe(data=>{
      console.log(data);
      this.router.navigate(['/login']);
    },
      error => {
      let message:string = error.error.message;
      if(error.error.message.includes('E11000')){
        if(error.error.message.includes('email'))
          message = 'User exist with this email,\nplease use forgot password to recover the account'
        else if(error.error.message.includes('_id'))
          message = 'User exist with given username';
        else if(error.error.message.includes('phonenumber'))
          message = 'User exist with given phonenumber';
        else
          message = 'User already exist with one of the entered data please try to use forgotpassword'
      }
      this.alert.create(this.host.viewContainerRef,message,AlertModel.danger);
      });
  }
}
