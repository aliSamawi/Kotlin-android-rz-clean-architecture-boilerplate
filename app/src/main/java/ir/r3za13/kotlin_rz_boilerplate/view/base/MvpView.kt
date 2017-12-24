package ir.r3za13.kotlin_rz_boilerplate.view.base

import com.afollestad.materialdialogs.MaterialDialog
import ir.r3za13.kotlin_rz_boilerplate.data.ErrorModel

/**
 * Created by Moosa_Abedini on 30/8/17.
 */
interface MvpView {
    fun showProgressDialog() {}
    fun dismissProgressDialog() {}
    fun showError(error: ErrorModel, showDialog: Boolean = true)
    fun showDialogWithRetry(title: String?, content: String?, retryTitle: String, okTitle: String, onOkClick: (MaterialDialog) -> Unit, onRetryClick: (MaterialDialog) -> Unit) {}
    fun showDialogWithRetryStringsID(title: Int, content: String?, retryTitle: Int, okTitle: Int, onOkClick: (MaterialDialog) -> Unit, onRetryClick: (MaterialDialog) -> Unit) {}
    fun onTokenExpired() {}

}