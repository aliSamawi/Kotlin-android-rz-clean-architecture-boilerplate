package ir.r3za13.kotlin_rz_boilerplate.injection.component

import ir.r3za13.kotlin_rz_boilerplate.injection.module.AppContextModule
import ir.r3za13.kotlin_rz_boilerplate.injection.module.NetworkModule
import dagger.Component
import javax.inject.Singleton


/**
 * Created by Moosa_Abedini on 29/8/17.
 */
@Singleton
@Component(modules = [(NetworkModule::class), (AppContextModule::class)])
interface AppComponent {

    fun activityComponent(): ActivityComponent
    fun presenterComponent(): PresenterComponent

}