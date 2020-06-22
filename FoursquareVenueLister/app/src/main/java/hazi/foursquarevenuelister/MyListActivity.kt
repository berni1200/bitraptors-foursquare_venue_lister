package hazi.foursquarevenuelister

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ListActivity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import hazi.foursquarevenuelister.model.DetailsResponse
import hazi.foursquarevenuelister.model.nearVenues.NearVenuesResponse
import hazi.foursquarevenuelister.model.nearVenues.Venue
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.simple_list_item.*
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyListActivity : Activity(){

    var latitude = "40.7463956"
    var longitude = "-73.9852992"
    var foodtype = "nottacos"

    private val URL_BASE = "https://api.foursquare.com/"

    var ids : MutableList<String>? = mutableListOf()
    var names : MutableList<String>? = mutableListOf()
    var venues : List<Venue>? = null

    lateinit var listview : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        listview = findViewById(R.id.listview)

        val extras = intent.extras
        foodtype = extras!!["foodtype"] as String
        latitude = extras["latitude"] as String
        longitude = extras["longitude"] as String

        val retrofit = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val foursquare = retrofit.create(FoursquareService::class.java)
        //val venuesCall = foursquare.searchNearVenues("tacos", "Chicago, IL")
        val venuesCall = foursquare.searchNearVenues(foodtype,"$latitude,$longitude")
        venuesCall.enqueue(object: Callback<NearVenuesResponse> {
            override fun onFailure(call: Call<NearVenuesResponse>, t: Throwable) {
                Toast.makeText(this@MyListActivity, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<NearVenuesResponse>, response: Response<NearVenuesResponse>) {
                //Toast.makeText(this@MyListActivity, response.body().toString(), Toast.LENGTH_LONG).show()
                venues = response.body()?.response?.venues
                if(venues != null){
                    for(i in venues!!){
                        ids?.add(i.id)
                        names?.add(i.name)
                    }
                    if(ids != null){
                        var arrayAdapter = ArrayAdapter<String>(this@MyListActivity,
                            R.layout.simple_list_item, R.id.listText, names!!)
                        listview.adapter = arrayAdapter
                    }
                }
                println(response)
                //textView2.text = venues?.size.toString()
            }
        })

        /*fun doNothing(){ //ez kell majd a részletek lekéréséhez
            val foursquare = retrofit.create(FoursquareService::class.java)
            if(venues != null){
                val detailsCall = foursquare.detailsOfVenue(venues!![0].id)
                detailsCall.enqueue(object: Callback<DetailsResponse>{
                    override fun onFailure(call: Call<DetailsResponse>, t: Throwable) {
                        Toast.makeText(this@MyListActivity, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(
                        call: Call<DetailsResponse>,
                        response: Response<DetailsResponse>
                    ) {
                        Toast.makeText(this@MyListActivity, response.body()?.response?.venue?.name, Toast.LENGTH_LONG).show()
                    }

                })
            }
        }*/
        listview.setOnItemClickListener { parent, view, position, id ->
            //Toast.makeText(this, id.toString(),Toast.LENGTH_SHORT).show()
            var intent = Intent(this, DetailsActivity::class.java)
            //var ids2 = ids?.toTypedArray()

            intent.putExtra("id", ids!![id.toInt()])
            //intent.putExtra("ids", ids2)
            startActivity(intent)
        }

    }

}