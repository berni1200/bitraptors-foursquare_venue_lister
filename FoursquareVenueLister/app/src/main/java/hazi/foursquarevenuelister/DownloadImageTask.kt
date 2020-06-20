package hazi.foursquarevenuelister

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import java.net.URL

class DownloadImageTask(var imageView: ImageView) : AsyncTask<String, Void, Bitmap>() { //AsyncTask deprecated from API30, use coroutines instead


    override fun doInBackground(vararg params: String?): Bitmap {
        val paramUrl = params[0]
        val url = URL(paramUrl)
        return BitmapFactory.decodeStream(url.openConnection().getInputStream())
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        imageView.setImageBitmap(result)
    }
}