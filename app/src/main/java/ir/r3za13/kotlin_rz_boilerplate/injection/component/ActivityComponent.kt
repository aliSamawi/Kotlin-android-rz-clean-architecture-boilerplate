package ir.r3za13.kotlin_rz_boilerplate.injection.component

import ir.r3za13.kotlin_rz_boilerplate.injection.scope.PerActivity
import dagger.Subcomponent
import ir.r3za13.kotlin_rz_boilerplate.MainActivity

/**
 * Created by Moosa_Abedini on 29/8/17.
 */
@PerActivity
@Subcomponent
interface ActivityComponent {

    fun inject(activity: MainActivity)

}