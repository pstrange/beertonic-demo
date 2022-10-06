package app.fintonic.demo.presentation.utils

sealed class ProcessStep {
    //paso inicial opcional, por si se tiene que validar precondiciones
    object Preconditions: ProcessStep()
    //paso final alternativo, por si existen errores en las precondiciones, puede ir acompañado de extras
    data class PreconditionsError(val message: String, val extras: Any? = null): ProcessStep()
    //carga del proceso base
    data class Loading(val progress: Any? = null): ProcessStep()
    //error en el proceso base
    data class Error(val message: String, val show: Boolean = true): ProcessStep()
    //finalización del proceso base
    object Finished: ProcessStep()
}