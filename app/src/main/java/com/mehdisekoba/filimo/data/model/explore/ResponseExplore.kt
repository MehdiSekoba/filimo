package com.mehdisekoba.filimo.data.model.explore


import com.google.gson.annotations.SerializedName

data class ResponseExplore(
    @SerializedName("data")
    val data: List<Data>?,
    @SerializedName("dataSource_key")
    val dataSourceKey: Int?, // 1
    @SerializedName("dataSource_type")
    val dataSourceType: String?, // glance
    @SerializedName("id")
    val id: Int?, // 1
    @SerializedName("itemsToShow")
    val itemsToShow: ItemsToShow?,
    @SerializedName("limit")
    val limit: Int?, // 15
    @SerializedName("links")
    val links: Links?,
    @SerializedName("list_tag_id")
    val listTagId: Int?, // 1
    @SerializedName("more_type")
    val moreType: String?, // infinity_slider
    @SerializedName("output_type")
    val outputType: String?, // glance
    @SerializedName("page")
    val page: Int?, // 1
    @SerializedName("rand_data")
    val randData: Boolean?, // false
    @SerializedName("reels_options")
    val reelsOptions: ReelsOptions?,
    @SerializedName("tag_id")
    val tagId: Any?, // null
    @SerializedName("theme")
    val theme: String? // reel
) {
    data class Data(
        @SerializedName("actions")
        val actions: List<Action>?,
        @SerializedName("age_range")
        val ageRange: String?, // 18-99
        @SerializedName("badge")
        val badge: Badge?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("filimo_rate")
        val filimoRate: FilimoRate?,
        @SerializedName("glance_key")
        val glanceKey: String?, // n6oab
        @SerializedName("glance_type")
        val glanceType: String?, // movie
        @SerializedName("id")
        val id: String?, // 1160
        @SerializedName("imdb_rate")
        val imdbRate: ImdbRate?,
        @SerializedName("is_bookmarked")
        val isBookmarked: Boolean?, // false
        @SerializedName("is_series")
        val isSeries: Boolean?, // true
        @SerializedName("logo")
        val logo: String?, // https://static.cdn.asset.filimo.com/cup/glnc_1160_logo_2208.png?width=300&quality=85&secret=1M2L5kBDMNa083zXqxiQsA
        @SerializedName("output_type")
        val outputType: String?, // glance
        @SerializedName("parent")
        val parent: String?, // 148679
        @SerializedName("pic")
        val pic: Pic?,
        @SerializedName("theme")
        val theme: String?, // reel
        @SerializedName("timer")
        val timer: Timer?,
        @SerializedName("title")
        val title: String?, // قسمت 6 گل یا پوچ
        @SerializedName("title_en")
        val titleEn: String?,
        @SerializedName("trailer")
        val trailer: Trailer?
    ) {
        data class Action(
            @SerializedName("action_type")
            val actionType: String?, // share
            @SerializedName("icon")
            val icon: String?, // https://www.filimo.com/assets/web/ui/img-nhQbBQB8PcCL8uqRe4vStg/icons/share.svg
            @SerializedName("link_key")
            val linkKey: String?, // https://www.filimo.com/m/n6oab/?glid=1160
            @SerializedName("link_text")
            val linkText: String?,
            @SerializedName("link_type")
            val linkType: String? // web
        )

        data class Badge(
            @SerializedName("info")
            val info: List<Info?>?,
            @SerializedName("new_episode")
            val newEpisode: Boolean? // true
        ) {
            data class Info(
                @SerializedName("background_color")
                val backgroundColor: String?, // #0C0C0C
                @SerializedName("icon")
                val icon: String?,
                @SerializedName("text")
                val text: String?, // قسمت جدید
                @SerializedName("text_color")
                val textColor: String?, // #FFFFFF
                @SerializedName("type")
                val type: String?, // new_episode
                @SerializedName("ui_type")
                val uiType: String? // text
            )
        }

        data class FilimoRate(
            @SerializedName("average")
            val average: Int?, // 0
            @SerializedName("enable")
            val enable: Boolean?, // false
            @SerializedName("percent")
            val percent: Int? // 0
        )

        data class ImdbRate(
            @SerializedName("average")
            val average: Double?, // 0
            @SerializedName("enable")
            val enable: Boolean? // false
        )

        data class Pic(
            @SerializedName("img_b")
            val imgB: String?, // https://static.cdn.asset.filimo.com/cup/glnc_1160_pic_4986.jpg?width=607&quality=85&secret=D5DlJUG74yNO_oiGXwqlwQ
            @SerializedName("img_m")
            val imgM: String?, // https://static.cdn.asset.filimo.com/cup/glnc_1160_pic_4986.jpg?width=324&quality=85&secret=5JfpE8beNw0E-hKSzU3-Xw
            @SerializedName("img_s")
            val imgS: String? // https://static.cdn.asset.filimo.com/cup/glnc_1160_pic_4986.jpg?width=162&quality=85&secret=ejLginthYIB3AyHHIfCBtQ
        )

        data class Timer(
            @SerializedName("enable")
            val enable: Boolean?, // false
            @SerializedName("publish_date")
            val publishDate: String? // 2024-09-01 08:00:00
        )

        data class Trailer(
            @SerializedName("enable")
            val enable: Boolean?, // true
            @SerializedName("host")
            val host: String?, // aparat
            @SerializedName("url")
            val url: String? // https://persian18.asset.aparat.com/aparat-video/76bbb0284d8cbb0ac41ff4b3e1d5587260671254-720p.apt?wmsAuthSign=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbiI6IjQzYzk1ZWQxNDZlNzhhYjAwYzI2NjhhNmMyMDJkMDU1IiwiZXhwIjoxNzI1MjcxOTE2LCJpc3MiOiJTYWJhIElkZWEgR1NJRyJ9.vXaGMLLUHNszWEj9l1jDIxnO9N2QpnIIfWx-SSVKEIw
        )
    }

    data class ItemsToShow(
        @SerializedName("default")
        val default: Int?, // 4
        @SerializedName("lg")
        val lg: Int?, // 4
        @SerializedName("md")
        val md: Int?, // 4
        @SerializedName("sm")
        val sm: Int?, // 4
        @SerializedName("xl")
        val xl: Int?, // 4
        @SerializedName("xs")
        val xs: Int?, // 2
        @SerializedName("xxs")
        val xxs: Int? // 1
    )

    data class Links(
        @SerializedName("first")
        val first: String?, // https://www.filimo.com/api/fa/v1/etc/glance/list/page/1
        @SerializedName("more_records")
        val moreRecords: Boolean?, // true
        @SerializedName("next")
        val next: String?, // https://www.filimo.com/api/fa/v1/etc/glance/list/page/2/perpage/15
        @SerializedName("prev")
        val prev: String?, // https://www.filimo.com/api/fa/v1/etc/glance/list/page/1/perpage/15
        @SerializedName("self")
        val self: String? // https://www.filimo.com/api/fa/v1/etc/glance/list/page/1/perpage/15
    )

    data class ReelsOptions(
        @SerializedName("swipe_left")
        val swipeLeft: SwipeLeft?,
        @SerializedName("swipe_right")
        val swipeRight: SwipeRight?,
        @SerializedName("swipe_up")
        val swipeUp: SwipeUp?
    ) {
        data class SwipeLeft(
            @SerializedName("text")
            val text: String? // با زدن روی فلش‌ها، فیلیموتور بعدی را ببینید.
        )

        data class SwipeRight(
            @SerializedName("text")
            val text: String? // با زدن روی فلش‌ها، فیلیموتور بعدی را ببینید.
        )

        data class SwipeUp(
            @SerializedName("text")
            val text: String? // برای شروع تور، رو به بالا بکشید.
        )
    }
}