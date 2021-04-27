package tech.purplebeen.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.*

@Dao
interface IssueDao {
    @Query("SELECT * FROM Issue WHERE orgs = :orgs and repo = :repo")
    fun getAll(orgs: String, repo: String): Single<List<Issue>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(issues: List<Issue>): Completable
}