package io.least.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.least.data.*
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RateExperienceViewModel(
    @Volatile private var config: RateExperienceConfig,
    private val serverConfig: RateExperienceServerConfig,
    private val repository: RateExperienceConfigRepo
): ViewModel() {

    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow<RateExperienceState>(RateExperienceState.ConfigLoading)

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<RateExperienceState> = _uiState

    init {
        if (serverConfig.fetchConfigFromServer) {
            _uiState.value = RateExperienceState.ConfigLoading
            viewModelScope.launch {
                kotlin.runCatching {  }
                config = repository.fetchRateExperienceConfig()
                _uiState.value = RateExperienceState.ConfigLoaded(config)
            }
        } else {
            _uiState.value = RateExperienceState.ConfigLoaded(config)
        }
    }

    fun onFeedbackSubmit(text: String, rating: Float, selectedTags: List<Tag>) {
        Log.d(this.javaClass.simpleName, "Creating a case --> $text")
        _uiState.value = RateExperienceState.Submitting
        viewModelScope.launch {
            try {
                repository.publishRateResults(RateExperienceResult(selectedTags, rating.toInt(), text))
                _uiState.value = RateExperienceState.SubmissionSuccess
            } catch (t: Throwable) {
                _uiState.value = RateExperienceState.SubmissionError
            }
        }
    }

    fun onRateSelected(rating: Float) {
        for (it in config.valueReaction) {
            if (rating.toInt() <= it.value){
                _uiState.value = RateExperienceState.RateSelected(it.label)
                break
            }
        }
    }
}

sealed class RateExperienceState {
    object ConfigLoading : RateExperienceState()
    class RateSelected(val reaction: String) : RateExperienceState()
    class ConfigLoaded(val config: RateExperienceConfig) : RateExperienceState()
    object Submitting : RateExperienceState()
    object SubmissionError : RateExperienceState()
    object SubmissionSuccess : RateExperienceState()
}
