package io.least.ui.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.least.core.collector.UserSpecificContext
import io.least.core.ServerConfig
import io.least.data.LabelValue
import io.least.data.RateExperienceConfig
import io.least.data.Tag
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
            RateExperienceConfig(
                tags = listOf(Tag("id1", "tag1"), Tag("id2", "tag2"), Tag("id3", "tag3")),
                numberOfStars = 6,
                valueReaction = listOf(
                    LabelValue(1, "too bad :("),
                    LabelValue(2, "Nice ;)"),
                    LabelValue(8, "Great!")
                ),
                title = "MY TITLE",
                postSubmitTitle = "It is post submit Title",
                postSubmitText = "It is post submit BODY TEXT",
            ),
            ServerConfig(
                hostUrl = "https://codingops-publisher.herokuapp.com",
                apiToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3NDMzMTUxNDgsInBheWxvYWQiOnsidXNlcklkIjoiNjJjMjU2NzE1ZjRiZjJlODE5N2Q0NTRmIiwicHJvamVjdE5hbWUiOiJhYSJ9LCJpYXQiOjE2NTY5MTUxNDh9.YQ3oOPirga8ZLehWMBxq27LhjriYLGaj_fWNXRhS7ks"
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
