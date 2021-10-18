package io.github.zagori.nytimes.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import coil.load
import io.github.zagori.nytimes.databinding.ItemArticleBinding
import io.github.zagori.nytimes.models.Doc

class DocViewHolder(
    private val binding: ItemArticleBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(doc: Doc){

        // set the text view
        binding.txtTitle.text = doc.headline.headline
        binding.txtSubtitle.text = doc.subtitle
        binding.txtPublishedDate.text = doc.pubDate

        // set the image
        doc.multimedia.firstOrNull()?.imageUrl?.let { binding.image.load(it) }
    }
}