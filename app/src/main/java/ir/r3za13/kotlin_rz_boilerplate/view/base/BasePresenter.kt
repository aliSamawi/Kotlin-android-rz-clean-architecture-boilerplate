package ir.r3za13.kotlin_rz_boilerplate.view.base

import ir.r3za13.kotlin_rz_boilerplate.injection.component.AppComponent
import ir.r3za13.kotlin_rz_boilerplate.injection.component.PresenterComponent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Moosa_Abedini on 30/8/17.
 */
abstract class BasePresenter<T : MvpView> {
    protected val disposables = CompositeDisposable()
    protected var view: T? = null
    protected var presenterComponent: PresenterComponent? = null

    fun addToDisposables(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun bind(view: T, appComponent: AppComponent) {
        presenterComponent = appComponent.presenterComponent()
        injectComponents()
        this.view = view
    }

    fun unbind() {
        this.view = null
    }

    open fun destroy() {
        presenterComponent = null
        disposables.clear()
        unbind()
    }

    protected open fun injectComponents() {}
}