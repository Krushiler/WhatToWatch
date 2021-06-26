package com.example.whattowatch.ui.films

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.whattowatch.R


class FilmsAdapter(private val films: MutableList<Film?>, private val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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

    internal class ProgressViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var progressBar: ProgressBar

        init {
            progressBar = v.findViewById<View>(R.id.progressBar1) as ProgressBar
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        /*val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.film_row_item, parent, false)*/

        val vh: RecyclerView.ViewHolder
        vh = if (viewType == 0) {
            val v: View = LayoutInflater.from(parent.context).inflate(
                R.layout.film_row_item, parent, false
            )
            ItemViewHolder(v)
        } else {
            val v: View = LayoutInflater.from(parent.context).inflate(
                R.layout.progressbar_item, parent, false
            )
            ProgressViewHolder(v)
        }
        return vh

//        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.titleTV.text = films[position]?.title
            holder.descriptionTV.text = films[position]?.description
            Glide.with(context)
                .load(films[position]?.multimedia?.imageSource)
                .apply(
                    RequestOptions().override(
                        holder.filmImageView.layoutParams.width,
                        holder.filmImageView.layoutParams.height
                    )
                )
                .into(holder.filmImageView)
        }else if (holder is ProgressViewHolder){
            holder.progressBar.isIndeterminate = true;
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (films[position] != null) 0 else 1
    }

    override fun getItemCount(): Int {
        return films.size
    }
}