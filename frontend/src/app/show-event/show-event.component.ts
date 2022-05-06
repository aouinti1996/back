import { Component, OnInit, AfterViewInit } from '@angular/core';
import {Event} from "../model/Event";
import {EventService} from "../services/event.service";
import * as tt from '@tomtom-international/web-sdk-maps';
import {environment} from "../../environments/environment";
import {Observable, Subscriber } from "rxjs";

@Component({
  selector: 'app-show-event',
  templateUrl: './show-event.component.html',
  styleUrls: ['./show-event.component.css']
})
export class ShowEventComponent implements OnInit, AfterViewInit {

  map : any;
  eventList !: Event[];
  event : Event = new Event();
  constructor(private service: EventService) { }

  ngOnInit(): void {
    this.service.getEvents().subscribe(res=>{this.eventList=res});
  }

  ngAfterViewInit(): void {
    this.loadMap();
  }

  private getCurrentPosition(): any {
    return new Observable((observer: Subscriber<any>) => {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition((position: any) => {
          observer.next({
            latitude: position.coords.latitude,
            longitude: position.coords.longitude,
          });
          observer.complete();
        });
      } else {
        observer.error();
      }
    });
  }

  private loadMap(): void {
    this.map = tt.map({
      key: environment.tomtom.key,
      container: 'map',
    });

    this.map.addControl(new tt.FullscreenControl());
    this.map.addControl(new tt.NavigationControl());

    this.getCurrentPosition()
      .subscribe((position: any) => {
        this.map.flyTo({
          center: {
            lat: position.latitude,
            lng: position.longitude,
          },
          zoom: 13,
        });

        const popup = new tt.Popup({ anchor: 'bottom', offset: { bottom: [0, -40] } }).setHTML('Angular TomTom');

        var marker = new tt.Marker().setLngLat({
          lat: 37.7749,
          lng: -122.4194,
        }).addTo(this.map);
        marker.setPopup(popup).togglePopup();
      });
  }

  public eventDetails(e : Event){
    this.event = e;
  }


}
