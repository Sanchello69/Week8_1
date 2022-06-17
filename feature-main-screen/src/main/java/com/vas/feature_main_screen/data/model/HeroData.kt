package com.vas.feature_main_screen.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HeroData(

    @Json(name = "id")
    var id: Int,

    @Json(name = "localized_name")
    var name: String,

    @Json(name = "icon")
    var urlIcon: String
)
