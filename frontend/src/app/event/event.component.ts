import { Component, OnInit } from '@angular/core';
import {Event} from "../model/Event";
import {EventService} from "../services/event.service";
import {Office} from "../model/Office";
import {NgForm} from "@angular/forms";
import {EventType} from "../model/Enums/eventType";
import {MatDialog} from "@angular/material/dialog";



@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {

  office : Office = new Office();
  imageURL : any;
  file : any;
  userFile : any;
  selected !: number;
  update(e:any ){
    this.selected = e.target.value
  }

  onSelectedImage(e: any){
    this.userFile = e.target.files[0];
    // @ts-ignore
    this.file = document.querySelector('input[type=file]').files[0];
    var reader = new FileReader();
    reader.readAsDataURL(this.file);
    reader.onload = (res=>{this.imageURL = reader.result})
    console.log(this.userFile);
  }

  eventType = EventType;
  keys = [];
  event : Event = new Event();
  listEvent !: Event[];
  listOffice !: Office[];
  constructor(private service : EventService, private dialogRef: MatDialog) {
    // @ts-ignore
    this.keys = Object.keys(this.eventType);
  }

  ngOnInit(): void {
    this.service.getEvents().subscribe(res=>{console.log(res); this.listEvent=res})
    this.service.getOffice().subscribe(res=>{console.log(res); this.listOffice=res});
  }

  reload(){
    window.location.reload();
  }

  saveEvent(){
    console.log(this.event);
    this.service.addEvent(this.event,this.selected ).subscribe(res=>{console.log(res)});
    this.reload();
  }

  deleteEvent(idEvent: number){
    console.log(idEvent);
    this.service.deleteEvent(idEvent).subscribe();
    this.reload();
  }

  addOffice(){
    console.log(this.office.officeName)
    this.service.addOffice(this.office).subscribe();
    this.reload();
  }






}
