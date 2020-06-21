package hazi.foursquarevenuelister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import hazi.foursquarevenuelister.model.DetailsResponse
import hazi.foursquarevenuelister.model.venuePhotos.VenuePhotos
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_gallery.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GalleryActivity : AppCompatActivity() {

    private val URL_BASE = "https://api.foursquare.com/"
    private var photosList = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        val extras = intent.extras
        //val photosList = extras!!["photosList"] as ArrayList<String>
        val id = extras!!["id"] as String

        val retrofit = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val foursquare = retrofit.create(FoursquareService::class.java)
        val photosCall = foursquare.photosOfVenue(id)
        photosCall.enqueue(object: retrofit2.Callback<VenuePhotos> {
            override fun onFailure(call: Call<VenuePhotos>, t: Throwable) {
                Toast.makeText(this@GalleryActivity, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<VenuePhotos>, response: Response<VenuePhotos>) {
                for(i in response.body()?.response?.photos?.items!!){
                    photosList.add(i.prefix + "original" + i.suffix)
                }

            }

        })

        rvGallery.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this, 2)
        rvGallery.layoutManager = layoutManager
        val adapter = MyRvAdapter(this, photosList)
        rvGallery.adapter = adapter

    }
}