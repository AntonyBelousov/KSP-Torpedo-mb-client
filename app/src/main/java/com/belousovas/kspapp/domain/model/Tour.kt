package com.belousovas.kspapp.domain.model

data class Tour(
    val tourName: String,
    val tourId: String,
) {
    lateinit var gameList: List<Game>
    lateinit var tourDeadline: String

    override fun toString(): String {
        return "Tour(tourName='$tourName', tourId='$tourId', gameList=$gameList, tourDeadline='$tourDeadline')"
    }
}