import {Component, EventEmitter, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {NgForm} from "@angular/forms";
import {RequestService} from "../../shared/services/request.service";
import {Router} from "@angular/router";
import {AlertModel} from "../../shared/components/alert/alert.model";
import {PlaceholderDirective} from "../../shared/directives/placeholder.directive";
import {DynamicComponentService} from "../../shared/services/dynamic-component.service";

@Component({
  selector: 'app-changepassword',
  templateUrl: './changepassword.component.html',
  styleUrls: ['./changepassword.component.css']
})
export class ChangepasswordComponent implements OnInit,
OnDestroy{
  email!:string;
  private closeSub!: EventEmitter<void>;
  @ViewChild(PlaceholderDirective,{static:true}) host!:PlaceholderDirective;
  constructor(private request:RequestService,
              private  alert:DynamicComponentService) { }

  ngOnInit(): void {
    this.email = this.request.getEmail();
  }

  changePassword(changepasswordform: NgForm) {
    let data = changepasswordform.form.value;
    this.request.changePassword(JSON.stringify(data['oldcred']),data['newpassword']).subscribe(data=>{
        console.log(data);
      this.closeSub = this.alert.create(this.host.viewContainerRef,"successfully changed the password",AlertModel.success);
    },
      error => {
        this.closeSub = this.alert.create(this.host.viewContainerRef,error.error.message,AlertModel.danger);

      });

  }

  ngOnDestroy(): void {
    if(this.closeSub != undefined)
      this.closeSub.emit();
  }
}
