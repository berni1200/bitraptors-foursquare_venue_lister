package hazi.foursquarevenuelister

import android.app.ListActivity
import android.os.Bundle

class MyListActivity : ListActivity(){

    var latitude = "40.7463956"
    var longitude = "-73.9852992"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
    }

}