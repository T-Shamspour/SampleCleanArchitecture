package ir.shamspour.data.model


import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import ir.shamspour.data.base.ResponseObject
import ir.shamspour.domain.model.Post

@Parcelize
data class PostRemote(
    val body: String?,
    val id: Int?,
    val title: String?,
    val userId: Int?
) : Parcelable,ResponseObject<Post> {
    override fun toDomain(): Post = Post(body, id, title, userId)
}