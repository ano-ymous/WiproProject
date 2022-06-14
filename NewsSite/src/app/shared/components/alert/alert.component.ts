import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AlertModel} from "./alert.model";

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.css']
})
export class AlertComponent implements OnInit {
  @Input('message') data !: string;
  @Input('type') type !: AlertModel;
  @Input('time') time : number = 30000;
  @Output() close:EventEmitter<void> = new EventEmitter<void>();
  color !: string
  constructor() { }

  ngOnInit(): void {
    this.cssColor();
    setTimeout(()=>{
      this.closeMethod();
    },this.time);
  }
  cssColor(){
    if (this.type === AlertModel.info) {
      this.color = '#437ba4';
    }
    else if(this.type === AlertModel.danger){
      this.color = '#9d3434';
    }
    else if(this.type === AlertModel.warning){
      this.color = '#c5b14e';
    }
    else if(this.type === AlertModel.success){
      this.color = '#457006';
    }
  }
  closeMethod(){
    this.close.emit();
  }
}
