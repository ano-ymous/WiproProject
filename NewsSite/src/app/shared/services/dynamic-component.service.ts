import {EventEmitter, Injectable, ViewContainerRef} from '@angular/core';
import {AlertComponent} from "../components/alert/alert.component";
import {AlertModel} from "../components/alert/alert.model";
import {Subscription} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DynamicComponentService {
  public closeSub!:Subscription
  constructor() { }
  create(viewContainerRef: ViewContainerRef,message:string,type:AlertModel,time?:number):EventEmitter<void>{
    viewContainerRef.clear();
    const componentRef = viewContainerRef.createComponent<AlertComponent>(AlertComponent);
    componentRef.instance.data = message;
    componentRef.instance.type = type;
    componentRef.instance.time = time ? time : 3000;
    this.closeSub = componentRef.instance.close.subscribe(()=>{
      this.closeSub.unsubscribe();
      viewContainerRef.clear();
    });
    return componentRef.instance.close;
  }
}
