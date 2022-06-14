import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import {FormsModule} from "@angular/forms";
import { NewsCardComponent } from './news-card/news-card.component';
import { CardCollectionComponent } from './card-collection/card-collection.component';
import {RouterModule, Routes} from "@angular/router";
import { IndividualCollectionComponent } from './individual-collection/individual-collection.component';
import {HTTP_INTERCEPTORS, HttpClientModule, HttpHeaders} from "@angular/common/http";
import {InfiniteScrollModule} from "ngx-infinite-scroll";
import { AlertComponent } from './shared/components/alert/alert.component';
import { PlaceholderDirective } from './shared/directives/placeholder.directive';
import { LoginComponent } from './user/login/login.component';
import { SignupComponent } from './user/signup/signup.component';
import { ChangepasswordComponent } from './user/changepassword/changepassword.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from "@angular/material/select";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import { PasswordValidatorDirective } from './shared/directives/password-validator.directive';
import { CountryValidatorDirective } from './shared/directives/country-validator.directive';
import {MatMenuModule} from "@angular/material/menu";
import {MatIconModule} from "@angular/material/icon";
import {LoginAuthenticationGuard} from "./shared/gaurd/login-authentication.guard";
import { ProfileComponent } from './profile/profile.component';
import { UserDetailsComponent } from './profile/user-details/user-details.component';
import { ChangeEmailComponent } from './user/change-email/change-email.component';
import {AuthInterceptor} from "./shared/interceptor/auth.interceptor";
import {JwtModule} from "@auth0/angular-jwt";
import { FullViewComponent } from './full-view/full-view.component';
import {ExtendedModule} from "@angular/flex-layout";
import { LogoutComponent } from './user/logout/logout.component';
import { AlertNullRectifierPipe } from './shared/pipe/alert-null-rectifier.pipe';

const routes: Routes=[
  {path:'profile',canActivate:[LoginAuthenticationGuard],canActivateChild:[LoginAuthenticationGuard],canDeactivate:[LoginAuthenticationGuard],component:ProfileComponent
    ,children:[
      {path:"",redirectTo:"userdetails",pathMatch:"full"},
      {path:"userdetails",canActivate:[LoginAuthenticationGuard],canDeactivate:[LoginAuthenticationGuard],component:UserDetailsComponent},
      {path:"changepassword",canActivate:[LoginAuthenticationGuard],canDeactivate:[LoginAuthenticationGuard],component:ChangepasswordComponent},
      {path:"changeemail",canActivate:[LoginAuthenticationGuard],canDeactivate:[LoginAuthenticationGuard],component:ChangeEmailComponent},
      {path:"**",redirectTo:"/home"},
    ]
  },
  {path:'login',component:LoginComponent},
  {path:'logout',component:LogoutComponent},
  {path:'signup',component:SignupComponent},
  {path:'user/:category',canActivate:[LoginAuthenticationGuard],canDeactivate:[LoginAuthenticationGuard],component:IndividualCollectionComponent},
  {path:'',redirectTo:'user/home',pathMatch:'full'},
  {path:'**',component: CardCollectionComponent},
]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    NewsCardComponent,
    CardCollectionComponent,
    IndividualCollectionComponent,
    AlertComponent,
    PlaceholderDirective,
    LoginComponent,
    SignupComponent,
    ChangepasswordComponent,
    PasswordValidatorDirective,
    CountryValidatorDirective,
    ProfileComponent,
    UserDetailsComponent,
    ChangeEmailComponent,
    FullViewComponent,
    LogoutComponent,
    AlertNullRectifierPipe,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    InfiniteScrollModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatMenuModule,
    MatIconModule,
    ExtendedModule,
  ],
  providers: [
    {
      provide:HTTP_INTERCEPTORS,
      useClass:AuthInterceptor,
      multi:true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
