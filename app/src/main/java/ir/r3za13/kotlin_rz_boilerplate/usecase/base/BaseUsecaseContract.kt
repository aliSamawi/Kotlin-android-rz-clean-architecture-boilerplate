package ir.r3za13.kotlin_rz_boilerplate.data.usecase.base


/**
 * Created by Reza-Abedini on 3/21/2017.
 */
interface BaseUsecaseContract<T> {
    val isCompleted: Boolean
    val isInProgress: Boolean
    val isFailed: Boolean
    fun cancel()
    fun observe(onSuccess: (T) -> Unit,
                onError: (e: Throwable) -> Unit): BaseUsecase<T>
}
