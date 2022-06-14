import {Component, EventEmitter, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {AlertModel} from "../shared/components/alert/alert.model";
import {DynamicComponentService} from "../shared/services/dynamic-component.service";
import {PlaceholderDirective} from "../shared/directives/placeholder.directive";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements
  OnInit{
  constructor(private alert: DynamicComponentService) { }

  ngOnInit(): void {
  }
}
