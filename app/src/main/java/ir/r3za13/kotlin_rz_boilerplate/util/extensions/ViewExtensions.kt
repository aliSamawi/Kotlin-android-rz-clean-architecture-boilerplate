package ir.r3za13.kotlin_rz_boilerplate.util.extensions

import android.graphics.Rect
import android.view.*
import android.widget.*
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AlphaAnimation
import ir.r3za13.kotlin_rz_boilerplate.util.GlobalFunctions


/**
 * @author vahab ghadiri
 */
fun EditText.setKeyboard(mainView: ScrollView) {
    this.viewTreeObserver.addOnGlobalLayoutListener {
        val rect = Rect()
        mainView.getWindowVisibleDisplayFrame(rect);
        val heightDiff = mainView.rootView.height - (rect.bottom - rect.top)
        if (heightDiff > GlobalFunctions.dpToPx(this.context, 100f)) {
            mainView.smoothScrollTo(0, this.bottom)
        }
    }
}

fun View.show(duration: Long = 400, onAnimationEnd: () -> Unit = {}) {
    if (visibility == View.VISIBLE)
        return

    val fadeOutAnimation = AlphaAnimation(0f, 1f)
    fadeOutAnimation.duration = duration
    fadeOutAnimation.fillAfter = true
    fadeOutAnimation.setAnimationListener(object : AnimationListener {
        override fun onAnimationEnd(animation: Animation) {
            onAnimationEnd.invoke()
        }

        override fun onAnimationRepeat(animation: Animation) {}

        override fun onAnimationStart(animation: Animation) {
            visibility = View.VISIBLE
        }
    })
    animation = fadeOutAnimation
}

fun View.hide(duration: Long = 300) {

    if (visibility == View.INVISIBLE || visibility == View.GONE)
        return

    val fadeOutAnimation = AlphaAnimation(1f, 0f)
    fadeOutAnimation.duration = duration
    fadeOutAnimation.fillAfter = true
    fadeOutAnimation.setAnimationListener(object : AnimationListener {
        override fun onAnimationEnd(animation: Animation) {
            visibility = View.GONE
        }

        override fun onAnimationRepeat(animation: Animation) {}

        override fun onAnimationStart(animation: Animation) {

        }
    })
    animation = fadeOutAnimation

}

////TODO do for some another views that is needed
//fun View.setFont(font: FontsTools.Fonts) {
//    val typeFace = FontsTools.getTypeFace(context, font)
//    when (this) {
//        is SearchView -> {
//            val searchText = this.findViewById<TextView>(android.support.v7.appcompat.R.id.search_src_text)
//            searchText.typeface = typeFace
//        }
//        is TabLayout -> {
//            val tabView = getChildAt(0) as LinearLayout
//            (0 until tabView.childCount)
//                    .map { tabView.getChildAt(it) as LinearLayout }
//                    .map { it.getChildAt(1) as TextView }
//                    .forEach { it.typeface = typeFace }
//        }
//        is TextInputLayout -> setTypeface(typeface)
//
//        is TextView -> typeface = typeFace
//
//    }
//}


fun ViewStub.doInflate(onInflate: View.() -> Unit={}): View? {
    var result: View? = null
    this.setOnInflateListener { _, inflated ->
        result = inflated
        inflated.onInflate()
    }
    this.inflate()
    return result
}
