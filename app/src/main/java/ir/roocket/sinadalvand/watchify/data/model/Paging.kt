package ir.roocket.sinadalvand.watchify.data.model

import com.google.gson.annotations.SerializedName

data class Paging<T>(
    @SerializedName("data")
    val data: List<T>?,
    @SerializedName("metadata")
    val metadata: PageDetails?,
)

data class PageDetails(
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("page_count")
    val pageCount: Int,
    @SerializedName("total_count")
    val totalCount: Int,
)