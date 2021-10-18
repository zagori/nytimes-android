package io.github.zagori.nytimes.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import coil.load
import io.github.zagori.nytimes.databinding.ItemArticleBinding
import io.github.zagori.nytimes.models.Doc
import io.github.zagori.nytimes.utils.Constants
import io.github.zagori.nytimes.utils.format

class DocViewHolder(
    private val binding: ItemArticleBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(doc: Doc){

        // set the text view
        binding.txtTitle.text = doc.headline.headline
        binding.txtSubtitle.text = doc.subtitle
        binding.txtPublishedDate.text = doc.date?.format("MMM dd, yyyy 'at' HH:mm")

        // set the image
        doc.multimedia.firstOrNull()?.imageUrl?.let { binding.image.load(Constants.WEBSITE_BASE_URL + it) }
    }
}