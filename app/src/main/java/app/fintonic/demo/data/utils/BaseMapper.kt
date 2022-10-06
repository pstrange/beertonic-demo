package app.fintonic.demo.data.utils

interface BaseMapper<in IN, out OUT> {
    fun mapFrom(from: IN): OUT
}

interface BaseDefaultMapper<IN> {
    fun toDefault(): IN
}
