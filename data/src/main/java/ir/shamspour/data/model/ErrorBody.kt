package ir.shamspour.data.model


import android.annotation.SuppressLint
import android.os.Parcelable
import ir.shamspour.data.base.ResponseObject
import ir.shamspour.domain.model.ErrorMessage
import ir.shamspour.domain.util.HttpErrors
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class ErrorBody(
    val message: String?,
    val status: Int?,
) : Parcelable,ResponseObject<ErrorMessage> {
    override fun toDomain(): ErrorMessage {
        return ErrorMessage(
            message=message,
            status=when(status){
                401-> HttpErrors.Unauthorized
                403-> HttpErrors.Forbidden
                400-> HttpErrors.BadRequest
                500-> HttpErrors.ServerError
                409-> HttpErrors.Conflict
                else-> HttpErrors.NotDefined
            })
    }
}