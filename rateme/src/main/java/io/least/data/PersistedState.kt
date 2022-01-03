package io.least.data

import java.util.*

data class PersistedState<PAYLOAD> (
    val lastShownDate: Date,
    val data: PAYLOAD
)