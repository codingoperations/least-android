package sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.least.case_management.ui.cases.CaseListFragment
import io.least.case_management.viewmodel.CaseListConfig
import io.least.collector.DeviceDataCollector
import io.least.connector.RestConnector
import io.least.data.RateExperienceConfig
import io.least.data.Tag
import io.least.ui.app.RateAppFragment
import io.least.ui.experience.RateExperienceFragment
import io.least.viewmodel.RateMeConfig
import io.sample.R
import io.sample.databinding.FragmentMainBinding
import io.sample.least.databinding.FragmentRatemeAppBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.buttonRateApp.setOnClickListener {
            RateAppFragment.show(
                parentFragmentManager,
                requireActivity().classLoader,
                RestConnector(DeviceDataCollector(), ""),
                RateMeConfig(minPositiveRate = 3.0f)
            )
        }
        binding.buttonRateExperience.setOnClickListener {
            RateExperienceFragment.show(
                parentFragmentManager,
                R.id.fragmentContainer,
                requireActivity().classLoader,
                RestConnector(DeviceDataCollector(), ""),
                RateExperienceConfig(
                    tags = listOf(Tag("id1", "tag1"), Tag("id2", "tag2"), Tag("id3", "tag3")),
                    appId = "myAppId",
                    numberOfStars = 7,
                    valueReaction = listOf(
                        Pair(2, "too bad :("),
                        Pair(6, "Nice ;)"),
                        Pair(8, "You shouldn't see it")
                    ),
                    title = "MY TITLE",
                    postSubmitTitle = "It is post submit Title",
                    postSubmitText = "It is post submit BODY TEXT",
                    fetchConfigFromServer = false
                )
            )
        }
        binding.buttonCaseManagement.setOnClickListener {
            CaseListFragment.show(
                parentFragmentManager,
                R.id.fragmentContainer,
                requireActivity().classLoader,
                CaseListConfig("https://example.com/v1/case-list", "profile-id-beka")
            )
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}