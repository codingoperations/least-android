package io.least.connector

import io.least.collector.GenericPayload

interface Connector<T> {
    fun create(data: T): GenericPayload<T>
    fun read(): GenericPayload<T>
    fun update(data: GenericPayload<T>)
    fun delete(data: GenericPayload<T>)
}