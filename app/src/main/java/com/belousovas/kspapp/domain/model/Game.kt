package com.belousovas.kspapp.domain.model

data class Game(
    val match: String,
    val betIndex: String
) {
    lateinit var outcome: String
}