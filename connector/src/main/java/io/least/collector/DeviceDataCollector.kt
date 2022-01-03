package io.least.collector

class DeviceDataCollector<T> {
    fun collect(customData: T): GenericPayload<T> {
        return GenericPayload("","", customData)
    }
}