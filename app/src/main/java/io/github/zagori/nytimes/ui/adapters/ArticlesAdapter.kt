package io.github.zagori.nytimes.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.zagori.nytimes.databinding.ItemArticleBinding
import io.github.zagori.nytimes.models.Article

class ArticlesAdapter : RecyclerView.Adapter<ArticleViewHolder>() {

    var articles: List<Article> = emptyList()
        set(value) {
            field = value
            if (field.isNotEmpty()) notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
        ArticleViewHolder(
            ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int = articles.size

}