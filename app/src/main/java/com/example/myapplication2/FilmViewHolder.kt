import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication2.Film
import com.example.myapplication2.R

class FilmViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title = itemView.findViewById<TextView>(R.id.title)
    private val poster = itemView.findViewById<ImageView>(R.id.poster)
    private val description = itemView.findViewById<TextView>(R.id.description)

    fun bind(film: Film) {
        title.text = film.title
        description.text = film.description

        Glide.with(itemView)
    .load(film.poster)
    .centerCrop()
    .into(poster)

    }
}