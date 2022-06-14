import {Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {DatafetchService} from "../shared/services/datafetch.service";
import {IInfiniteScrollEvent} from "ngx-infinite-scroll";
import {DynamicComponentService} from "../shared/services/dynamic-component.service";
import {PlaceholderDirective} from "../shared/directives/placeholder.directive";
import {AlertModel} from "../shared/components/alert/alert.model";
import {CardData} from "../shared/interface/card-data";
import {DynamicFullviewService} from "../shared/services/dynamic-fullview.service";

@Component({
  selector: 'app-card-collection',
  templateUrl: './card-collection.component.html',
  styleUrls: ['./card-collection.component.css']
})
export class CardCollectionComponent implements
  OnInit,
  OnChanges,
  OnDestroy{
  isFullScreen:boolean = false;
  page:number = 1;
  data:CardData[] = [];
  isLoading:boolean = false;
  @Input('heading') heading!:string;
  isDataEnded:boolean = false;
  favoritesList!:string[];
  @ViewChild(PlaceholderDirective, {static : true}) host!:PlaceholderDirective;
  closeEvent: EventEmitter<void> | undefined;
  fullviewcardDataEmitter: EventEmitter<{header:string | null,
    content:string|null,url:string|null}> = new EventEmitter<{header: string | null; content: string | null; url: string | null}>();
  fullviewcloseEvent :EventEmitter<void> | undefined;
  constructor(private activedRoute: ActivatedRoute,
              private fetch: DatafetchService,
              private component: DynamicComponentService,private fullview:DynamicFullviewService) { }

  ngOnDestroy(): void {
    if(this.closeEvent != undefined)
        this.closeEvent.emit();
    if(this.fullviewcloseEvent != undefined)
      this.fullviewcloseEvent.emit();
    }

  ngOnInit(): void {
    this.fetch.getFavList().subscribe(data=>{
      console.log(data);
      // @ts-ignore
      this.favoritesList = [...data];
    });
    this.fullviewcardDataEmitter.subscribe(data=>{
      this.fullviewcloseEvent = this.fullview.create(this.host.viewContainerRef,data.header,data.content,data.url);
    })
  }

  onSeeAllChange() {
    this.fetch.fullViewSubject.next(!this.isFullScreen);
    this.isFullScreen = !this.isFullScreen;
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.isLoading = true;
    this.page = 1;
    this.data = [];
    this.fetchData();
  }
  fetchData(){
    if(this.isDataEnded) {
      this.isLoading = false;
      return;
    }
    this.fetch.fetchWithKey(this.heading,this.page).subscribe(data=>{
      console.log('data is getting');
      if(data.length != 0) {
        this.data.push(...data);
        if(this.heading==='favorite')
          this.isDataEnded = true;
      }
      else{
        this.closeEvent = this.component.create(this.host.viewContainerRef,'no more news to show',AlertModel.warning);
        this.isDataEnded = true;
      }
    },
      error => {
      console.log('error found');
        this.closeEvent = this.component.create(this.host.viewContainerRef,error.error.message,AlertModel.danger);
        this.isLoading = false;
    },
      ()=>{
      this.page+=1;
      this.isLoading = !this.isLoading;
    });
  }

  onScroll(event: IInfiniteScrollEvent) {
    this.isLoading=true;
    this.fetchData();
  }

  isfav(i: any):boolean {
    if(i==[])
      return false
    return this.favoritesList.includes(i['url']);
  }

}
