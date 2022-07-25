package io.least.ui.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.least.core.collector.UserSpecificContext
import io.least.core.ServerConfig
import io.least.rate.R
import io.least.rate.databinding.ActivityRatemeExpBinding
import io.least.ui.experience.RateExperienceFragment

class RateExpActivity : AppCompatActivity() {
    private var _binding: ActivityRatemeExpBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRatemeExpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        RateExperienceFragment.show(
            supportFragmentManager,
            R.id.fragmentContainer,
            this.classLoader,

            null,

            // TODO change token and host
            ServerConfig(
                hostUrl = "https://least-service.herokuapp.com",
                apiToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3NDMzNzE3NDMsInBheWxvYWQiOnsidXNlcklkIjoiNjJjMzYwNWE2OGE4NTRhMjExZGYxY2RmIiwicHJvamVjdE5hbWUiOiJhYmMifSwiaWF0IjoxNjU2OTcxNzQzfQ.8H5tUz6m8Fm55Py_2xq6cCjDOCc2hm0wvWpdVEEPyV0"
            ),
            withBackStack = false,
            usersContext = UserSpecificContext("bkodirov1986@gmail.com"),
//                View.inflate(requireContext(), R.layout.custom_view, null)
        )
    }

    companion object {
        @JvmStatic
        fun startActivity(activity: Activity) {
            activity.startActivity(Intent(activity, RateExpActivity::class.java))
        }
    }
}
