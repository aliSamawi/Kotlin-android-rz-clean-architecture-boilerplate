package ir.r3za13.kotlin_rz_boilerplate

import android.app.Application
import com.orhanobut.hawk.Hawk
import ir.r3za13.kotlin_rz_boilerplate.injection.component.AppComponent
import ir.r3za13.kotlin_rz_boilerplate.injection.component.DaggerAppComponent
import ir.r3za13.kotlin_rz_boilerplate.injection.module.AppContextModule

/**
 * Created by Moosa_Abedini on 24/12/17.
 */

class MyApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        setupComponent()
        setupPushNotification()
        Hawk.init(this).build()
    }

    private fun setupComponent() {
        appComponent = DaggerAppComponent.builder()
                .appContextModule(AppContextModule(applicationContext))
                .build()


    }

    private fun setupPushNotification() {
//        OneSignal.startInit(this)
//                .setNotificationReceivedHandler(PushReceivedHandler())
//                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.None)
//                .init()
    }
}