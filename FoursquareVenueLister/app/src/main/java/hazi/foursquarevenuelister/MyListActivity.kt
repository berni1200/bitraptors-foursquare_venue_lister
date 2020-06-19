package hazi.foursquarevenuelister

import android.app.ListActivity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hazi.foursquarevenuelister.model.nearVenues.NearVenuesResponse
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyListActivity : AppCompatActivity(){

    var latitude = "40.7463956"
    var longitude = "-73.9852992"

    private val URL_BASE = "https://api.foursquare.com/"

    lateinit var ids : List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val retrofit = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val foursquare = retrofit.create(FoursquareService::class.java)
        val venuesCall = foursquare.searchNearVenues("$latitude,$longitude", "Chicago, IL")
        venuesCall.enqueue(object: Callback<NearVenuesResponse> {
            override fun onFailure(call: Call<NearVenuesResponse>, t: Throwable) {
                Toast.makeText(this@MyListActivity, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<NearVenuesResponse>, response: Response<NearVenuesResponse>) {
                Toast.makeText(this@MyListActivity, response.toString(), Toast.LENGTH_LONG).show()
            }
        })


    }

}