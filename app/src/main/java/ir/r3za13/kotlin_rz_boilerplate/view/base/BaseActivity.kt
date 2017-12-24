package ir.r3za13.kotlin_rz_boilerplate.view.base

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.transition.Transition
import com.afollestad.materialdialogs.MaterialDialog
import ir.r3za13.kotlin_rz_boilerplate.injection.component.ActivityComponent
import ir.r3za13.kotlin_rz_boilerplate.R
import ir.r3za13.kotlin_rz_boilerplate.util.extensions.getAppComponent
import io.reactivex.disposables.CompositeDisposable
import ir.r3za13.kotlin_rz_boilerplate.data.ErrorModel


/**
 * Created by Moosa_Abedini on 30/8/17.
 */
abstract class BaseActivity : AppCompatActivity(), Transition.TransitionListener, MvpView
{
    protected var activityComponent: ActivityComponent? = null
    protected val disposables = CompositeDisposable()

    private var baseUtils: BaseUtils? = null
    private var mEndTransitionOp: (() -> Unit)? = null
    private var mAlsoEndTransition = true

    protected abstract fun getLayoutResId(): Int
    protected abstract fun getPresenter(): BasePresenter<*>?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(getLayoutResId())
        this.baseUtils = BaseUtils(this)
        activityComponent = getAppComponent().activityComponent()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.sharedElementEnterTransition.addListener(this)
        }

    }

    override fun onStop() {
        baseUtils?.dismissLoadingProgressDialog()
        super.onStop()
    }

    override fun onDestroy() {
        activityComponent = null
        getPresenter()?.destroy()
        disposables.dispose()
        super.onDestroy()
    }

    override fun showProgressDialog() {
        baseUtils?.showLoadingProgressDialog(getString(R.string.please_wait), getString(R.string.connecting))
    }

    override fun dismissProgressDialog() {
        baseUtils?.dismissLoadingProgressDialog()
    }

    override fun onTokenExpired() {
        baseUtils?.onTokenExpired()
    }

    override fun showDialogWithRetry(title: String?, content: String?, retryTitle: String, okTitle: String, onOkClick: (MaterialDialog) -> Unit, onRetryClick: (MaterialDialog) -> Unit) {
        baseUtils?.showDialogWithRetry(title ?: "", content ?: "", retryTitle, okTitle, onOkClick, onRetryClick)
    }

    override fun showDialogWithRetryStringsID(title: Int, content: String?, retryTitle: Int, okTitle: Int, onOkClick: (MaterialDialog) -> Unit, onRetryClick: (MaterialDialog) -> Unit) {
        baseUtils?.showDialogWithRetryByIds(title, content ?: "", retryTitle, okTitle, onOkClick, onRetryClick)
    }
    //transitions
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onTransitionEnd(transition: Transition?) {
        mEndTransitionOp?.invoke()
        if (!mAlsoEndTransition) {
            window.sharedElementEnterTransition.removeListener(this)
        }
    }

    override fun onTransitionResume(transition: Transition?) {
    }

    override fun onTransitionPause(transition: Transition?) {
    }

    override fun onTransitionCancel(transition: Transition?) {
    }

    override fun onTransitionStart(transition: Transition?) {

    }

    override fun showError(error: ErrorModel, showDialog: Boolean) {
        baseUtils?.showError(error,supportFragmentManager,showDialog)
    }

    //    override fun attachBaseContext(newBase: Context?) {
//        super.attachBaseContext(LocaleHelper.onAttach(newBase!!))
//    }

//    override fun showError(errorModel: ErrorModel,)
//    {
//        baseUtils?.showError(errorModel,supportFragmentManager,)
//    }

//    open fun initToolbar(view: View?, title :String?, hasBackButton :Boolean,menuText: String?=null, onMenuClick: () -> Unit = {})
//    {
//        if(view==null)
//            return
//
//        val toolbar = view.findViewById<Toolbar>(R.id.myToolbar)
//        val tvTitle = view.findViewById<ir.r3za13.koltin_rz_boilerplate.view.customViews.truckieTextView>(R.id.tvToolbarTitle)
//        val tvToolbarMenu = view.findViewById<ir.r3za13.koltin_rz_boilerplate.view.customViews.truckieTextView>(R.id.tvToolbarMenu)
//
//        setSupportActionBar(toolbar)
//        if(hasBackButton) {
//            getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
//            getSupportActionBar()?.setDisplayShowHomeEnabled(true)
//        }
//
//        setTitle("")
//        tvTitle?.text = title
//
//        if(menuText==null)
//            tvToolbarMenu.visibility = View.GONE
//        else {
//            tvToolbarMenu.setOnClickListener { onMenuClick() }
//            tvToolbarMenu.text = menuText
//        }
//
//    }

    final fun onActivityTransitionEnd(alsoEndTransition: Boolean = true, op: () -> Unit) {
        mEndTransitionOp = op
        mAlsoEndTransition = alsoEndTransition
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}