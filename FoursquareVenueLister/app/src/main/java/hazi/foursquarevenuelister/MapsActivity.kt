package hazi.foursquarevenuelister

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import hazi.foursquarevenuelister.model.nearVenues.NearVenuesResponse
import hazi.foursquarevenuelister.model.nearVenues.Venue
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap

    var latitude = "40.7463956"
    var longitude = "-73.9852992"
    var foodtype = "nottacos"
    private val URL_BASE = "https://api.foursquare.com/"
    var ids : MutableList<String>? = mutableListOf()
    var names : MutableList<String>? = mutableListOf()
    var venues : List<Venue>? = null

    private var locationsList = arrayListOf<String>() //"latitude,longitude"
    private lateinit var currentLocation : LatLng
    lateinit var markers : ArrayList<Marker>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val extras = intent.extras
        foodtype = extras!!["foodtype"] as String
        latitude = extras["latitude"] as String
        longitude = extras["longitude"] as String
        markers = ArrayList<Marker>()

        currentLocation = LatLng(latitude.toDouble(), longitude.toDouble())

        val retrofit = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val foursquare = retrofit.create(FoursquareService::class.java)

        val venuesCall = foursquare.searchNearVenues(foodtype,"$latitude,$longitude")
        venuesCall.enqueue(object: Callback<NearVenuesResponse> {
            override fun onFailure(call: Call<NearVenuesResponse>, t: Throwable) {
                Toast.makeText(this@MapsActivity, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<NearVenuesResponse>, response: Response<NearVenuesResponse>) {
                venues = response.body()?.response?.venues
                if(venues != null){
                    for(i in venues!!){
                        ids?.add(i.id)
                        names?.add(i.name)
                        locationsList.add(i.location.lat.toString() + "," + i.location.lng.toString())
                    }
                }
                if(names == null || names?.size == 0){
                    Toast.makeText(this@MapsActivity, "No venues find nearby with the given type.", Toast.LENGTH_LONG).show()
                }
                var j = 0
                for(i in locationsList){
                    var splitted = i.split(",")
                    var location = LatLng(splitted[0].toDouble(), splitted[1].toDouble())
                    markers.add(mMap.addMarker(MarkerOptions().position(location).title(names?.get(j))))
                    j++
                }
            }
        })

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {


        // Add a marker in Sydney and move the camera
        /*val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/
        mMap = googleMap
        mMap.setOnMarkerClickListener { marker -> onMarkerClick(marker) }
        mMap.addMarker(MarkerOptions().position(currentLocation).title("You are here"))


        var j = 0
        for(i in locationsList){
            var splitted = i.split(",")
            var location = LatLng(splitted[0].toDouble(), splitted[1].toDouble())
            markers[j] = mMap.addMarker(MarkerOptions().position(location).title(names?.get(j)))
            j++
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation))
        mMap.setMinZoomPreference(12.0f)

    }


    override fun onMarkerClick(marker: Marker?): Boolean {
        var j = 0
        for(i in markers){
            if(i == marker){
                break
            }
            j++
        }
        var intent = Intent(this, DetailsActivity::class.java)
        if(marker?.position?.latitude != latitude.toDouble() && marker?.position?.longitude != longitude.toDouble()){
            intent.putExtra("id", ids!!.get(j))
            startActivity(intent)
        }
        return true
    }

}