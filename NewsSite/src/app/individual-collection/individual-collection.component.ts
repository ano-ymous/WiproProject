import {Component, EventEmitter, Input, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {DynamicFullviewService} from "../shared/services/dynamic-fullview.service";
import {PlaceholderDirective} from "../shared/directives/placeholder.directive";
import {AlertModel} from "../shared/components/alert/alert.model";
import {DynamicComponentService} from "../shared/services/dynamic-component.service";

@Component({
  selector: 'app-individual-collection',
  templateUrl: './individual-collection.component.html',
  styleUrls: ['./individual-collection.component.css']
})
export class IndividualCollectionComponent implements OnInit {
  @Input('heading')heading!:string;
  category:string[] = [];
  homeCollection:string[] = [
    'top today','general','entertainment','business','sports','health','technology','science'
  ];
  constructor(private activatedRoute: ActivatedRoute,
              private fullview:DynamicFullviewService,
              private component: DynamicComponentService) { }

  ngOnInit(): void {
    console.log("new individual component");
    this.activatedRoute.params.subscribe(data=>{
      this.category = [];
      if(data['category']==="home"){
        this.category.push(...this.homeCollection);
      }
      else if(data['category']==="favorite"){
        this.category.push("favorite");
      }
      else
        this.category.push(data['category']);
    });
  }
}
