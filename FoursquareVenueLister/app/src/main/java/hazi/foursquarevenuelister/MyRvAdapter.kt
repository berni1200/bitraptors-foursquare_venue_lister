package hazi.foursquarevenuelister
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.gallery_grid_item.view.*


class MyRvAdapter(var context : Context, var photosList : ArrayList<String>) : RecyclerView.Adapter<MyRvAdapter.PhotoHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        var view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_grid_item, parent, false);
        return MyRvAdapter.PhotoHolder(view)
    }

    override fun getItemCount(): Int = photosList.size

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        val photo = photosList[position]
        holder.bindPhoto(photo)
    }

    class PhotoHolder(view: View) : RecyclerView.ViewHolder(view){
        private var view: View = view
        private var photo : String? = null

        fun bindPhoto(photo: String?) {
            this.photo = photo
            DownloadImageTask(view.img).execute(photo)
        }
    }

}