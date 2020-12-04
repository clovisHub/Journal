package com.example.journal.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import com.example.journal.ui.home.ArticlesListFragment

object ErrorUtils {

    fun handleErrorCode(context: Context, code:Int?): Dialog? {
        return if(code == null) null
            else AlertDialog.Builder(context)
            .setMessage(getMessage(code))
            .setCancelable(true)
            .setOnDismissListener {
                NavigationUtils.getFragment(ArticlesListFragment.newInstance())
            }
            .create()

    }

    private fun getMessage(code: Int?) : String {
        return when (code) {
            400 -> "Bad Request"
            401 -> "Unauthorized"
            403 -> "Forbidden"
            404 -> "Not Found"
            500 -> "Internal Server Error"
            502 -> "Bad Gateway"
            503 -> "Service Unavailable"
            504 -> "Service Unavailable"
            else -> {
                " Unknown Error"
            }
        }
    }
}