package com.example.journal.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.example.journal.R
import com.example.journal.databinding.ViewCardBinding
import com.example.journal.models.Image
import com.example.journal.utils.ImagesUtils

class ArticleItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : CardView(context, attrs, defStyleAttr) {

    var binding: ViewCardBinding? = null

    init {
        binding = DataBindingUtil
            .inflate(LayoutInflater.from(context), R.layout.view_card, this, true)
    }
}

@BindingAdapter("articleId")
fun setCardIdBinding(cardView : ArticleItemView , textInput: String){
    cardView.binding?.cardId?.visibility = if (textInput.isEmpty()) View.GONE else View.VISIBLE
    cardView.binding?.cardId?.text = textInput
}

@BindingAdapter("articleImage")
fun setCardImageBinding(cardView : ArticleItemView , imageInput: Image?){
    cardView.binding?.cardImage?.visibility = if (imageInput?.url == null) View.GONE else View.VISIBLE
    cardView.binding?.root?.visibility= if (imageInput?.url == null) View.GONE else View.VISIBLE
    cardView.binding?.root?.let { view ->
        imageInput?.url?.let {
            ImagesUtils.setSettingsImageUrlFitCenterMessage(view, it, cardView.binding!!.cardImage)
        }
    }
}

@BindingAdapter("articleTitle")
fun setCardTitleBinding(cardView : ArticleItemView , title: String){
    cardView.binding?.cardText?.text = title?:"No title"
}

@BindingAdapter("articleMedia")
fun setCardMediaBinding(cardView : ArticleItemView , media: Boolean){
    cardView.binding?.mediaId?.visibility =  if (media)  View.VISIBLE else View.INVISIBLE
}