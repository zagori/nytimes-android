package io.github.zagori.nytimes.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.zagori.nytimes.databinding.ItemArticleBinding
import io.github.zagori.nytimes.models.Article
import io.github.zagori.nytimes.models.Doc

class ArticlesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var vType = ViewTypeArticle
        set(value) {
            field = value
            notifyItemChanged(0)
        }

    var articles: List<Article> = emptyList()
        set(value) {
            field = value
            if (field.isNotEmpty()) vType = ViewTypeArticle
        }

    var docs: List<Doc> = emptyList()
        set(value) {
            field = value
            if (field.isNotEmpty()) vType = ViewTypeDoc
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == ViewTypeArticle) ArticleViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
        else DocViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ArticleViewHolder -> holder.bind(articles[position])
            is DocViewHolder -> holder.bind(docs[position])
        }
    }

    override fun getItemCount(): Int = when (vType) {
        ViewTypeArticle -> articles.size
        ViewTypeDoc -> docs.size
        else -> 1
    }

    override fun getItemViewType(position: Int): Int = vType

    companion object {
        const val ViewTypeArticle = 0
        const val ViewTypeDoc = 1
    }

}