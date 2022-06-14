import {Component, EventEmitter, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {NgForm} from "@angular/forms";
import {RequestService} from "../../shared/services/request.service";
import {formatDate} from "@angular/common";
import {AlertModel} from "../../shared/components/alert/alert.model";
import {DynamicComponentService} from "../../shared/services/dynamic-component.service";
import {PlaceholderDirective} from "../../shared/directives/placeholder.directive";

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit,
OnDestroy{
  data:any;
  private closeSub!: EventEmitter<void>;
  @ViewChild(PlaceholderDirective,{static:true}) host!:PlaceholderDirective;
  constructor(private request:RequestService,
              private alert: DynamicComponentService) { }

  ngOnDestroy(): void {
    if(this.closeSub != undefined)
        this.closeSub.emit();
    }

  ngOnInit(): void {
    this.request.getUserdetials().subscribe(data=>{
      this.data = {
        'username':data['username'],
        'email' : data['email'],
        'firstname' : data['firstname'],
        'lastname' : data['lastname'],
        'phonenumber':data['phonenumber'],
        'dateOfBirth': data['dateOfBirth'],
        'gender':data['gender'],
        'nation':data['nation']
      };
    });
  }

  changeDetials(signupform: NgForm) {
    console.log(signupform);
    this.data['dateOfBirth'] = formatDate(signupform.form.controls['dateOfBirth'].value,'yyyy-MM-dd','en','+0530').toString();
    console.log(this.data['dateOfBirth']);
    this.request.setUserDetails(this.data).subscribe(data=>{
      // @ts-ignore
      this.request.setUsername(this.data['username']);
      console.log(data);
      this.closeSub = this.alert.create(this.host.viewContainerRef,"successfully modified the data",AlertModel.success);
    },
      error => {
        this.closeSub = this.alert.create(this.host.viewContainerRef,error.error.message,AlertModel.danger);
      });
  }
}
