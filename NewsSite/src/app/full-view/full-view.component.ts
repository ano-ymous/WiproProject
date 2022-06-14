import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-full-view',
  templateUrl: './full-view.component.html',
  styleUrls: ['./full-view.component.css']
})
export class FullViewComponent implements OnInit {
  content!:string | null;
  header!:string | null;
  closeEmitter:EventEmitter<void> = new EventEmitter<void>();
  url!: string | null;
  constructor() { }

  ngOnInit(): void {
  }
  close(){
    this.closeEmitter.emit();
  }
}
