package ir.r3za13.kotlin_rz_boilerplate.util.extensions

import android.content.Context
import android.util.Log
import ir.r3za13.kotlin_rz_boilerplate.MyApplication

import ir.r3za13.kotlin_rz_boilerplate.injection.component.AppComponent
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Moosa_Abedini on 30/8/17.
 */
fun Context.getAppComponent(): AppComponent = (applicationContext as MyApplication).appComponent

fun <T : Any> T.log(tag: String = "taggg", isError: Boolean = false, isCurrentTimeNeedded: Boolean = false, string: String = ""): T {
    var newTag = tag
    if (isCurrentTimeNeedded) {
        val currentTimeStr = SimpleDateFormat("mm:ss.SSS", Locale.US).format(Calendar.getInstance().time).toString()
        newTag = "$tag ( $currentTimeStr )"
    }

    when (this) {
        is List<*> -> this.forEach {
            if (!isError)
                Log.d(newTag, it.toString())
            else
                Log.e(newTag, it.toString())
        }
        else -> {
            if (!isError)
                Log.d(newTag, this.toString())
            else
                Log.e(newTag, this.toString())
        }
    }
    return this
}

fun <T : Any?> T.log(tag: String = "taggg", string: String = ""): T {
    Log.d(tag, "$string   ----->   ${this}")
    return this
}