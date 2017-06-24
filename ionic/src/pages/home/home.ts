import { UserService } from './../../providers/user-service';
import { Geolocation } from '@ionic-native/geolocation';
import { Component, ViewChild, ElementRef } from '@angular/core';
import { NavController } from 'ionic-angular';

declare var google;

@Component({
    selector: 'page-home',
    templateUrl: 'home.html'
})
export class HomePage {

    @ViewChild('map') mapElement: ElementRef;
    @ViewChild('directionsPanel') directionPanel: ElementRef;
    map: any;
    labels: any;
    labelIndex: any;
    private users: Array<any>;

constructor(public navController: NavController, public geoLocation: Geolocation, public userService: UserService) {
    this.labels = 'ㄱㄴㄷㄹㅁㅂㅅㅇㅈㅊㅋㅌㅍㅎ';
    this.labelIndex = 0;
}

ionViewDidLoad() {
    this.loadMap();
    this.userService.getUsers().subscribe(users => {
        this.users = users;
        this.showDrivers();
    })
    // this.startNavigation();
}

showDrivers(){
    for(const user of this.users){
        let userLatLng = {lat: user.latitude, lng: user.longitude};
        let marker = new google.maps.Marker({
            map: this.map,
            label: user.name,
            animation: google.maps.Animation.DROP,
            position: userLatLng
         });
        let content = "<h4>" + user.name + "</h4>";

        this.addInfoWindow(marker, content);
    }
}

loadMap() {
    navigator.geolocation.getCurrentPosition(position => {

        let latlng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);

        const mapOptions = {
            center: latlng,
            zoom: 15,
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            streetViewControl: false
        };

        this.map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);

        let marker = new google.maps.Marker({
        })
    }, (err) => {
        console.error(err);
    });
}

addMarker() {

    let marker = new google.maps.Marker({
        map: this.map,
        label: this.labels[this.labelIndex++ % this.labels.length],
        animation: google.maps.Animation.DROP,
        position: this.map.getCenter()
    });

    let content = "<h4>Information!</h4>";

    this.addInfoWindow(marker, content);
}

addInfoWindow(marker, content) {

    let infoWindow = new google.maps.InfoWindow({
        content: content
    });

    google.maps.event.addListener(marker, 'click', () => {
        infoWindow.open(this.map, marker);
    });

}

startNavigation() {

    navigator.geolocation.getCurrentPosition(position => {

        const directionsService = new google.maps.DirectionsService;
        const directionsDisplay = new google.maps.DirectionsRenderer;

        directionsDisplay.setMap(this.map);
        directionsDisplay.setPanel(this.directionPanel.nativeElement);

        directionsService.route({
            origin: { lat: position.coords.latitude, lng: position.coords.longitude },
            destination: { lat: 39.685259, lng: -75.744364 },
            travelMode: google.maps.TravelMode['DRIVING']
        }, (res, status) => {
            if (status === google.maps.DirectionsStatus.OK) {
                directionsDisplay.setDirections(res);
            } else {
                console.log("Error Loading Directions");
            }
        });

    });
}

}