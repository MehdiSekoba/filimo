package com.mehdisekoba.filimo.data.model.category


import com.google.gson.annotations.SerializedName

data class ResponseCategoryDetails(
    @SerializedName("data")
    val data: List<Data>?,
    @SerializedName("included")
    val included: List<Included>?,
    @SerializedName("links")
    val links: Links?,
    @SerializedName("meta")
    val meta: Meta?
) {
    data class Data(
        @SerializedName("attributes")
        val attributes: Attributes?,
        @SerializedName("id")
        val id: Int?, // 3
        @SerializedName("relationships")
        val relationships: Relationships?,
        @SerializedName("type")
        val type: String? // Row
    ) {
        data class Attributes(
            @SerializedName("has_downvote")
            val hasDownvote: Boolean?, // false
            @SerializedName("id")
            val id: Int?, // 3
            val linkText: String?, // محبوب ترین شخصیت ها
            @SerializedName("link_to_episode")
            val linkType: String?, // list
            @SerializedName("list_tag_id")
            val listTagId: Int?, // 2001215
            @SerializedName("md5")
            val md5: String?, // c70879633a6998e68e4717cbe5aebe1b
            @SerializedName("more_type")
            val moreType: String?, // grid
            @SerializedName("output_type")
            val outputType: String?, // poster
            @SerializedName("show_all")
            val showAll: Boolean?, // true
            @SerializedName("showSerialParent")
            val showSerialParent: Boolean?, // true
            @SerializedName("smarties_info")
            val smartiesInfo: SmartiesInfo?,
            @SerializedName("tag_id")
            val tagId: Int?, // 1000051
            @SerializedName("theme")
            val theme: String?, // brick
            @SerializedName("tracker")
            val tracker: Tracker?
        ) {
            data class SmartiesInfo(
                @SerializedName("smarties_type")
                val smartiesType: String? // other
            )

            data class Tracker(
                @SerializedName("Algorithm")
                val algorithm: String?, // other
                @SerializedName("Index")
                val index: Int?, // 0
                @SerializedName("Label")
                val label: Any?, // null
                @SerializedName("Slug")
                val slug: Any?, // null
                @SerializedName("Source")
                val source: Any?, // null
                @SerializedName("Tagid")
                val tagid: String?, // Not specified
                @SerializedName("Title")
                val title: String?, // محبوب ترین شخصیت ها
                @SerializedName("Type")
                val type: Any? // null
            )
        }

        data class Relationships(
            @SerializedName("buttonlists")
            val buttonlists: Buttonlists?,
            @SerializedName("crews")
            val crews: Crews?,
            @SerializedName("customhtmls")
            val customhtmls: Customhtmls?,
            @SerializedName("headersliders")
            val headersliders: Headersliders?,
            @SerializedName("listinfos")
            val listinfos: Listinfos?,
            @SerializedName("livetvs")
            val livetvs: Livetvs?,
            @SerializedName("menus")
            val menus: Menus?,
            @SerializedName("movies")
            val movies: Movies?,
            @SerializedName("posters")
            val posters: Posters?,

        ) {
            data class Buttonlists(
                @SerializedName("data")
                val data: List<Any>?
            )

            data class Crews(
                @SerializedName("data")
                val data: List<Any>?
            )

            data class Customhtmls(
                @SerializedName("data")
                val data: List<Any>?
            )

            data class Headersliders(
                @SerializedName("data")
                val data: List<Any>?
            )

            data class Listinfos(
                @SerializedName("data")
                val data: List<Any>?
            )

            data class Livetvs(
                @SerializedName("data")
                val data: List<Any>?
            )

            data class Menus(
                @SerializedName("data")
                val data: List<Any>?
            )

            data class Movies(
                @SerializedName("data")
                val data: List<Data>?
            ) {
                data class Data(
                    @SerializedName("id")
                    val id: Int?, // 45589
                    @SerializedName("type")
                    val type: String? // movies
                )
            }

            data class Posters(
                @SerializedName("data")
                val data: List<Data>?
            ) {
                data class Data(
                    @SerializedName("id")
                    val id: Int?, // 4339
                    @SerializedName("type")
                    val type: String? // posters
                )
            }




        }
    }

    data class Included(
        @SerializedName("attributes")
        val attributes: Attributes?,
        @SerializedName("id")
        val id: Int?, // 4339
        @SerializedName("type")
        val type: String? // posters
    ) {
        data class Attributes(

            @SerializedName("age_range")
            val ageRange: String?, // 6-12
            @SerializedName("cat_title_str")
            val catTitleStr: String?, // انیمیشن,ماجراجویی
            @SerializedName("categories")
            val categories: List<Category>?,
            @SerializedName("commingsoon_txt")
            val commingsoonTxt: String?,
            @SerializedName("countries")
            val countries: List<Country>?,
            @SerializedName("country")
            val country: String?, // آمریکا
            @SerializedName("cover")
            val cover: Any?, // https://static.cdn.asset.filimo.com/flmt/mov_cvr_45589_9626.jpg?width=2560&quality=90&secret=814bRSn9K87bv-RwnDJwJw
            @SerializedName("cover_data")
            val coverData: CoverData?,
            @SerializedName("descr")
            val descr: String?, // کایلو پسر چهارساله خلاق و حساسی است که خانواده و دوستان حمایتگرش به او کمک می کنند با احساسات خود آشنا شود و آن ها را بپذیرد.
            @SerializedName("director")
            val director: String?, // Courtney Beneteau

            @SerializedName("duration")
            val duration: Any?,
            @SerializedName("freemium")
            val freemium: Boolean?, // false
            @SerializedName("HD")
            val hD: Boolean?, // false
            @SerializedName("id")
            val id: Int?, // 45589
            @SerializedName("is_finished_online_release")
            val isFinishedOnlineRelease: Boolean?, // false
            @SerializedName("movie_id")
            val movieId: String?, // 45589
            @SerializedName("movie_title")
            val movieTitle: String?, // کایلو
            @SerializedName("movie_title_en")
            val movieTitleEn: String?, // Caillou
            @SerializedName("output_type")
            val outputType: String?, // poster
            @SerializedName("pic")
            val pic: Pic?,
            @SerializedName("pro_year")
            val proYear: String?, // ۲۰۲۴
            @SerializedName("publish_date")
            val publishDate: String?, // 2024-09-01 14:44:24

            @SerializedName("rate_enable")
            val rateEnable: Boolean?, // true
            @SerializedName("rate_enable_by_cnt")
            val rateEnableByCnt: Boolean?, // false

            @SerializedName("tag_id")
            val tagId: Int?, // 1000051
            @SerializedName("uid")
            val uid: String?, // 45589

            @SerializedName("watermark")
            val watermark: Boolean? // false
        ) {


            data class Category(
                @SerializedName("link_key")
                val linkKey: String?, // animation
                @SerializedName("link_type")
                val linkType: String?, // list
                @SerializedName("title")
                val title: String?, // انیمیشن
                @SerializedName("title_en")
                val titleEn: String? // animation
            )

            data class Country(
                @SerializedName("country")
                val country: String?, // کانادا
                @SerializedName("country_en")
                val countryEn: String? // canada
            )

            data class CoverData(
                @SerializedName("horizontal")
                val horizontal: String?, // https://static.cdn.asset.filimo.com/flmt/mov_cvr_filimo_hor_149604_782.jpg?width=1920&quality=80&secret=9UVc6Ics1V7gvgz2MD4ipw
                @SerializedName("vertical")
                val vertical: String? // https://static.cdn.asset.filimo.com/flmt/mov_cvr_filimo_ver_149604_123.jpg?width=960&quality=80&secret=fydEVJssLaZw2WNNicKgTA
            )


            data class Pic(
                @SerializedName("movie_img_b")
                val movieImgB: String?, // https://static.cdn.asset.filimo.com/flmt/mov_45589_185863.jpg?width=800&quality=85&secret=b58AVL38XwcMJ3RWrPXC9w
                @SerializedName("movie_img_m")
                val movieImgM: String?, // https://static.cdn.asset.filimo.com/flmt/mov_45589_185863.jpg?width=300&quality=85&secret=6mlvxvvY29gBpJ2uJjv4dg
                @SerializedName("movie_img_s")
                val movieImgS: String?, // https://static.cdn.asset.filimo.com/flmt/mov_45589_185863.jpg?width=165&quality=85&secret=8jZ-UTsHNVqTOfztkjhmMw

            )


        }
    }

    data class Links(
        @SerializedName("forward")
        val forward: String? // https://www.filimo.com/api/fa/v1/movie/movie/list/tagid/2001215/list_perpage/10/list_offset/10
    )

    data class Meta(
        @SerializedName("current_page")
        val currentPage: Int?, // 1
        @SerializedName("filter")
        val filter: Boolean?, // false
        @SerializedName("id")
        val id: Int?, // 1
        @SerializedName("md5")
        val md5: String?, // 6748286e9166a97c87e9d1430f49ed4d
        @SerializedName("per_page")
        val perPage: Int?, // 10
        @SerializedName("row_count")
        val rowCount: Int?, // 1
        @SerializedName("title")
        val title: String?, // تماشای آنلاین فیلم و سریال
        @SerializedName("title_en")
        val titleEn: String?, // home
        @SerializedName("tracker")
        val tracker: Any?, // null
        @SerializedName("trailer")
        val trailer: Trailer?,
        @SerializedName("web_url")
        val webUrl: Any? // null
    ) {
        data class Trailer(
            @SerializedName("enable")
            val enable: Boolean?, // false
            @SerializedName("host")
            val host: Any?, // null
            @SerializedName("url")
            val url: Any? // null
        )
    }
}