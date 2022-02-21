package io.least.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Tag(
    val id: String,
    val text: String
) :Parcelable

@Serializable
data class RateExperienceConfig(
    val tags: List<Tag>,
    val numberOfStars: Int,
    val valueReaction: List<LabelValue>,
    val title: String,
    val postSubmitTitle: String,
    val postSubmitText: String,
)

@Serializable
data class LabelValue(
    val value: Int,
    val label: String,
)

@Serializable
data class RateExperienceResult(
    val tags: List<Tag>,
    val rate: Int,
    val feedback: String,
)

data class RateExperienceServerConfig (
    val hostUrl : String,
    val appId : String,
    val fetchConfigFromServer : Boolean,
    val autoClosePostSubmission : Boolean,
)