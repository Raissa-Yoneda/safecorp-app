package br.com.safecorp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.safecorp.data.dao.AssessmentDao
import br.com.safecorp.data.dao.SelfCheckDao
import br.com.safecorp.data.model.Assessment
import br.com.safecorp.data.model.SelfCheck
import br.com.safecorp.util.DateConverter

@Database(entities = [Assessment::class, SelfCheck::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun assessmentDao(): AssessmentDao
    abstract fun selfCheckDao(): SelfCheckDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "safecorp_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
} 