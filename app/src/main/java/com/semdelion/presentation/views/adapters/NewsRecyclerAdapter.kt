package com.semdelion.presentation.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.semdelion.R
import com.semdelion.domain.models.News
import com.semdelion.presentation.navigation.NewsNavigationArg
import com.semdelion.presentation.views.NewsFragmentDirections

class NewsRecyclerAdapter:
    ListAdapter<News, NewsRecyclerAdapter.NewsViewHolder>(NewsComparator()) {

    class NewsComparator: DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.news_title)
        val imageView: ImageView = itemView.findViewById(R.id.news_imageview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout._template_news_item, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.titleTextView.text = getItem(position).title

        Glide.with(holder.itemView.context)
            .load(getItem(position).imageURL)
            .placeholder(R.drawable.ic_news_placeholder)
            .into(holder.imageView)

        holder.itemView.setOnClickListener {
            val navArg = NewsNavigationArg(getItem(position).title,
                getItem(position).link,
                getItem(position).creator,
                getItem(position).description,
                getItem(position).content,
                getItem(position).pubDate,
                getItem(position).imageURL)
            val directions = NewsFragmentDirections.actionNewsFragmentToNewsDetailsFragment(navArg)
            it.findNavController().navigate(directions)
        }
    }
}

