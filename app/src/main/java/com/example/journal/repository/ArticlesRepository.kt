package com.example.journal.repository

import com.example.journal.JournalApplication
import com.example.journal.models.Article
import com.example.journal.models.ArticleDetail
import com.example.journal.network.ArticlesApi
import com.example.journal.network.NetworkListener
import com.google.gson.Gson

object ArticlesRepository : NetworkListener {

    private var articlesRepoList: Array<Article>? = null
    private var articleRepoDetail: ArticleDetail? = null

    var articleListener : ArticleRepositoryListener? = null
    var application : JournalApplication? = null


    fun getArticles() : List<Article>? {
        application?.let { ArticlesApi(it).fetchArticles(this) }
        return articlesRepoList?.toList()
    }

    fun getSingleArticle(id: Long): ArticleDetail? {
        application?.let { ArticlesApi(it).fetchAnArticle(this, id) }
        return articleRepoDetail
    }

    override fun setListOfArticles(response: String) {
        try {
            articleListener?.getArticlesList(Gson().fromJson(response, Array<Article>::class.java).toList())

        } catch (exception: Exception) {
            //Maybe log this to Crashlytics
        }
    }

    override fun setArticleDetails(response: String) {

        try {
            articleListener?.getArticleDetail(Gson().fromJson(response, ArticleDetail::class.java))

        } catch (exception: Exception) {
             //Maybe log this to Crashlytics
        }
    }

    override fun onError(error: Int) {
        articleListener?.getErrorCode(error)
    }

    interface ArticleRepositoryListener {

        fun getArticlesList(articles: List<Article>)

        fun getArticleDetail(article: ArticleDetail)

        fun getErrorCode(code: Int)
    }
}