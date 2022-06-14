import {Component, EventEmitter, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {NgForm} from "@angular/forms";
import {RequestService} from "../../shared/services/request.service";
import {Router} from "@angular/router";
import {AlertModel} from "../../shared/components/alert/alert.model";
import {PlaceholderDirective} from "../../shared/directives/placeholder.directive";
import {DynamicComponentService} from "../../shared/services/dynamic-component.service";

@Component({
  selector: 'app-change-email',
  templateUrl: './change-email.component.html',
  styleUrls: ['./change-email.component.css']
})
export class ChangeEmailComponent implements
  OnInit,
  OnDestroy{
  private closeSub!: EventEmitter<void>;
  @ViewChild(PlaceholderDirective,{static:true}) host!:PlaceholderDirective;
  constructor(private request: RequestService,
              private router: Router,
              private alert: DynamicComponentService) { }

  ngOnInit(): void {
  }

  changeEmail(changeemailform: NgForm) {
    console.log(changeemailform);
    let val = changeemailform.form.value;
    if(val['email']===val['newemail']){
      return ;
    }
    this.request.changeEmail(JSON.stringify({email:val['email'],password:val['password']}),
      val['newemail']).subscribe(data=>{
      localStorage.clear();
      this.router.navigate(['/logout']);
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
