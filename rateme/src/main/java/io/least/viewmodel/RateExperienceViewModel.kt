package io.least.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.least.connector.Connector
import io.least.data.RateExperienceConfig
import io.least.data.RateExperienceConfigRepo
import io.least.data.Tag
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RateExperienceViewModel (
    @Volatile private var config: RateExperienceConfig,
    private val repository: RateExperienceConfigRepo,
    private val connector: Connector<String>?
): ViewModel() {

    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow<RateExperienceState>(RateExperienceState.ConfigLoading)

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<RateExperienceState> = _uiState

    init {
        if (config.fetchConfigFromServer) {
            _uiState.value = RateExperienceState.ConfigLoading
            viewModelScope.launch {
                config = repository.fetchRateExperienceConfig()
                _uiState.value = RateExperienceState.ConfigLoaded(config)
            }
        } else {
            _uiState.value = RateExperienceState.ConfigLoaded(config)
        }
    }

    fun onFeedbackSubmit(text: String, rating: Float, selectedTags: List<Tag>) {
//        connector?.create(text)
        Log.d(this.javaClass.simpleName, "Creating a case --> $text")
    }
}

sealed class RateExperienceState {
    object ConfigLoading : RateExperienceState()
    class ConfigLoaded(val config: RateExperienceConfig) : RateExperienceState()
}
