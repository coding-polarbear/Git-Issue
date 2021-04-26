package tech.purplebeen.core.db

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Issue")
data class Issue(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int,

    @ColumnInfo( name = "number")
    @SerializedName("number")
    val number: Int,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String,

    @ColumnInfo(name = "body")
    @SerializedName("body")
    val body: String,

    @ColumnInfo(name="orgs")
    val orgs: String,

    @ColumnInfo(name = "repo")
    val repo: String,

    @Embedded
    @SerializedName("user") val user: User
)