package com.example.whattowatch.ui.films

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.whattowatch.R


class FilmsAdapter(private val films: MutableList<Film>, private val context: Context?) : RecyclerView.Adapter<FilmsAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTV: TextView
        val descriptionTV: TextView
        val filmImageView: ImageView

        init {
            titleTV = itemView.findViewById<View>(R.id.filmTitle) as TextView
            descriptionTV = itemView.findViewById<View>(R.id.filmDescription) as TextView
            filmImageView = itemView.findViewById<View>(R.id.filmImage) as ImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.film_row_item, parent, false)



        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.titleTV.text = films[position].title
        holder.descriptionTV.text = films[position].description
        Glide.with(context)
            .load(films[position].multimedia.imageSource)
            .apply(RequestOptions().override(holder.filmImageView.layoutParams.width, holder.filmImageView.layoutParams.height))
            .into(holder.filmImageView)


    }

    override fun getItemCount(): Int {
        return films.size
    }
}