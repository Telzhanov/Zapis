package com.example.zapis_test.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SalonResponse(
    @SerializedName("salons") val salons: List<Salon>
)

data class Salon (
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("type") val type : String,
    @SerializedName("checkRating") val checkRating: Int?,
    @SerializedName("pictureUrl") val pictureUrl: String?
): Serializable


data class SalonDetail(
    @SerializedName("salon") val salon : SalonLong,
    @SerializedName("services") val services : List<Service>
)

data class Service(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("price") val price : Int,
    @SerializedName("priceMax") val priceMax : Int,
    @SerializedName("duration") val duration : Int,
    @SerializedName("description") val description : String,
    @SerializedName("priceStr") val priceStr : String,
    @SerializedName("categoryId") val categoryId : Int
)


data class SalonLong (

    @SerializedName("id") val id : Int,
    @SerializedName("category") val category : String,
    @SerializedName("name") val name : String,
    @SerializedName("address") val address : String,
    @SerializedName("workStartTime") val workStartTime : String,
    @SerializedName("workEndTime") val workEndTime : String,
    @SerializedName("description") val description : String,
    @SerializedName("type") val type : String,
    @SerializedName("checkRating") val checkRating : Int,
    @SerializedName("instagramProfile") val instagramProfile : String,
    @SerializedName("isMastersHidden") val isMastersHidden : Boolean,
    @SerializedName("avatarUrl") val avatarUrl : String,
    @SerializedName("reviewCount") val reviewCount : Int,
    @SerializedName("averageRating") val averageRating : Double,
    @SerializedName("phoneNumbers") val phoneNumbers : List<String>,
    @SerializedName("todayReservationsCount") val todayReservationsCount : String,
    @SerializedName("pictures") val pictures : List<String>
)