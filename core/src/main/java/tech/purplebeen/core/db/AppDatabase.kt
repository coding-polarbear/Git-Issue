package tech.purplebeen.core.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Issue::class, User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun issueDao(): IssueDao
}