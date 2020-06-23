package hazi.foursquarevenuelister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_gallery.*

class GalleryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        val extras = intent.extras
        val photosList = extras!!["photosList"] as ArrayList<String>

        rvGallery.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this, 2)
        rvGallery.layoutManager = layoutManager
        val adapter = MyRvAdapter(this, photosList)
        rvGallery.adapter = adapter

    }
}