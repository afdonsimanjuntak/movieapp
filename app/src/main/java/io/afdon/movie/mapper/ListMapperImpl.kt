package io.afdon.movie.mapper

class ListMapperImpl<I, O>(private val mapper: Mapper<I, O>): ListMapper<I?, O> {

    override fun map(input: List<I?>?): List<O> {
        return input?.mapNotNull { mapper.map(it) }.orEmpty()
    }
}