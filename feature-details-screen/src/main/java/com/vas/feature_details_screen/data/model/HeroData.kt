package com.vas.feature_details_screen.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HeroData(

    @Json(name = "id")
    var id: Int,

    @Json(name = "localized_name")
    var name: String,

    @Json(name = "img")
    var urlImg: String,

    @Json(name = "attack_type")
    var attackType: String,

    @Json(name = "roles")
    var roles: List<String>,

    @Json(name = "base_health")
    var health: String,

    @Json(name = "base_health_regen")
    var healthRegen: String,

    @Json(name = "base_mana")
    var mana: String,

    @Json(name = "base_mana_regen")
    var manaRegen: String,

    @Json(name = "base_armor")
    var armor: String,

    @Json(name = "base_str")
    var str: String,

    @Json(name = "base_agi")
    var agi: String,

    @Json(name = "base_int")
    var int: String,

    @Json(name = "str_gain")
    var strGain: String,

    @Json(name = "agi_gain")
    var agiGain: String,

    @Json(name = "int_gain")
    var intGain: String,

    @Json(name = "base_attack_min")
    var attackMin: String,

    @Json(name = "base_attack_max")
    var attackMax: String,
)
