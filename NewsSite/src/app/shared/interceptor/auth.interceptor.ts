import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpHeaders
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {RequestService} from "../services/request.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private request:RequestService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if(this.request.getToken()!=undefined)
      request = request.clone({
        setHeaders: {
          "Access-Control-Allow-Headers": "*",
          "Access-Control-Allow-Origin": "*",
          "Authorization": "" + this.request.getToken(),
          "Access-Control-Allow-Methods": "GET,POST,OPTIONS,DELETE,PUT"
        }
      });
    return next.handle(request);
  }
}
