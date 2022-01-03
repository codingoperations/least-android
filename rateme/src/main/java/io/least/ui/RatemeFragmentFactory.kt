package io.least.ui

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import io.least.connector.Connector

class RatemeFragmentFactory(private val connector: Connector<String>?) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        Log.d("FragmentFactory", "instantiate(...)")
        return when(className) {
            RateMeFragment::class.java.name -> RateMeFragment(connector)
            else -> super.instantiate(classLoader, className)
        }
    }
}