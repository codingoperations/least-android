package io.least.ui.experience

import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import io.least.data.RateExperienceConfig
import io.least.data.RateExperienceServerConfig

class RateExperienceFragmentFactory(
    private val config: RateExperienceConfig,
    private val serverConfig: RateExperienceServerConfig,
    private val customView: View?
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        Log.d("FragmentFactory", "instantiate(...)")
        return when(className) {
            RateExperienceFragment::class.java.name -> RateExperienceFragment(config, serverConfig, customView)
            else -> super.instantiate(classLoader, className)
        }
    }
}