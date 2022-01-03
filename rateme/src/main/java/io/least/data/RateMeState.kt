package io.least.data

class RateMeState(val lastKnown: State): PreferencePersistable {
    override fun encode(): String {
        TODO("Not yet implemented")
    }

    override fun decode(): PreferencePersistable {
        TODO("Not yet implemented")
    }

}

enum class State { NONE, ASK_LATER, NEVER_ASK, SUBMITTED }