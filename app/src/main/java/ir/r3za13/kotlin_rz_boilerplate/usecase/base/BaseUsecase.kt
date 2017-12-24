package ir.r3za13.kotlin_rz_boilerplate.data.usecase.base


import ir.r3za13.kotlin_rz_boilerplate.data.ErrorManager
import ir.r3za13.kotlin_rz_boilerplate.view.base.BasePresenter
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by Reza-Abedini on 3/21/2017.
 */
abstract class BaseUsecase<T> : BaseUsecaseContract<T> {

    @Volatile final override var isCompleted = false
    @Volatile final override var isFailed = false
    private var disposable: Disposable? = null


    protected abstract fun createObservable(): Single<T>

    override fun cancel() {
        if (disposable != null && !disposable!!.isDisposed)
            disposable!!.dispose()
        isCompleted = false
        isFailed = false
        disposable = null
    }

    override var isInProgress = !isCompleted && !isFailed && disposable != null && !disposable!!.isDisposed

    override fun observe(onSuccess: (T) -> Unit, onError: (e: Throwable) -> Unit): BaseUsecase<T> {
        isCompleted = false
        isFailed = false
        createObservable()   //.delay(1, TimeUnit.SECONDS) delay for mock data
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<T> {

                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onSuccess(t: T) {
                        onSuccess(t)
                        isCompleted = true
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        ErrorManager.handleError(e)
                        onError(e)
                        isCompleted = false
                        isFailed = true
                    }
                })
        return this
    }

    fun <V, T : BasePresenter<V>> register(presenter: T) {
        disposable?.run {
            presenter.addToDisposables(this)
        }
    }
}
