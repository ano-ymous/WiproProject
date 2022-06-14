import { Injectable } from '@angular/core';
import {ActivatedRoute, Router, RouterState} from "@angular/router";
import {RequestService} from "./request.service";

@Injectable({
  providedIn: 'root'
})
export class UrlGenerationService {
  pageCount:number = 10;
  newsUrl : string = 'http://localhost:8084/news/api/fetch';
  favoritesUrl:string = 'http://localhost:8084/news/api/favorites/';
  constructor(private activeroute:ActivatedRoute,private route: Router,private request:RequestService) { }
  public getUrl(key: string, isFullView: boolean, page: number){
    if(key === 'favorite'){
      return this.favoritesUrl+'/getfavorites/'+this.request.getUsername();
    }
    if(isFullView){
      this.pageCount = 25;
    }
    else this.pageCount = 10;
    return this.newsUrl+this.fetchWithKey(key)+'page='+page+'&pageSize='+this.pageCount;
  }
  public fetchWithKey(key:string):string {
    let routeWords: string[] = this.routeWordGenerator(this.route.url);
    let url = ''
    if(routeWords[0]==='home'){
      url+= '/top-headlines';
    }
    else{
      url+='/everything'
    }
    if(['general','entertainment','business','sports','health','technology','science'].includes(key))
      url+='?category='+key;
    else if(["top today"].includes(key)){
      url+="?nation=in";
    }
    else
      url+='?keyword='+key;
    return url+'&';
  }


  private routeWordGenerator(url:string):string[]{
    let seperate:string[] = url.split('?')[0].split('/');
    seperate.shift();
    seperate.shift();
    return seperate;
  }

  genFavurl():string {
    return this.favoritesUrl+"add/"+this.request.getUsername();
  }

  genFavListUrl():string {
    return this.favoritesUrl+"getlist/"+this.request.getUsername();
  }

  deleteFavUrl():string {
    return this.favoritesUrl+"removefavorites/"+this.request.getUsername();
  }
}
