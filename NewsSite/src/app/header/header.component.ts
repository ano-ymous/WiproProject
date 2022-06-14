import {Component, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {NgForm} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../shared/services/auth.service";
import {RequestService} from "../shared/services/request.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  username !:String|null;
  isMenuCollapsed: boolean = true;
  @ViewChild('view') view: any;
  constructor(private routes:Router,
              private auth: AuthService,
              private request : RequestService,
              private active: ActivatedRoute) {
  }

  ngOnInit(): void {
    console.log(this.view);
    this.username = this.request.getUsername();
  }
  ngOnChanges(changes: SimpleChanges): void {

    console.log(changes);
  }

  submit(form: NgForm) {
    console.log(form);
    this.isMenuCollapsed = true;
    this.routes.navigate(['/user',form.form.value.search]);
    form.reset();
  }

  logout() {
    this.routes.navigate(['/logout']);
  }
}
