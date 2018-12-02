package com.shota.androidquiz.aumap

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.shota.androidquiz.aumap.facultyManager.FacultyIndex
import com.shota.androidquiz.aumap.player.Player

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var index = FacultyIndex();
    private var player = Player();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        index.load();
        player.setLocationToDefoult()
        checkPermission()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    fun checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        888)
                return
            }
        }

        getUserLocation()
    }
    //var userLocation: Location? = null
    inner class MyLocationListener: LocationListener {

        constructor() {
//            userLocation = Location("Start")
//            userLocation!!.latitude = 0.0
//            userLocation!!.longitude = 0.0
            player.resetLocation()
        }

        override fun onLocationChanged(location: Location?) {
//            userLocation = location
            player.changeLocation(location);
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderEnabled(provider: String?) {
            //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderDisabled(provider: String?) {
            //To change body of created functions use File | Settings | File Templates.
        }
    }
    fun getUserLocation() {

        Toast.makeText(this, "User location access ON", Toast.LENGTH_LONG).show()

        var myLocation = MyLocationListener()
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3, 3f, myLocation)

        var myThread = MyThread()
        myThread.start()
    }
    var oldLocation: Location? = null
    inner class MyThread: Thread {

        constructor():super() {
            oldLocation = Location("Start")
            oldLocation!!.latitude = 0.0
            oldLocation!!.longitude = 0.0
        }

        override fun run() {
            while (true) {
                try {
                    if (oldLocation!!.distanceTo(player.getLocation()) == 0f) {
                        continue
                    }

                    oldLocation = player.getLocation()

                    runOnUiThread {
                        mMap.clear()

                        var currentLocation = player.getLatLng()
                        mMap!!.addMarker(player.getGoogleMapMarker())
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 18f))

                        for (i in 0..index.size-1) {
                            var newFaculty = index.getFaculty(i)!!
                            if (!newFaculty.isCollected) {
                                var pokemonLocation = newFaculty.getLatLng()
                                mMap!!.addMarker(newFaculty!!.getGoogleMapMarker(player))

                                var pokemonL = newFaculty.getLocation();
                                if (player.getLocation()!!.distanceTo(pokemonL) < 5) {
                                    player.collectFaculty(newFaculty,applicationContext);
                                }
                            }
                        }
                    }
                    Thread.sleep(1000)
                } catch (ex: Exception) {}
            }
        }

    }
}
