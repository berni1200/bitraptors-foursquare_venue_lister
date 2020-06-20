package hazi.foursquarevenuelister

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Toast
import hazi.foursquarevenuelister.model.DetailsResponse
import kotlinx.android.synthetic.main.activity_details.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Request.Builder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

class DetailsActivity : AppCompatActivity() {

    private val URL_BASE = "https://api.foursquare.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val extras = intent.extras
        val id = extras!!["id"] as String
        Toast.makeText(this@DetailsActivity, id.toString(), Toast.LENGTH_LONG).show()

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
                Toast.makeText(this@DetailsActivity, response.body()?.response?.venue?.name, Toast.LENGTH_LONG).show()
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

                if(response.body()?.response?.venue?.bestPhoto?.prefix != null && response.body()?.response?.venue?.bestPhoto?.suffix != null){
                    val responseUrl = response.body()?.response?.venue?.bestPhoto?.prefix + "original" + response.body()?.response?.venue?.bestPhoto?.suffix

                    DownloadImageTask(ivBestPhoto).execute(responseUrl)
                }else{
                    //kell egy "notfound" kép az imageview-hoz
                }

            }

        })
    }
}

