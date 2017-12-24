package ir.r3za13.kotlin_rz_boilerplate.util

import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog

/**
 * Created by Moosa_Abedini on 30/8/17.
 */
class DialogHelper{

    companion object
    {
        fun showIndeterminateDialog(context: Context, title: String, message: String) : MaterialDialog
        {
            return MaterialDialog.Builder(context)
                    .title(title)
                    .content(message)
                    .progress(true, 0)
                    .show()
        }

        fun showDialogWithRetry(context: Context,title: String, content: String, retryTitle: String, okTitle: String, onRetryClick: (MaterialDialog) -> Unit ,onOkCliclk:(MaterialDialog) -> Unit)
        {
            MaterialDialog.Builder(context)
                    .title(title)
                    .content(content)
                    .positiveText(retryTitle)
                    .negativeText(okTitle)
                    .onPositive { dialog, _ ->
                        onRetryClick(dialog)
                    }
                    .onNegative { dialog, _ ->
                        onOkCliclk(dialog)
                    }
                    .show()
        }
    }
}