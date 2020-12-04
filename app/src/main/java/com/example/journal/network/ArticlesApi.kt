package com.example.journal.network

import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.journal.JournalApplication

class ArticlesApi (private val journalApplication: JournalApplication){

    fun fetchArticles(listener: NetworkListener) {
        makeRequest(listener)
    }

    fun fetchAnArticle(listener: NetworkListener, id: Long?) {
        makeRequest(listener, id)
    }

    private fun makeRequest(listener: NetworkListener, id : Long? = null) {

        var method = 0
        val url = if(id == null) {
                       method = Request.Method.GET
                       "http://static.navigamobile.com/fizzbuzz/fizzbuzz.json.gz"
                  }
                  else  {
                        method = Request.Method.GET
                        "http://static.navigamobile.com/fizzbuzz/id/$id.json.gz"
                  }

        val queue = Volley.newRequestQueue(journalApplication)

        val stringRequest = StringRequest(method, url,
            Response.Listener<String> { response ->
                if(id == null) listener.setListOfArticles(response)
                else listener.setArticleDetails(response)
            },
            Response.ErrorListener {
                Log.e("message", it.message.toString())
                Log.e("message", it.networkResponse.statusCode.toString())
                listener.onError(it.networkResponse.statusCode)
            })

        queue.add(stringRequest)
    }
}

interface NetworkListener {

    fun setListOfArticles(response: String)

    fun setArticleDetails(response: String)

    fun onError(error: Int)
}
