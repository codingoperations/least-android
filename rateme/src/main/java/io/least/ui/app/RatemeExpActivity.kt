package io.least.ui.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.least.data.LabelValue
import io.least.data.RateExperienceConfig
import io.least.data.RateExperienceServerConfig
import io.least.data.Tag
import io.least.rate.R
import io.least.rate.databinding.ActivityRatemeExpBinding
import io.least.ui.experience.RateExperienceFragment

class RatemeExpActivity : AppCompatActivity() {
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
                numberOfStars = 10,
                valueReaction = listOf(
                    LabelValue(1, "too bad :("),
                    LabelValue(2, "Nice ;)"),
                    LabelValue(8, "Great!")
                ),
                title = "MY TITLE",
                postSubmitTitle = "It is post submit Title",
                postSubmitText = "It is post submit BODY TEXT",
            ),
            RateExperienceServerConfig("https://codingops-publisher.herokuapp.com", appId = "myAppId",
                fetchConfigFromServer = true,
                autoClosePostSubmission = false
            ),
            withBackStack = false
//                View.inflate(requireContext(), R.layout.custom_view, null)
        )
    }

    companion object {
        @JvmStatic
        fun startActivity(activity: Activity) {
            activity.startActivity(Intent(activity, RatemeExpActivity::class.java))
        }
    }
}
