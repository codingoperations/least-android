package io.least.data

interface PreferencePersistable {
    fun encode(): String
    fun decode(): PreferencePersistable
}