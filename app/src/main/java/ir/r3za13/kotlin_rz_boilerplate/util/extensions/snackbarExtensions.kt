package ir.r3za13.kotlin_rz_boilerplate.util.extensions

import android.app.Activity
import android.support.annotation.IntegerRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.View

/**
 *@author vahab ghadiri
 * @see https://gist.github.com/antoniolg/cdd070245b431b226ba3
 */
inline fun View.snack(@IntegerRes messageRes: Int, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
    snack(resources.getString(messageRes), length, f)
}

inline fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
    val snack = Snackbar.make(this, message, length)
    snack.f()
    snack.show()
}


fun Activity.snack(message: String, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit = {}): Snackbar? {
    val view = findViewById<View>(android.R.id.content)
    view?.let {
        val snack = Snackbar.make(view, message, length)
        snack.f()
        snack.show()
        return snack
    }
    return null
}

fun Fragment.snack(message: String, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit = {}): Snackbar? {
    val view = activity?.findViewById<View>(android.R.id.content)
    view?.let {
        val snack = Snackbar.make(view, message, length)
        snack.f()
        snack.show()
        return snack
    }
    return null
}

fun Snackbar.action(@IntegerRes actionRes: Int, color: Int? = null, listener: (View) -> Unit) {
    action(view.resources.getString(actionRes), color, listener)
}

fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(color) }
}
