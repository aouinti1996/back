import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, observable} from "rxjs";
import {Event} from "../model/Event";
import {Office} from "../model/Office";
import {User} from "../model/User";
import {NoteE} from "../model/NoteE";
import {EventType} from "../model/Enums/eventType";


@Injectable({
  providedIn: 'root'
})
export class EventService {


  constructor( private http : HttpClient) {

  }
  /* GET METHODE */

  getEvents() : Observable<Event[]>{
    return this.http.get<Event[]>("http://localhost:8089/WellCo/Event/listOfEvent");
  }

  getEventByOffice(idOffice : number): Observable<Event[]>{
    return this.http.get<Event[]>("http://localhost:8089/WellCo/Event/getEventsByOffice/" + idOffice);
  }

  getOffice(): Observable<Office[]>{
    return this.http.get<Office[]>("http://localhost:8089/WellCo/Event/listOffice");
  }

  gteUsersByCentreInterest(idUser : number) : Observable<User[]>{
    return this.http.get<User[]>("http://localhost:8089/WellCo/Event/getUsersByCentreInterest/" + idUser);
  }

  getUsersByLikes(idEvent : number) :Observable<User[]>{
    return this.http.get<User[]>("http://localhost:8089/WellCo/Event/getUsersByLikes/" + idEvent);
  }

  getUserByComment(idEvent : number) :Observable<User[]>{
    return this.http.get<User[]>("http://localhost:8089/WellCo/Event/getUsersByComment/" + idEvent);
  }

  /* PUT METHODE */

  body = {title : "Angular PUT Request"};

  addEventToFav(idEvent : number, idUser : number){
    return this.http.put<any>("http://localhost:8089/WellCo/Event/AddEventToFav/" + idEvent + "/" + idUser , this.body);
  }

  joinEvent(idEvent : number, idUser : number){
    this.http.put("http://localhost:8089/WellCo/Event/joinEvent/" + idEvent + "/" + idUser,this.body)
  }


  likeEvent(idEvent : number, idUser : number){
    return this.http.put("http://localhost:8089/WellCo/Event/likeE/"+ idEvent + "/" + idUser, this.body);
  }

  commentEvent(idEvent : number, idUser : number, comment : string){
    return this.http.put("http://localhost:8089/WellCo/Event/commentEvent/"+ idEvent + "/" + idUser + "/" + comment, this.body);
  }


  noteEvent(idEvent : number, idUser : number, eventNote: NoteE){
    return this.http.put("http://localhost:8089/WellCo/Event/noteEvent/"+ idEvent + "/" + eventNote + "/" + idUser, this.body);
  }

  /* DELETE METHODE */

  deleteEvent(idEvent : number) :Observable<Event>{
    return this.http.delete<Event>("http://localhost:8089/WellCo/Event/deleteEvent/" + idEvent);
  }

  removeLike(idEvent : number, idUser : number){
    return this.http.delete("http://localhost:8089/WellCo/Event/removeLike/"+ idEvent + "/" + idUser);
  }

  uncommentEvent(idComment : number){
    return this.http.delete("http://localhost:8089/WellCo/Event/uncommentEvent/" + idComment);
  }

  unjoinEvent(idEvent : number, idUser : number){
    return this.http.delete("http://localhost:8089/WellCo/Event/unjoinEvent/"+ idEvent + "/" + idUser);
  }

  removeNote(idNote : number, idEvent : number){
    return this.http.delete("http://localhost:8089/WellCo/Event/deleteNote/"+ idNote + "/" + idEvent);
  }

  /* POST METHODE */

  addEvent(event : Event, idOffice : number): Observable<Event>{
    return this.http.post<Event>("http://localhost:8089/WellCo/Event/addEvent/" + idOffice , event);
  }

  addOffice(office : Office): Observable<Office>{
    return this.http.post<Office>("http://localhost:8089/WellCo/Event/addOffice", office);
  }

}
