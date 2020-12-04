package com.example.journal.models

data class Article(val id: Long?, val image: Image?, val title: String?, val media: Boolean?)

data class Image(val width:Long?, val height: Long?, val url: String?)

data class ArticleDetail(val id: Long?, val title: String?, val body: String?)