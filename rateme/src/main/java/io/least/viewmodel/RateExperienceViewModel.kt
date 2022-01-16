package io.least.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.least.data.RateExperienceConfig
import io.least.data.RateExperienceConfigRepo
import io.least.data.RateExperienceResult
import io.least.data.Tag
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RateExperienceViewModel(
    @Volatile private var config: RateExperienceConfig,
    private val repository: RateExperienceConfigRepo
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
        Log.d(this.javaClass.simpleName, "Creating a case --> $text")
        viewModelScope.launch {
            repository.publishRateResults(RateExperienceResult(selectedTags, rating.toInt(), text))
        }
    }
}

sealed class RateExperienceState {
    object ConfigLoading : RateExperienceState()
    class ConfigLoaded(val config: RateExperienceConfig) : RateExperienceState()
}
