package ir.r3za13.kotlin_rz_boilerplate.data.repository

import ir.r3za13.kotlin_rz_boilerplate.data.restful.APIs
import ir.r3za13.kotlin_rz_boilerplate.data.restful.APIsWithToken
import ir.r3za13.kotlin_rz_boilerplate.injection.qualifier.WithToken
import ir.r3za13.kotlin_rz_boilerplate.injection.qualifier.WithoutToken
import javax.inject.Inject

/**
 * main repository that is responsible for all data connections with server
 * @author Moosa Abedini
 */
class CloudRepository @Inject constructor(
        @WithToken private val apIsWithToken: APIsWithToken,
        @WithoutToken private val api: APIs
//        private val localRepository: LocalRepository
) : CloudRepositoryContract {

//    /**
//     * method to get all jobs
//     */
//    override fun getAllJobs(): Single<TaskListResponse>
//            = Single.defer { apIsWithToken.getJobs() }


}
