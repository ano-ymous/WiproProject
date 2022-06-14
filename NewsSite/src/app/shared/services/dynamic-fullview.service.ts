import {EventEmitter, Injectable, ViewContainerRef} from '@angular/core';
import {Subscription} from "rxjs";
import {FullViewComponent} from "../../full-view/full-view.component";

@Injectable({
  providedIn: 'root'
})
export class DynamicFullviewService {
  public closeSub :Subscription = new Subscription();

  constructor() { }

  create(viewContainerRef: ViewContainerRef, header: string | null, content: string | null, url: string | null):EventEmitter<void>{
    viewContainerRef.clear();
    const componentRef = viewContainerRef.createComponent<FullViewComponent>(FullViewComponent);
    componentRef.instance.header = header;
    componentRef.instance.content = content;
    componentRef.instance.url = url;
    this.closeSub = componentRef.instance.closeEmitter.subscribe(()=>{
      this.closeSub.unsubscribe();
      viewContainerRef.clear();
    });
    return componentRef.instance.closeEmitter;
  }
}
