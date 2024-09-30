package com.mehdisekoba.filimo.data.model.category


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

data class ResponseCategory(
    @SerializedName("data")
    val data: List<Data>?
) {
    data class Data(
        @SerializedName("attributes")
        val attributes: Attributes?,
        @SerializedName("id")
        val id: Int?, // 2001215
        @SerializedName("type")
        val type: String? // Category
    ) {
        @Parcelize
        data class Attributes(
            @SerializedName("children")
            val children: List<String>?,
            @SerializedName("cover")
            val cover: String?, // https://static.cdn.asset.filimo.com/flmt/tag_cvr_2001215_fl_5.jpg?width=1200&quality=85&secret=eGivow8Onx0V6ojS2vXm0Q
            @SerializedName("img")
            val img: String?, // https://www.filimo.com//assets/web/ui/img-e0b236jvXXOH4mXHu9Zlw/filimo/categories/fa/2001215.jpg
            @SerializedName("link_key")
            val linkKey: String?, // kids
            @SerializedName("link_type")
            val linkType: String?, // list
            @SerializedName("parent")
            val parent: String?, // null
            @SerializedName("tag_id")
            val tagId: Int?, // 2001215
            @SerializedName("title")
            val title: String?, // فیلیمو کودک
            @SerializedName("title_en")
            val titleEn:@RawValue String? // kids
        ): Parcelable
    }
}