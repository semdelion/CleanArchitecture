package com.semdelion.presentation.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.semdelion.R
import com.semdelion.databinding.TemplateNewsItemBinding
import com.semdelion.domain.models.NewsModel

class NewsRecyclerAdapter(private val itemClickListener: (NewsModel) -> Unit) :
    ListAdapter<NewsModel, NewsRecyclerAdapter.NewsViewHolder>(NewsComparator()), View.OnClickListener {

    private lateinit var binding: TemplateNewsItemBinding

    class NewsComparator : DiffUtil.ItemCallback<NewsModel>() {
        override fun areItemsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
            return oldItem == newItem
        }
    }

    class NewsViewHolder(binding: TemplateNewsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val titleTextView: TextView = binding.newsTitle
        val imageView: ImageView = binding.newsImageview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = TemplateNewsItemBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.tag = item
        holder.titleTextView.text = item.title

        Glide.with(holder.itemView.context)
            .load(item.imageURL)
            .placeholder(R.drawable.ic_news_placeholder)
            .into(holder.imageView)
    }

    override fun onClick(view: View) {
        val news = view.tag as NewsModel
        itemClickListener(news)
    }
}

