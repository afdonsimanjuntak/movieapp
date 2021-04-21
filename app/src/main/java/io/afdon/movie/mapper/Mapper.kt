package io.afdon.movie.mapper

interface Mapper<I, O> {

    fun map(input: I?) : O?
}