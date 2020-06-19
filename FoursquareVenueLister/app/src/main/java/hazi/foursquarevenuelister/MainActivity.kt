package hazi.foursquarevenuelister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goToSecond(view: View) {
        var intent = Intent(this, MyListActivity::class.java)
        startActivity(intent)
    }
}