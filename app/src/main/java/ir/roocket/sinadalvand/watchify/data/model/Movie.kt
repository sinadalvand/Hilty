package ir.roocket.sinadalvand.watchify.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Movie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String?,
    @SerializedName("poster")
    val poster: String?,
    var isSelected: Boolean = false,
    var watchedDate: Date?
)