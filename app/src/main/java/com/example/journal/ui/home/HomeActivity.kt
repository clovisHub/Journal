package com.example.journal.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.example.journal.R
import com.example.journal.databinding.ActivityHomeBinding
import com.example.journal.utils.ErrorUtils.handleErrorCode
import com.example.journal.utils.NavigationUtils

class HomeActivity: AppCompatActivity() {

    val viewModel: HomeViewModel by lazy { HomeViewModel(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home)
            ?.apply {

                NavigationUtils.setFragmentManager(this@HomeActivity.supportFragmentManager)
                NavigationUtils.getFragment(ArticlesListFragment.newInstance())
            }
    }
}