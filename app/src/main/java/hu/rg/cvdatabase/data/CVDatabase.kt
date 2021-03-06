package hu.rg.cvdatabase.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hu.rg.cvdatabase.data.dao.CVDao
import hu.rg.cvdatabase.data.entities.*

@Database( entities = [
    CV::class,
    School::class,
    Job::class,
],
    version = 2,
    exportSchema = false
)
abstract class CVDatabase() : RoomDatabase() {
    abstract val cvDao : CVDao
    companion object {
        @Volatile
        private var INSTANCE : CVDatabase?=null

        fun getInstance(context:Context) : CVDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    CVDatabase::class.java,
                    "cv_database"
                )
                    .fallbackToDestructiveMigration()
                    .build().also {
                    INSTANCE = it
                }
            }
        }
    }
}