package com.semdelion.presentation.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.semdelion.R
import com.semdelion.databinding.TemplateNewsItemBinding
import com.semdelion.domain.models.News
import com.semdelion.presentation.navigation.NewsNavigationArg
import com.semdelion.presentation.views.NewsFragmentDirections

class NewsRecyclerAdapter:
    ListAdapter<News, NewsRecyclerAdapter.NewsViewHolder>(NewsComparator()) {

    private lateinit var bindingView: TemplateNewsItemBinding

    class NewsComparator: DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }
    }

    class NewsViewHolder(binding: TemplateNewsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val titleTextView: TextView = binding.newsTitle
        val imageView: ImageView = binding.newsImageview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        bindingView = TemplateNewsItemBinding.inflate(inflater, parent, false)
        return NewsViewHolder(bindingView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.titleTextView.text = getItem(position).title

        Glide.with(holder.itemView.context)
            .load(getItem(position).imageURL)
            .placeholder(R.drawable.ic_news_placeholder)
            .into(holder.imageView)

        holder.itemView.setOnClickListener {
            val navArg = NewsNavigationArg(
                getItem(position).title,
                getItem(position).link,
                getItem(position).creator,
                getItem(position).content,
                getItem(position).pubDate,
                getItem(position).imageURL)
            val directions = NewsFragmentDirections.actionNewsFragmentToNewsDetailsFragment(navArg)
            it.findNavController().navigate(directions)
        }
    }
}

