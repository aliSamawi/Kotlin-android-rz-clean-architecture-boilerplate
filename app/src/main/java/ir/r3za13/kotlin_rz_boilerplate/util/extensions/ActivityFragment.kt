import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager

//package ir.r3za13.koltin_rz_boilerplate.util.extensions
//
//import android.animation.ObjectAnimator
//import android.app.Activity
//import android.app.Fragment
//import android.content.Context
//import android.content.Intent
//import android.graphics.Interpolator
//import android.graphics.Rect
//import android.os.Build
//import android.os.Bundle
//import android.support.design.widget.Snackbar
//import android.support.v4.app.ActivityOptionsCompat
//import android.support.v7.app.AppCompatActivity
//import android.util.Log
//import android.view.View
//import android.view.animation.BounceInterpolator
//import android.view.inputmethod.InputMethodManager
//import android.widget.CheckBox
//import android.widget.EditText
//import android.widget.ScrollView
//import ir.r3za13.koltin_rz_boilerplate.view.base.BaseFragment
//import java.io.Serializable
//
///**
// * extension functions related to activities
// *
// * @author vahab ghadiri
// */
//
//inline fun <reified T : Any> Activity.launchActivityForResult(
//        requestCode: Int,
//        options: Bundle? = null,
//        noinline init: Intent.() -> Unit = {}) {
//
//    val intent = newIntent<T>(this)
//    intent.init()
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//        startActivityForResult(intent, requestCode, options)
//    } else {
//        startActivityForResult(intent, requestCode)
//    }
//}
//
inline fun <reified T : Any> newIntent(context: Context): Intent =
        Intent(context, T::class.java)

inline fun <reified T : Any> Activity.launchActivity(
        options: Bundle? = null,
        noinline init: Intent.() -> Unit = {}, myIntent: Intent? = null, vararg flag: Int) {
    val intent: Intent?
    if (myIntent == null)
        intent = newIntent<T>(this)
    else
        intent = myIntent

    intent.init()
    for (value in flag) {
        intent.addFlags(value)
    }
    options?.let { intent.putExtras(options) }
    startActivity(intent)
}

inline fun <reified T : Any> Fragment.launchActivity(
        options: Bundle? = null,
        noinline init: Intent.() -> Unit = {}, myIntent: Intent? = null, vararg flag: Int) {
    val intent: Intent?
    if (myIntent == null)
        intent = newIntent<T>(context!!)
    else
        intent = myIntent

    intent.init()
    for (value in flag) {
        intent.addFlags(value)
    }
    options?.let { intent.putExtras(options) }
    startActivity(intent)
}

//inline fun <reified T : Any> Activity.launchActivityWithTransition(
//        sharedView: View?,
//        transitionName: String,
//        dataKey: String = "", data: Int = 0,
//        noinline init: Intent.() -> Unit = {}, vararg flag: Int) {
//
//    val intent = newIntent<T>(this)
//    intent.init()
//    for (value in flag) {
//        intent.addFlags(value)
//    }
//    sharedView?.let {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            val animOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, it, transitionName)
//            val bundle = animOptions.toBundle()
//            if (!dataKey.isEmpty())
//                intent.putExtra(dataKey, data)
//            startActivity(intent, bundle)
//        } else {
//            intent.putExtra(dataKey, data)
//            startActivity(intent)
//        }
//
//    }
//}

//inline fun <reified T : Any> BaseFragment.launchActivityWithTransition(
//        sharedView: View?,
//        transitionName: String,
//        dataKey: String = "", data: Int = 0,
//        noinline init: Intent.() -> Unit = {}, vararg flag: Int) {
//
//    val intent = newIntent<T>(activity)
//    intent.init()
//    for (value in flag) {
//        intent.addFlags(value)
//    }
//    sharedView?.let {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            val animOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, it, transitionName)
//            val bundle = animOptions.toBundle()
//            if (!dataKey.isEmpty())
//                intent.putExtra(dataKey, data)
//            startActivity(intent, bundle)
//        } else {
//            intent.putExtra(dataKey, data)
//            startActivity(intent)
//        }
//
//    }
//}
//
//


fun <T : View> View.find(id: Int): T? = findViewById<T>(id)

fun <T : View> Activity.findSure(id: Int): T = findViewById<T>(id) as T
//
fun <T : View> android.support.v4.app.Fragment.find(id: Int): T? = activity?.findViewById<T>(id)

//
//
fun Activity.hideSoftKeyboard() {
    val inputMethodManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val v = currentFocus
    inputMethodManager.hideSoftInputFromWindow(v?.windowToken, 0)
}

fun Activity.showSoftKeyboard() {
    val inputMethodManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val v = currentFocus
    inputMethodManager.showSoftInput(v, 0)
}
//
//fun Activity.focusOnView(scrollView: ScrollView,view: View) {
//    scrollView.post({ scrollView.smoothScrollTo(0, view.getBottom()) })
//}
//
