package app.fintonic.demo.data.utils

abstract class LocalDataSource<T: Any, O: Any> {

    suspend fun consume(): Outcome<O> {
        return try {
            val response = withIO { consumeService() }
                getOutcome(response)
        } catch (t: Throwable) {
            t.printStackTrace()
            Outcome.Error(handleThrowable(t))
        }
    }

    protected abstract suspend fun consumeService(): T
    protected abstract fun getOutcome(result: T): Outcome<O>

}