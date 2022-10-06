package app.fintonic.demo.data.utils

import retrofit2.Response

abstract class ApiDataSource<T: Any, O: Any> {

    suspend fun consume(): Outcome<O> {
        try {
            val response = withIO { consumeService() }
            if (response.isSuccessful)
            //si estatus es diferente de 200 y se necesita procesar un cambio de estatus
            //se deja que la implementación de la clase se haga cargo
                return if (shouldOverrideSuccess())
                    processSuccessResponse(response)
                else
                    getOutcome(response.body()!!)
            else {
                val errorBodyStream = response.errorBody()?.charStream()
                val errorBody = errorBodyStream?.readText() ?: ""
                try {
                    if (shouldOverrideError())
                        return processErrorResponse(response)
                    if(processOnlyErrorResponse())
                        return processOnlyErrorResponse(errorBody)
                    if(processCompleteResponse())
                        return processCompleteResponse(response, errorBody)
                } catch (ex: Exception){
                    ex.printStackTrace()
                }
            }
            return Outcome.Error("Error desconocido")
        } catch (t: Throwable) {
            t.printStackTrace()
            return Outcome.Error(handleThrowable(t))
        }
    }

    open fun shouldOverrideSuccess() = false
    open fun processSuccessResponse(response: Response<T>): Outcome<O> = Outcome.Completed

    open fun processOnlyErrorResponse() = false
    open fun processOnlyErrorResponse(error: String?): Outcome<O> = Outcome.Error("Error desconocido")

    open fun processCompleteResponse() = false
    open fun processCompleteResponse(response: Response<T>, error: String?): Outcome<O> = Outcome.Error("Error desconocido")

    open fun shouldOverrideError() = false
    open fun processErrorResponse(response: Response<T>): Outcome<O> = Outcome.Error("Error desconocido")

    protected abstract suspend fun consumeService(): Response<T>
    protected abstract fun getOutcome(body: T): Outcome<O>

}