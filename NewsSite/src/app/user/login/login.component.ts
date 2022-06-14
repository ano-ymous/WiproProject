import {Component, OnInit, ViewChild} from '@angular/core';
import {NgForm} from "@angular/forms";
import {User} from "./login.model";
import {Router} from "@angular/router";
import {LoginAuthenticationGuard} from "../../shared/gaurd/login-authentication.guard";
import {AuthService} from "../../shared/services/auth.service";
import {DynamicComponentService} from "../../shared/services/dynamic-component.service";
import {PlaceholderDirective} from "../../shared/directives/placeholder.directive";
import {AlertModel} from "../../shared/components/alert/alert.model";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username !:string;
  @ViewChild(PlaceholderDirective,{static:true}) host!: PlaceholderDirective;
  constructor(private login:AuthService,
              private router: Router,
              private auth:LoginAuthenticationGuard,
              private alert:DynamicComponentService) { }

  ngOnInit(): void {
  }

  validateCredentials(loginForm: NgForm) {
    let formData: FormData = new FormData();
    formData.append("email", loginForm.form.controls['email'].value.toLowerCase());
    formData.append("password", loginForm.form.controls['password'].value);
    this.login.login(formData).subscribe((response)=>{
        const user = new User(response['username'],loginForm.form.controls['email'].value,
          response['data'], response['expiryDate']);
        localStorage.setItem('user',JSON.stringify(user));
        console.log(JSON.stringify(user));
        this.login.user.next(user);
        this.router.navigate(['/user/home']);
      },
      error => {
      let message:string = "Unable to find error please refresh the page and try again"
      if(error.message.includes("Unauthorized"))
        message = "Invalid Credentials"
        this.alert.create(this.host.viewContainerRef,message,AlertModel.danger);
      }
      );
  }
}
