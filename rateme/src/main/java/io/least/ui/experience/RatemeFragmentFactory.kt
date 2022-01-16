package io.least.ui.experience

import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import io.least.connector.Connector
import io.least.data.RateExperienceConfig
import io.least.data.RateExperienceResult

class RateExperienceFragmentFactory(
    private val config: RateExperienceConfig,
    private val customView: View?
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        Log.d("FragmentFactory", "instantiate(...)")
        return when(className) {
            RateExperienceFragment::class.java.name -> RateExperienceFragment(config, customView)
            else -> super.instantiate(classLoader, className)
        }
    }
}