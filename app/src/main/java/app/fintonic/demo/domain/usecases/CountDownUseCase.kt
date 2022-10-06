package app.fintonic.demo.domain.usecases

import app.fintonic.demo.data.utils.Outcome
import app.fintonic.demo.data.utils.asOutcome
import kotlinx.coroutines.delay

class CountDownUseCase {

    data class Params(
        val timeMillis: Long = 3000L
    )

    suspend operator fun invoke(params: Params) : Outcome<Boolean> {
        delay(params.timeMillis)
        return true.asOutcome()
    }

}