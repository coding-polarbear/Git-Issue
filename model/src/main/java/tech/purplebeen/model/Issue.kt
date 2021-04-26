package tech.purplebeen.model

import com.google.gson.annotations.SerializedName

data class Issue(
    @SerializedName("number") val number: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String
    )