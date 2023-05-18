package com.semdelion.presentation.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.semdelion.R
import com.semdelion.domain.models.News

class NewsRecyclerAdapter(private var _news: MutableList<News>):
    RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder>() {

    fun setMovieList(news: List<News>) {
        _news = news.toMutableList()
        notifyDataSetChanged()
        //notifyItemRangeInserted(int, int) DIFFUtil
        // TODO https://medium.com/tech-carnot/making-recyclerview-efficient-using-list-adapter-with-diffutil-4a06d4583ea4
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout._template_news_item, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.titleTextView.text = _news[position].title
        Glide.with(holder.itemView.context).load(_news[position].imageURL).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return _news.size
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.news_title)
        val imageView: ImageView = itemView.findViewById(R.id.news_imageview)
    }
}

