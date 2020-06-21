package hazi.foursquarevenuelister

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*

class MainActivity : AppCompatActivity(), MyLocationProvider.OnNewLocationAvailable {

    private lateinit var locationHelper: MyLocationProvider
    private var lastLocation: Location? = null
    private var getCurrent : Boolean = true

    var latitude = "40.7463956"
    var longitude = "-73.9852992"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //getCurrent = true
        locationHelper = MyLocationProvider(this, this)
        startLocationMonitoring()
    }

    override fun onResume() {
        super.onResume()
        getCurrent = true
    }

    fun goToSecond(view: View) {
        getCurrent = true
        var intent = Intent(this, MyListActivity::class.java)
        startActivity(intent)
    }

    fun startLocationMonitoring() {
        locationHelper.startLocationMonitoring()
    }

    override fun onStop(){
        locationHelper.stopLocationMonitoring()
        super.onStop()
    }

    override fun onNewLocation(location: Location){
        if(getCurrent){
            latitude = location.latitude.toString()
            longitude = location.longitude.toString()
            getCurrent = false
        }else{
            lastLocation = location

        }

    }


}