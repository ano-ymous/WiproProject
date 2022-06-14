import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanActivateChild,
  CanDeactivate,
  CanLoad,
  Route,
  Router,
  RouterStateSnapshot,
  UrlSegment,
  UrlTree
} from '@angular/router';
import {Observable} from 'rxjs';
import {AuthService} from "../services/auth.service";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class LoginAuthenticationGuard implements CanActivate, CanActivateChild, CanDeactivate<unknown>, CanLoad {
  constructor(private auth: AuthService,private router:Router) {
  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.auth.user.pipe(map(data=> {
      const isAuth = !!data;
      if(isAuth)
        return true;
      return this.router.createUrlTree(['/login']);
      })
    )
  }
  canActivateChild(
    childRoute: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.auth.user.pipe(map(data=> {
        const isAuth = !!data;
        if(isAuth)
          return true;
        return this.router.createUrlTree(['/login']);
      })
    )
  }
  canDeactivate(
    component: unknown,
    currentRoute: ActivatedRouteSnapshot,
    currentState: RouterStateSnapshot,
    nextState?: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.auth.user.pipe(map(data=> {
        const isAuth = !!data;
        if(isAuth)
          return true;
        return this.router.createUrlTree(['/login']);
      })
    );
  }
  canLoad(
    route: Route,
    segments: UrlSegment[]): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return true;
  }
}
