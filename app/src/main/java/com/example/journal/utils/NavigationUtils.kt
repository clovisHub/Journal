package com.example.journal.utils

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.journal.R
import com.example.journal.ui.parents.OriginFragment

object NavigationUtils {

    val KEY: String? ="Id"

    private val arguments =  Bundle()

    private var supportFragmentManager : FragmentManager? = null

    fun setFragmentManager(fragmentManager: FragmentManager) {
        if(supportFragmentManager == null) supportFragmentManager = fragmentManager
        else supportFragmentManager
    }

    fun  <T: OriginFragment> getFragment(currentFragment: T, id: Long? = null) {

        if (supportFragmentManager == null) {
            return
        }

        if(id != null) {
            arguments.putLong(KEY, id)
            currentFragment.arguments = arguments
        }

       supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container_id, currentFragment)
            ?.addToBackStack(currentFragment::class.java.simpleName)
            ?.commit()
    }
}