package com.vas.feature_details_screen.domain.model

data class Hero(

    var id: Int,

    var name: String,

    var urlImg: String,

    var attackType: String,

    var roles: List<String>,

    var health: String,

    var healthRegen: String,

    var mana: String,

    var manaRegen: String,

    var armor: String,

    var str: String,

    var agi: String,

    var int: String,

    var strGain: String,

    var agiGain: String,

    var intGain: String,

    var attackMin: String,

    var attackMax: String,
)