package com.example.journal.ui.parents

import android.app.ProgressDialog
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.journal.ui.home.ArticlesListFragment
import com.example.journal.ui.home.HomeActivity
import com.example.journal.ui.home.HomeViewModel
import com.example.journal.utils.ErrorUtils
import com.example.journal.utils.NavigationUtils

@Suppress("DEPRECATION")
abstract class BaseFragment: OriginFragment() {
    protected var homeViewModel: HomeViewModel? = null
    private var progressDialog : ProgressDialog?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog =  ProgressDialog(context)
        activity?.let {home ->
            (home as HomeActivity).viewModel.observeErrorCode().observe(getLiveCycleOwner(), Observer {
                it?.getContentIfNotHandled<Int>().let {code ->
                    dismiss()
                    ErrorUtils.handleErrorCode(home, code)?.show()
                }?:dismiss()
            })
        }
    }

    abstract fun getLiveCycleOwner() : BaseFragment

    fun showDialog() {
        progressDialog?.show()
    }

    fun dismiss() {
        progressDialog?.dismiss()
    }

}