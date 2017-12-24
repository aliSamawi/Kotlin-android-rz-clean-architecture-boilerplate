package ir.r3za13.kotlin_rz_boilerplate.util.extensions

import android.util.Patterns

/**
 *
 * @author vahab ghadiri
 */


//string
fun String.isEmail(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()
//
//
//fun <T> List<T>.pageWithLocale(context: Context): List<T> {
//    return if (LocaleHelper.getLanguage(context) == LocaleHelper.BespokeLocale.FA_PERSIAN.localeString) {
//        this.asReversed()
//    } else {
//        this
//    }
//}

fun String.toTitleString(): String {
    return if (length > 0) {
        this.replace(get(0), get(0).toUpperCase())
    } else
        this
}

fun Int.toStringFormWith_K(): String {
    val num = this / 1000
    return if (num > 0)
        "${num}k"
    else
        this.toString()
}


