import { Component, OnInit } from '@angular/core';
import {RequestService} from "../../shared/services/request.service";

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {
  username!:string;
  constructor(private request:RequestService) { }

  ngOnInit(): void {
    this.username = ""+this.request.getUsername();
    this.request.clear();
  }

}
