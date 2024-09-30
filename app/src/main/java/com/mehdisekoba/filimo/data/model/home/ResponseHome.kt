package com.mehdisekoba.filimo.data.model.home


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.mehdisekoba.filimo.data.model.home.ResponseHome.Data.Attributes.SmartiesInfo
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

data class ResponseHome(
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
        val id: Int?, // 1
        @SerializedName("relationships")
        val relationships: Relationships?,
        @SerializedName("type")
        val type: String? // Row
    ) {
        data class Attributes(
            @SerializedName("id")
            val id: Int,
            @SerializedName("tag_id")
            val tagId: Any?,
            @SerializedName("output_type")
            val outputType: String,
            @SerializedName("theme")
            val theme: String,
            @SerializedName("more_type")
            val moreType: String,
            @SerializedName("line_count")
            val lineCount: Any?,
            @SerializedName("showSerialParent")
            val showSerialParent: Boolean?,
            @SerializedName("link_to_episode")
            val linkToEpisode: Boolean?,
            @SerializedName("link_type")
            val linkType: String,
            @SerializedName("link_key")
            val linkKey: Any?,
            @SerializedName("show_all")
            val showAll: Boolean,
            @SerializedName("itemsToShow")
            val itemsToShow: ItemsToShow?,
            @SerializedName("has_downvote")
            val hasDownvote: Boolean?,
            @SerializedName("list_tag_id")
            val listTagId: String,
            @SerializedName("link_text")
            val linkText: String,
            @SerializedName("tracker")
            val tracker: Tracker,
            @SerializedName("md5")
            val md5: String,
            @SerializedName("link_to_status_tag_id")
            val linkToStatusTagId: Long?,

            @SerializedName("use_special")
            val useSpecial: String?,

            @SerializedName("show_extra_info")
            val showExtraInfo: Boolean?,


            ) {
            data class ItemsToShow(
                @SerializedName("default")
                val default: Int?, // 5
                @SerializedName("lg")
                val lg: Int?, // 5
                @SerializedName("md")
                val md: Int?, // 4
                @SerializedName("sm")
                val sm: Int?, // 4
                @SerializedName("xl")
                val xl: Int?, // 5
                @SerializedName("xs")
                val xs: Int?, // 2
                @SerializedName("xxs")
                val xxs: Int? // 1
            )

            data class SmartiesInfo(
                @SerializedName("total_score")
                val totalScore: Double,
                @SerializedName("smarties_type")
                val smartiesType: String,
                @SerializedName("row_type")
                val rowType: String,
                @SerializedName("row_slug")
                val rowSlug: String,
                @SerializedName("row_source")
                val rowSource: String,
                @SerializedName("row_full_title")
                val rowFullTitle: String,
                @SerializedName("row_label")
                val rowLabel: String,

                )

            data class Tracker(

                @SerializedName("Index")
                val index: Int,
                @SerializedName("Tagid")
                val tagid: Any?,
                @SerializedName("Title")
                val title: String,
                @SerializedName("Algorithm")
                val algorithm: String,
                @SerializedName("Slug")
                val slug: String?,
                @SerializedName("Source")
                val source: String?,
                @SerializedName("Type")
                val type: String?,
                @SerializedName("Label")
                val label: String?,
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
            @SerializedName("taglists")
            val taglists: Taglists?,
            @SerializedName("webinapps")
            val webinapps: Webinapps?
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
                val data: List<Data>?
            ) {
                data class Data(
                    @SerializedName("id")
                    val id: Int?, // 1
                    @SerializedName("type")
                    val type: String? // customhtmls
                )
            }

            data class Headersliders(
                @SerializedName("data")
                val data: List<Data>?
            ) {
                data class Data(
                    @SerializedName("id")
                    val id: Int?, // 1
                    @SerializedName("type")
                    val type: String? // headersliders
                )
            }

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
                    val id: Int?, // 149117
                    @SerializedName("type")
                    val type: String? // movies
                )
            }

            data class Posters(
                @SerializedName("data")
                val data: List<Any>?
            )

            data class Taglists(
                @SerializedName("data")
                val data: List<Any>?
            )

            data class Webinapps(
                @SerializedName("data")
                val data: List<Any>?
            )
        }
    }

    data class Included(
        @SerializedName("attributes")
        val attributes: @RawValue Attributes?,
        @SerializedName("id")
        val id: Int?, // 1
        @SerializedName("type")
        val type: String? // headersliders
    ) {
        @Parcelize
        data class Attributes(
            val id: Int?,
            @SerializedName("link_type")
            val linkType: String?,
            @SerializedName("slider_type")
            val sliderType: String?,
            @SerializedName("link_key")
            val linkKey: String?,
            @SerializedName("link_text")
            val linkText: String?,
            @SerializedName("button_type")
            val buttonType: String?,
            @SerializedName("is_headerslider")
            val isHeaderslider: Boolean?,
            @SerializedName("title")
            val title: @RawValue String?,
            @SerializedName("desc")
            val desc: @RawValue String?,
            @SerializedName("cover_mobile")
            val coverMobile: @RawValue List<String>?,
            @SerializedName("cover_desktop")
            val coverDesktop: @RawValue Any?,
            @SerializedName("logo")
            val logo: String?,
            @SerializedName("is_exclusive")
            val isExclusive: Boolean?,
            @SerializedName("exclusive_icon")
            val exclusiveIcon: String?,
            @SerializedName("is_iranian")
            val isIranian: @RawValue Boolean?,
            val commingSoon: Boolean?,
            @SerializedName("no_timer")
            val noTimer: Boolean?,
            @SerializedName("publish_date")
            val publishDate: String?,
            val frameId: String?,
            @SerializedName("categories")
            val categories: @RawValue List<Category>?,
            @SerializedName("trial_used")
            val trialUsed: String?,
            val bgcolor: String?,
            val uid: String?,
            @SerializedName("cover")
            val coverMovie: @RawValue Any?,
            @SerializedName("is_series")
            val isSeries: Boolean?,
            @SerializedName("to_parent")
            val toParent: Long?,
            @SerializedName("content_type")
            val contentType: String?,
            @SerializedName("is_dubbed")
            val isDubbed: @RawValue Boolean?,
            @SerializedName("has_subtitle")
            val hasSubtitle: @RawValue Boolean?,
            @SerializedName("content_desc")
            val contentDesc: String?,
            val theme: String?,
            @SerializedName("output_type")
            val outputType: String?,
            @SerializedName("movie_id")
            val movieId: String?,
            @SerializedName("movie_title")
            val movieTitle: String?,
            @SerializedName("movie_title_en")
            val movieTitleEn: String?,
            @SerializedName("tag_id")
            val tagId: Int?,
            val watermark: Boolean?,
            @SerializedName("HD")
            val hd: @RawValue Boolean?,
            @SerializedName("watch_list_action")
            val watchListAction: String?,
            @SerializedName("commingsoon_txt")
            val commingsoonTxt: String?,
            @SerializedName("rate_enable")
            val rateEnable: Boolean?,
            @SerializedName("rate_enable_by_cnt")
            val rateEnableByCnt: Boolean?,
            @SerializedName("pic")
            val pic: @RawValue Pic?,
            @SerializedName("pro_year")
            val proYear: String?,
            @SerializedName("cat_title_str")
            val catTitleStr: String?,
            val descr: String?,
            val director: String?,
            @SerializedName("movie_logo")
            val movieLogo: String?,
            val fullscreen: Boolean?,
            @SerializedName("cover_data")
            val coverData: @RawValue CoverData?,
            @SerializedName("age_range")
            val ageRange: @RawValue String?,
            @SerializedName("countries")
            val countries: @RawValue List<Country>?,
            val path: String?,
            @SerializedName("duration")
            val duration:@RawValue Any?,

        ) : Parcelable {
            data class Category(
                @SerializedName("title_en")
                val titleEn: String,
                val title: String,
                @SerializedName("link_type")
                val linkType: String,
                @SerializedName("link_key")
                val linkKey: String,
            )

            data class CoverData(

                @SerializedName("horizontal")
                val horizontal: @RawValue String?,

                @SerializedName("vertical")
                val vertical: String?
            )

            data class Pic(
                @SerializedName("movie_img_s")
                val movieImgS: String,
                @SerializedName("movie_img_m")
                val movieImgM: String,
                @SerializedName("movie_img_b")
                val movieImgB: String,
            )

            data class Country(
                @SerializedName("country")
                val country: @RawValue String?, // استرالیا
                @SerializedName("country_en")
                val countryEn: String? // australia
            )



        }
    }

    data class Links(
        @SerializedName("forward")
        val forward: String? // https://www.filimo.com/api/fa/v1/movie/movie/list/tagid/1/list_perpage/10/list_offset/10
    )

    data class Meta(
        @SerializedName("current_page")
        val currentPage: Int?, // 1
        @SerializedName("filter")
        val filter: Boolean?, // false
        @SerializedName("id")
        val id: String?, // 1
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