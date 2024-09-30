package com.mehdisekoba.filimo.data.model.detail


import com.google.gson.annotations.SerializedName

data class ResponseDetail(
    @SerializedName("data")
    val data: Data?
) {
    data class Data(
        @SerializedName("attributes")
        val attributes: Attributes?,
        @SerializedName("id")
        val id: Int?, // null
        @SerializedName("type")
        val type: String? // Review
    ) {
        data class Attributes(
            @SerializedName("ActorCrewData")
            val actorCrewData: ActorCrewData?,
            @SerializedName("albumData")
            val albumData: Any?, // null
            @SerializedName("aparatTrailer")
            val aparatTrailer: AparatTrailer?,
            @SerializedName("crewData")
            val crewData: Any?, // null
            @SerializedName("kodoumo_link")
            val kodoumoLink: String?,
            @SerializedName("OtherCrewData")
            val otherCrewData: List<OtherCrewData>?,
            @SerializedName("quotation")
            val quotation: Any?, // null
            @SerializedName("review")
            val review: Any?, // null
            @SerializedName("reviewMovieDetails")
            val reviewMovieDetails: Any?, // null
            @SerializedName("reviewMovieId")
            val reviewMovieId: String?, // 21819
            @SerializedName("youtube_link")
            val youtubeLink: Any? // null
        ) {
            data class ActorCrewData(
                @SerializedName("post_info")
                val postInfo: PostInfo?,
                @SerializedName("profile")
                val profile: List<Profile>?
            ) {
                data class PostInfo(
                    @SerializedName("title")
                    val title: String?, // بازیگر
                    @SerializedName("title_ar")
                    val titleAr: String?, // ممثل
                    @SerializedName("title_en")
                    val titleEn: String?, // actor
                    @SerializedName("title_fa")
                    val titleFa: String? // بازیگر
                )

                data class Profile(
                    @SerializedName("bio")
                    val bio: String?, //  بازیگر کانادایی و متولد ۷ سپتامبر ۱۹۷۸ در ونکوور است.وی بیشتر به خاطر حضور در فیلم مقصد نهایی ۱ مشهور است.
                    @SerializedName("bio_en")
                    val bioEn: String?,
                    @SerializedName("link_key")
                    val linkKey: String?, // https://www.filimo.com/api/fa/v1/movie/movie/list/tagid/1000300/text/Devon-Sawa/fl/crew
                    @SerializedName("link_type")
                    val linkType: String?, // crew
                    @SerializedName("name")
                    val name: String?, // دوون ساوا
                    @SerializedName("name_ar")
                    val nameAr: String?,
                    @SerializedName("name_en")
                    val nameEn: String?, // Devon Sawa
                    @SerializedName("name_fa")
                    val nameFa: String?, // دوون ساوا
                    @SerializedName("pic")
                    val pic: String?, // yes
                    @SerializedName("post_info")
                    val postInfo: PostInfo?,
                    @SerializedName("profile_image")
                    val profileImage: String?, // https://static.cdn.asset.filimo.com/flmt/rvw_cast_2661_1-b.jpg?width=220&quality=85&secret=h3vSSzxUdt0rKs6F6DJ5ug
                    @SerializedName("role_name")
                    val roleName: String?,
                    @SerializedName("role_name_ar")
                    val roleNameAr: String?,
                    @SerializedName("role_name_en")
                    val roleNameEn: String?,
                    @SerializedName("role_name_fa")
                    val roleNameFa: String?
                ) {
                    data class PostInfo(
                        @SerializedName("title")
                        val title: String?, // بازیگر
                        @SerializedName("title_ar")
                        val titleAr: String?, // ممثل
                        @SerializedName("title_en")
                        val titleEn: String?, // Actor
                        @SerializedName("title_fa")
                        val titleFa: String? // بازیگر
                    )
                }
            }

            data class AparatTrailer(
                @SerializedName("big_poster")
                val bigPoster: String?, // https://static.cdn.asset.aparat.com/avt/60721907-9982-l__7814.jpg?width=900&quality=90&secret=vwzVOIyQyxK4o1aetv2NLw
                @SerializedName("duration")
                val duration: String?, // 1:42
                @SerializedName("embed")
                val embed: String?, // https://www.aparat.com/video/video/embed/videohash/msj75pn/vt/frame
                @SerializedName("file_link")
                val fileLink: String?, // https://caspian17.asset.aparat.com/aparat-video/16859c1cdbe838036456bc05364baaee60721907-1080p.mp4?wmsAuthSign=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbiI6IjU3ZWY3ZjEwNWI3MGRmYjg0ZGMyZmJkM2EwY2I5ZTFhIiwiZXhwIjoxNzI2NDM2NDUwLCJpc3MiOiJTYWJhIElkZWEgR1NJRyJ9.aLq5-N6i6WDD6BnHzMzRABlnpxCAmUBnhS54-M89P_8
                @SerializedName("frame_link")
                val frameLink: String?, // https://caspian17.asset.aparat.com/aparat-video/16859c1cdbe838036456bc05364baaee60721907-1080p.mp4?wmsAuthSign=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbiI6IjU3ZWY3ZjEwNWI3MGRmYjg0ZGMyZmJkM2EwY2I5ZTFhIiwiZXhwIjoxNzI2NDM2NDUwLCJpc3MiOiJTYWJhIElkZWEgR1NJRyJ9.aLq5-N6i6WDD6BnHzMzRABlnpxCAmUBnhS54-M89P_8
                @SerializedName("hls_link")
                val hlsLink: String?, // https://www.aparat.com/video/hls/manifest/videohash/msj75pn/f/msj75pn.m3u8?k=acfeb
                @SerializedName("preview")
                val preview: Any?, // null
                @SerializedName("preview_mode_link")
                val previewModeLink: String?, // https://www.filimo.com/w/zpij5?pm
                @SerializedName("small_poster")
                val smallPoster: String?, // https://static.cdn.asset.aparat.com/avt/60721907-9982-l__7814.jpg?width=300&quality=90&secret=gomni0pDRnZbcPmJGfYB1w
                @SerializedName("thumb")
                val thumb: String?, // https://static.cdn.asset.aparat.com/avt/60721907-9982-l__7814.jpg?width=900&quality=90&secret=vwzVOIyQyxK4o1aetv2NLw
                @SerializedName("title")
                val title: String?, // تریلر فیلم نابود شده Consumed
                @SerializedName("type")
                val type: String? // video
            )

            data class OtherCrewData(
                @SerializedName("post_info")
                val postInfo: PostInfo?,
                @SerializedName("profile")
                val profile: List<Profile>?
            ) {
                data class PostInfo(
                    @SerializedName("title")
                    val title: String?, // کارگردان
                    @SerializedName("title_ar")
                    val titleAr: String?, // المخرج
                    @SerializedName("title_en")
                    val titleEn: String?, // Director
                    @SerializedName("title_fa")
                    val titleFa: String? // کارگردان
                )

                data class Profile(
                    @SerializedName("bio")
                    val bio: String?,
                    @SerializedName("bio_en")
                    val bioEn: String?,
                    @SerializedName("link_key")
                    val linkKey: String?, // https://www.filimo.com/api/fa/v1/movie/movie/list/tagid/1000300/text/Mitchell-Altieri/fl/crew
                    @SerializedName("link_type")
                    val linkType: String?, // crew
                    @SerializedName("name")
                    val name: String?, // میشل آلتیری
                    @SerializedName("name_ar")
                    val nameAr: String?,
                    @SerializedName("name_en")
                    val nameEn: String?, // Mitchell Altieri
                    @SerializedName("name_fa")
                    val nameFa: String?, // میشل آلتیری
                    @SerializedName("pic")
                    val pic: String?, // no
                    @SerializedName("post_info")
                    val postInfo: PostInfo?,
                    @SerializedName("profile_image")
                    val profileImage: String?, // https://www.filimo.com/assets/web/ui/img-MNaA6gL86cEye3I9HSmKKQ/Artboard.png
                    @SerializedName("role_name")
                    val roleName: String?,
                    @SerializedName("role_name_ar")
                    val roleNameAr: String?,
                    @SerializedName("role_name_en")
                    val roleNameEn: String?,
                    @SerializedName("role_name_fa")
                    val roleNameFa: String?
                ) {
                    data class PostInfo(
                        @SerializedName("title")
                        val title: String?, // کارگردان
                        @SerializedName("title_ar")
                        val titleAr: String?, // المخرج
                        @SerializedName("title_en")
                        val titleEn: String?, // Director
                        @SerializedName("title_fa")
                        val titleFa: String? // کارگردان
                    )
                }
            }
        }
    }
}