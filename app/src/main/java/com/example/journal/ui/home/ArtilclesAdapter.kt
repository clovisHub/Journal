package com.example.journal.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.journal.R
import com.example.journal.databinding.SingleArticleItemBinding
import com.example.journal.models.Article

class ArticlesAdapter(private val listener: TaskListListener): RecyclerView.Adapter<ArticlesAdapter.ArticleHolder>() {

    private var articlesList: MutableList<Article> = mutableListOf()

    fun setArticlesList(articles: List<Article>) {
        articlesList.clear()
        articlesList.addAll(articles.filter { it.image?.url?.isNotEmpty() == true})
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ArticleHolder {
        val view  = DataBindingUtil.inflate<SingleArticleItemBinding>(LayoutInflater.from(parent.context),
            R.layout.single_article_item,
            parent, false)

        return ArticleHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        holder.view.article = articlesList[position]
        holder.view.root.setOnClickListener {
            listener.onClick(articlesList[position].id)
        }
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    inner class ArticleHolder(val view: SingleArticleItemBinding) : RecyclerView.ViewHolder(view.root)

    interface TaskListListener {
        fun  onClick(articleId: Long?)
    }

}