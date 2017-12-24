package ir.r3za13.kotlin_rz_boilerplate.data

/**
 * Created by Moosa_Abedini on 22/10/17.
 */
data class ErrorModel(
        val e: Exception?,
        val errorCode: Int,
        val errorMessage: String,
        val isConnectionError: Boolean
)