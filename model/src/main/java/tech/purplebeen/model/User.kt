package tech.purplebeen.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatar_url") val profileUrl: String,
    @SerializedName("login") val userName: String
)
