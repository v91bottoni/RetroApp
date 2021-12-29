package com.v1bottoni.retroapp.domain.mappers

abstract class BaseMapper<in T,E> {
    abstract fun mapFrom(from: T): E
    abstract fun mapFrom(from: List<T>): List<E>
}