package ir.r3za13.kotlin_rz_boilerplate.view.base

import android.content.Context
import android.support.v4.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import ir.r3za13.kotlin_rz_boilerplate.data.ErrorModel
import ir.r3za13.kotlin_rz_boilerplate.injection.component.ActivityComponent
import ir.r3za13.kotlin_rz_boilerplate.util.extensions.getAppComponent

/**
 *
 * @author vahab ghadiri
 */
abstract class BaseFragment : Fragment(),MvpView {

    protected var activityComponent: ActivityComponent? = null
    var isFirstSeen = false
    lateinit var baseUtils: BaseUtils

    protected abstract fun getPresenter(): BasePresenter<*>?

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        baseUtils = BaseUtils(this)
        activityComponent = activity!!.getAppComponent().activityComponent()
    }

    override fun onDetach() {
        super.onDetach()
        getPresenter()?.destroy()
        activityComponent = null
    }

    open fun onFragmentFirstSeen() {

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && !isFirstSeen) {
            isFirstSeen = true
            onFragmentFirstSeen()
        }
    }

    override fun showError(error: ErrorModel, showDialog: Boolean) {
        baseUtils.showError(error,fragmentManager!!,showDialog)
    }

    override fun showDialogWithRetryStringsID(title: Int, content: String?, retryTitle: Int, okTitle: Int, onOkClick: (MaterialDialog) -> Unit, onRetryClick: (MaterialDialog) -> Unit) {
        baseUtils.showDialogWithRetryByIds(title, content ?: "", retryTitle, okTitle, onOkClick, onRetryClick)
    }

    override fun showProgressDialog() {
        super.showProgressDialog()
    }
}