package io.least.viewmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RateMeConfig(
    val minPositiveRate: Float = 3.0f,
): Parcelable