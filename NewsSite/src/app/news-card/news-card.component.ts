import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CardData} from "../shared/interface/card-data";
import {DatafetchService} from "../shared/services/datafetch.service";

@Component({
  selector: 'app-news-card',
  templateUrl: './news-card.component.html',
  styleUrls: ['./news-card.component.css']
})
export class NewsCardComponent implements OnInit {
  isFront:boolean = true;
  @Input('fav')isFav:boolean = false;
  @Input('data')data!:CardData;
  @Input('fullviewemitter') fullviewemitter: EventEmitter<{header:string | null,
    content:string|null,url:string|null}> = new EventEmitter<{header: string | null; content: string | null; url: string | null}>();
  constructor(private fetch:DatafetchService) { }

  ngOnInit(): void {

  }

  toggleFav() {
    if (!this.isFav) {
      this.fetch.addFav(this.data).subscribe(() => {
        this.isFav = !this.isFav;
      });
    }
    else{
      this.fetch.deleteFav(this.data.url).subscribe(data=>{
        console.log(data);
        this.isFav = false;
      });
    }
  }
  fullviewCreater(){
    this.fullviewemitter.emit({header:this.data.title,content:this.data.content,url:this.data.url});
  }
}
