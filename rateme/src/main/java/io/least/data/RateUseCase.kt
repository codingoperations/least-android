package io.least.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RateExperienceConfig(
    val tags: List<Tag>,
    val appId: String,
    val numberOfStars: Int,
    val valueReaction: List<Pair<Int, String>>,
    val title: String,
    val postSubmitTitle: String,
    val postSubmitText: String,
    val fetchConfigFromServer: Boolean
) :Parcelable

@Parcelize
data class Tag(
    val id: String,
    val text: String
) :Parcelable

data class RateExperienceDto(
    val tags: List<Tag>,
    val numberOfStars: Int,
    val valueReaction: List<Pair<Int, String>>,
    val title: String,
    val postSubmitTitle: String,
    val postSubmitText: String,
)