package com.busanit.ch12_network.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.busanit.ch12_network.R
import com.busanit.ch12_network.databinding.ItemArticleBinding
import com.busanit.ch12_network.databinding.ItemPostBinding
import com.busanit.ch12_network.retrofit.adapter.CommentAdapter
import com.busanit.ch12_network.retrofit.model.Comment


class ArticleAdapter(val articles: List<Article>) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>(){

    class ArticleViewHolder(val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.textViewTitle.text = article.title
            binding.textViewDescription.text = article.description

            // 이미지는 Glide로 바인딩
            Glide.with(binding.imageView.context)
                .load(article.urlToImage)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(binding.imageView)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ArticleViewHolder(binding)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articles[position])
    }
}