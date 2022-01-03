package sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.least.case_management.ui.cases.CaseListFragment
import io.least.case_management.viewmodel.CaseListConfig
import io.least.collector.DeviceDataCollector
import io.least.connector.RestConnector
import io.least.ui.RateMeFragment
import io.sample.R
import io.sample.databinding.MainActivityBinding

class SampleShowCaseActivity : AppCompatActivity() {
    private var _binding: MainActivityBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRateMe.setOnClickListener {
            RateMeFragment.show(
                supportFragmentManager,
                classLoader,
                RestConnector(DeviceDataCollector<String>(), "")
            )
        }
        binding.buttonCaseManagement.setOnClickListener {
            CaseListFragment.show(
                supportFragmentManager,
                R.id.fragmentContainer,
                classLoader, CaseListConfig("https://example.com/v1/case-list", "profile-id-beka")
            )
        }
    }
}