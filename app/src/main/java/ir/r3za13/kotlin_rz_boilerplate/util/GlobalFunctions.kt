package ir.r3za13.kotlin_rz_boilerplate.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.support.v4.util.Preconditions
import android.util.TypedValue
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Moosa_Abedini on 30/8/17.
 */
class GlobalFunctions {

    companion object {

        fun dpToPx(context: Context, dp: Float)
                = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics).toInt()

        fun pxToDp(context: Context, px: Int): Int {
            val r: Resources = context.resources
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px.toFloat(), r.displayMetrics).toInt()
        }

        fun checkPlayServices(context: Context): Boolean {
            val googleAPI = GoogleApiAvailability.getInstance()
            val result = googleAPI.isGooglePlayServicesAvailable(context)
            if (result != ConnectionResult.SUCCESS) {
                if (googleAPI.isUserResolvableError(result)) {
                    googleAPI.getErrorDialog(context as Activity?, result, StaticFields.PLAY_SERVICES_RESOLUTION_REQUEST).show()
                }

                return false
            }

            return true

        }


        fun dateToISO8601String(date: Date): String?
        {
            try {
                val tz = TimeZone.getTimeZone("UTC")
                val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                df.setTimeZone(tz)
                return df.format(date)
            }
            catch (e:Exception)
            {
                e.printStackTrace()
                return null
            }
        }

        fun stringToISO8601Date(dateString: String): Date?
        {
            try{
                val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ",Locale.getDefault())
                return df.parse(dateString)
            }
            catch (e:Exception)
            {
                e.printStackTrace()
                return null
            }
        }

        fun get12HoursTimeFormat(date: Date): String
        {
            val sdf = SimpleDateFormat("hh:mm a")
            return sdf.format(date)
        }

        @SuppressLint("RestrictedApi")
        fun getDayOfMonthSuffix(n: Int): String {
            Preconditions.checkArgument(n >= 1 && n <= 31, "illegal day of month: " + n)
            if (n >= 11 && n <= 13) {
                return "th"
            }
            when (n % 10) {
                1 -> return "st"
                2 -> return "nd"
                3 -> return "rd"
                else -> return "th"
            }
        }
    }

}