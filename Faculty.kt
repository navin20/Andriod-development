package com.shota.androidquiz.aumap.facultyManager

import android.location.Location
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.shota.androidquiz.aumap.player.Player

class Faculty(){
    public var name:String = "";
    public var abberviation:String = "";
    public var logo:Int = 0;
    public var lat:Double = 0.0;
    public var long:Double = 0.0;
    public var isCollected = false;
    constructor(name: String, des: String,image: Int,lat: Double, long: Double, isCatch: Boolean) : this() {
        this.name = name;
        this.abberviation = des;
        this.logo = image;
        this.lat = lat;
        this.long = long;
        this.isCollected = isCatch;
    }
    fun getLatLng(): LatLng {
        return LatLng(this.lat,this.long);
    }

    fun collected() {
        this.isCollected = true;
    }

    fun getGoogleMapMarker(player: Player): MarkerOptions? {
        return MarkerOptions()
                .position(this.getLatLng())
                .title(this.name)
                .snippet(this.abberviation+" distance:" +Math.round(player.getLocation().distanceTo(this.getLocation())) +"m.")
                .icon(BitmapDescriptorFactory.fromResource(this.logo))
    }

    fun getLocation(): Location? {
        var loc = Location("Faculty")
        loc.latitude = this.lat
        loc.longitude = this.long
        return loc;
    }
}