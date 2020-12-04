package com.example.journal.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.journal.JournalApplication
import com.example.journal.models.Article
import com.example.journal.models.ArticleDetail
import com.example.journal.repository.ArticlesRepository
import com.example.journal.utils.Event

class HomeViewModel(context: Context) : ViewModel(), ArticlesRepository.ArticleRepositoryListener {

     private val liveListOfArticles: MutableLiveData<Event<List<Article>>> = MutableLiveData()
     private val liveArticle: MutableLiveData<Event<ArticleDetail>> = MutableLiveData()
     private val liveError: MutableLiveData<Event<Int>> = MutableLiveData()

    init {
        ArticlesRepository.articleListener = this
        ArticlesRepository.application = context.applicationContext as JournalApplication
    }

    fun fetchArticlesList()  {
        ArticlesRepository.getArticles()
    }

    fun fetchArticleDetails(id: Long?) {
        id?.let {
            ArticlesRepository.getSingleArticle(id)
        }
    }

    fun observeArticlesList() : LiveData<Event<List<Article>>>{
        return liveListOfArticles
    }

    fun observeArticleDetails() : LiveData<Event<ArticleDetail>>{
        return liveArticle
    }

    fun observeErrorCode() : LiveData<Event<Int>> {
        return liveError
    }

    override fun getArticlesList(articles: List<Article>) {
         liveListOfArticles.postValue(Event(articles))
    }

    override fun getArticleDetail(article: ArticleDetail) {
        liveArticle.postValue(Event(article))
    }

    override fun getErrorCode(code: Int) {
        liveError.postValue(Event(code))
    }
}