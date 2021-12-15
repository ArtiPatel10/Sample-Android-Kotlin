package com.sample.helper.dummyData

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class GymData (
    @SerializedName("gyms")
    @Expose
    var gyms: ArrayList<Gym>? = null
)


data class Gym (
    @SerializedName("id")
    var id: Int=0,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("rating")
    var rating: Double? = null,

    @SerializedName("price")
    var price: Int? = null,

    @SerializedName("favorite")
    var favorite: Boolean = false,

    @SerializedName("date")
    var date: String? = null,

    @SerializedName("popular_clasess")
    var popularClasses: List<PopularClasess>? = null
)


data class PopularClasess (
    @SerializedName("title")
    var title: String? = null,

    @SerializedName("price")
    var price: Int? = null,

    @SerializedName("favorite")
    var favorite: Boolean=false,

    @SerializedName("location")
    var location: String? = null,

    @SerializedName("time")
    var time: String? = null
)
