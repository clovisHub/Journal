package com.example.journal.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.journal.R
import com.example.journal.databinding.FragmentArticlesListBinding
import com.example.journal.models.Article
import com.example.journal.ui.parents.BaseFragment
import com.example.journal.utils.NavigationUtils

class ArticlesListFragment: BaseFragment(), ArticlesAdapter.TaskListListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let { home ->
             //showDialog()
            (home as HomeActivity).viewModel
                .fetchArticlesList()
        }
    }

    override fun getLiveCycleOwner(): BaseFragment {
        return this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? = DataBindingUtil.inflate<FragmentArticlesListBinding>(inflater,
                R.layout.fragment_articles_list,
                container,
                false
            )?.apply {
        recyclerArticlesId.layoutManager = LinearLayoutManager(requireContext())
        recyclerArticlesId.setHasFixedSize(true)
        recyclerArticlesId.adapter = ArticlesAdapter(this@ArticlesListFragment)?.apply {
            activity?.let { home ->
              homeViewModel = (home as HomeActivity).viewModel
            }
        }

        homeViewModel?.observeArticlesList()
            ?.observe(viewLifecycleOwner, Observer { articlesEvent ->
                articlesEvent?.getContent()?.let {
                    dismiss()
                    (recyclerArticlesId.adapter as ArticlesAdapter).setArticlesList(it)
                }
            })


    }?.root

    override fun onClick(articleId: Long?) {
        articleId?.let {
            NavigationUtils.getFragment(ArticleDetailsFragment.newInstance(), it)
        } ?: Toast.makeText(context, "This article details is not available", Toast.LENGTH_LONG)
            .show()

    }

    companion object {
        fun newInstance() : ArticlesListFragment {
            return ArticlesListFragment()
        }
    }
}