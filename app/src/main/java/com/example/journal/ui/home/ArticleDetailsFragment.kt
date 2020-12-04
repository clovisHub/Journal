package com.example.journal.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.journal.R
import com.example.journal.databinding.FragmentArticleItemDetailsBinding
import com.example.journal.models.ArticleDetail
import com.example.journal.ui.parents.BaseFragment
import com.example.journal.utils.NavigationUtils

class ArticleDetailsFragment: BaseFragment() {

    override fun onStart() {
        super.onStart()
        activity?.let { home ->
            (home as HomeActivity).viewModel
                .fetchArticleDetails(requireArguments().getLong(NavigationUtils.KEY))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? = DataBindingUtil.inflate<FragmentArticleItemDetailsBinding>(inflater, R.layout.fragment_article_item_details,
        container, false)?.apply {
        showDialog()
        activity?.let { home ->
            (home as HomeActivity).viewModel
                .observeArticleDetails()
                .observe(viewLifecycleOwner, Observer { articleEvent ->
                    articleEvent?.getContentIfNotHandled<ArticleDetail>()?.let {
                        dismiss()
                        articleDetail = it
                    }
                })
        }

    }?.root

    override fun getLiveCycleOwner(): BaseFragment {
        return this
    }

    companion object {
        fun newInstance() : ArticleDetailsFragment {
            return ArticleDetailsFragment()
        }
    }
}