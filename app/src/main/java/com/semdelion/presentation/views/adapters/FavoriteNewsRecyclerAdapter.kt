package com.semdelion.presentation.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.semdelion.R
import com.semdelion.databinding.TemplateNewsItemBinding
import com.semdelion.domain.models.NewsModel
import com.semdelion.presentation.navigation.NewsNavigationArg

class FavoriteNewsRecyclerAdapter(private val getDirections: (navArg: NewsNavigationArg) -> NavDirections) :
    ListAdapter<NewsModel, FavoriteNewsRecyclerAdapter.NewsViewHolder>(NewsComparator()) {

    private lateinit var bindingView: TemplateNewsItemBinding

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
        bindingView = TemplateNewsItemBinding.inflate(inflater, parent, false)
        return NewsViewHolder(bindingView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = getItem(position)
        holder.titleTextView.text = item.title

        Glide.with(holder.itemView.context)
            .load(item.imageURL)
            .placeholder(R.drawable.ic_news_placeholder)
            .into(holder.imageView)

        holder.itemView.setOnClickListener {
            val navArg = NewsNavigationArg(
                title = item.title,
                link = item.link,
                creator = item.creator,
                content = item.content,
                pubDate = item.pubDate,
                imageURL = item.imageURL
            )

            it.findNavController().navigate(getDirections(navArg))
        }
    }
}
