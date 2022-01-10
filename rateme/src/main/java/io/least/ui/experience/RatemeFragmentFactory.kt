package io.least.ui.experience

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import io.least.connector.Connector
import io.least.data.RateExperienceConfig

class RateExperienceFragmentFactory(
    private val config: RateExperienceConfig,
    private val connector: Connector<String>?
    ) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        Log.d("FragmentFactory", "instantiate(...)")
        return when(className) {
            RateExperienceFragment::class.java.name -> RateExperienceFragment(config, connector)
            else -> super.instantiate(classLoader, className)
        }
    }
}