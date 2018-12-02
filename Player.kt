package com.shota.androidquiz.aumap.player

import android.content.Context
import android.location.Location
import android.widget.Toast
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.shota.androidquiz.aumap.R
import com.shota.androidquiz.aumap.facultyManager.Faculty

class Player(){
    private var lat:Double = 0.0;
    private var long:Double = 0.0;
    constructor(lat:Double,long: Double) : this() {
        this.lat = lat;
        this.long = long;
    }

    fun getLatLng(): LatLng {
        return LatLng(lat,long);
    }

    fun collectFaculty(faculty: Faculty, context: Context){
        faculty.collected();
        Toast.makeText(context, faculty.abberviation+ " Pin collected", Toast.LENGTH_LONG).show();
    }
    fun getGoogleMapMarker(): MarkerOptions? {
        return MarkerOptions()
                .position(this.getLatLng())
                .title("Me")
                .snippet("Here is my location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.me))
    }
    fun getLocation(): Location {
        var userLocation = Location("Start");
        userLocation!!.latitude = this.lat
        userLocation!!.longitude = this.long
        return userLocation;
    }

    fun walkTo(faculty: Faculty, steps:Int): LatLng {
        var latDist = (faculty.lat - this.lat)/steps;
        var longDist = (faculty.long - this.long)/steps;

        this.lat += latDist;
        this.long += longDist;

        return LatLng(latDist,longDist);
    }
    fun setLocationToDefoult(){
        this.lat = 13.612320;
        this.long = 100.836808;
    }
    fun changeLocation(location: Location?) {
        this.lat = location!!.latitude;
        this.long = location!!.longitude;
    }

    fun resetLocation() {
        this.lat = 0.0;
        this.long = 0.0;
    }
}