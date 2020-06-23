package hazi.foursquarevenuelister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Toast
import hazi.foursquarevenuelister.model.DetailsResponse
import kotlinx.android.synthetic.main.activity_details.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsActivity : AppCompatActivity() {

    private val URL_BASE = "https://api.foursquare.com/"

    private var photosList = arrayListOf<String>()
    private lateinit var id : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val extras = intent.extras
        id = extras!!["id"] as String
        bGallery.isEnabled = true

        val retrofit = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val foursquare = retrofit.create(FoursquareService::class.java)
        val detailsCall = foursquare.detailsOfVenue(id)
        detailsCall.enqueue(object: retrofit2.Callback<DetailsResponse> {
            override fun onFailure(call: Call<DetailsResponse>, t: Throwable) {
                Toast.makeText(this@DetailsActivity, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<DetailsResponse>,
                response: Response<DetailsResponse>
            ) {
                if(response.body()?.response?.venue?.location?.address == null){
                    tvAddress.text = "Address: -"
                }else{
                    tvAddress.text = "Address: " + response.body()?.response?.venue?.location?.address
                }
                if(response.body()?.response?.venue?.description == null){
                    tvDescritption.text = "Description: -"
                }else{
                    tvDescritption.text = "Description: " + response.body()?.response?.venue?.description
                    tvDescritption.movementMethod = ScrollingMovementMethod()
                }
                tvVenueName.text = response.body()?.response?.venue?.name
                if(response.body()?.response?.venue?.rating == null){
                    tvRating.text = "Rating: -"
                }else{
                    tvRating.text = "Rating: "+ response.body()?.response?.venue?.rating.toString()
                }
                if(response.body()?.response?.venue?.popular?.isOpen != null){
                    if(response.body()?.response?.venue?.popular?.isOpen!!){
                        tvOpen.text = "Now open"
                    }else{
                        tvOpen.text = "Now closed"
                    }
                }
                if(response.body()?.response?.venue?.popular?.isLocalHoliday != null){
                    if(response.body()?.response?.venue?.popular?.isLocalHoliday!!){
                        tvIsHoliday.text = "Local holiday destination"
                    }
                }
                var contact = response.body()?.response?.venue?.contact
                if(contact != null){
                    if(contact.facebookName != null){
                        tvFacebookContact.text = "Facebook: " + contact?.facebookName!!
                    }else{
                        tvFacebookContact.text = "Facebook: -"
                    }
                    if(contact.instagram != null){
                        tvInstagramContact.text = "Instagram: " + contact?.instagram!!
                    }else{
                        tvInstagramContact.text = "Instagram: -"
                    }
                    if(contact.formattedPhone != null){
                        tvPhoneContact.text = "Phone: " + contact?.formattedPhone!!
                    }else{
                        tvPhoneContact.text = "Phone: -"
                    }
                }
                if(response.body()?.response?.venue?.url != null){
                    tvUrlContact.text = "${response.body()?.response?.venue?.url!!}"
                }


                if(response.body()?.response?.venue?.bestPhoto?.prefix != null && response.body()?.response?.venue?.bestPhoto?.suffix != null){
                    val responseUrl = response.body()?.response?.venue?.bestPhoto?.prefix + "original" + response.body()?.response?.venue?.bestPhoto?.suffix

                    DownloadImageTask(ivBestPhoto).execute(responseUrl)
                }else{
                    //kell egy "notfound" kép az imageview-hoz
                }
                if(response.body()?.response?.venue?.photos?.groups != null){
                    for(i in response.body()?.response?.venue?.photos?.groups!!){
                        for(j in i.items){
                            photosList.add(j.prefix + "original" + j.suffix)
                        }
                    }
                }

                //adding photos from listed items that contains the venue

                if(response.body()?.response?.venue?.listed != null && response.body()?.response?.venue?.listed?.groups != null){
                    for(i in response.body()?.response?.venue?.listed?.groups!!){
                        for (j in i.items) {
                            if(j.photo != null){
                                photosList.add(j.photo.prefix + "original" + j.photo.suffix)
                            }
                        }
                    }
                }

                if(photosList.size == 0){
                    bGallery.isEnabled = false
                }
                //csak képek lekérése
                /*val photosCall = foursquare.photosOfVenue(id)
                photosCall.enqueue(object: retrofit2.Callback<VenuePhotos> {
                    override fun onFailure(call: Call<VenuePhotos>, t: Throwable) {
                        Toast.makeText(this@DetailsActivity, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<VenuePhotos>, response: Response<VenuePhotos>) {
                        for(i in response.body()?.response?.photos?.items!!){
                            photosList.add(i.prefix + "original" + i.suffix)
                        }
                    }


                })*/
            }

        })
    }

    fun showGallery(view: View) {
        var intent = Intent(this, GalleryActivity::class.java)
        intent.putStringArrayListExtra("photosList", photosList)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}

