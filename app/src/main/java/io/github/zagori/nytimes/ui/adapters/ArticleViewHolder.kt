package io.github.zagori.nytimes.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import coil.load
import io.github.zagori.nytimes.databinding.ItemArticleBinding
import io.github.zagori.nytimes.models.Article

class ArticleViewHolder(
    private val binding: ItemArticleBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(article: Article){

        // set the text view
        binding.txtTitle.text = article.title
        binding.txtSubtitle.text = article.subtitle
        binding.txtByline.text = article.byline
        binding.txtPublishedDate.text = article.publishedDate

        // set the image
        article.media?.firstOrNull()?.metadata?.lastOrNull()?.url?.let {
            binding.image.load(it)
        }
    }
}