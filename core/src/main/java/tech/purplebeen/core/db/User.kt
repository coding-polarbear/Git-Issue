package tech.purplebeen.core.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "User")
data class User(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "user_id")
    val id: Int,

    @ColumnInfo(name = "avatar_url")
    @SerializedName("avatar_url")
    val profileUrl: String,

    @ColumnInfo(name = "user_name")
    @SerializedName("login")
    val userName: String
)
