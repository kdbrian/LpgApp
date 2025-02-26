package com.example.lpg.android.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lpg.android.data.local.dao.OrdersDao
import com.example.lpg.android.data.local.model.Order
import com.example.lpg.android.data.local.util.Converters
import com.example.lpg.android.util.SecurePrefs
import net.sqlcipher.database.SupportFactory

@Database(entities = [Order::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class LpgAppDb : RoomDatabase() {


    abstract fun ordersDao(): OrdersDao

    companion object {
        @Volatile
        var INSTANCE: LpgAppDb? = null

        fun getDatabase(context: Context): LpgAppDb {
            return INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context): LpgAppDb {

            val passphrase = SecurePrefs.getPassphrase(context)
            println("pass : $passphrase")
            val factory = SupportFactory(passphrase)

            return Room
                .databaseBuilder(
                    context = context.applicationContext,
                    klass = LpgAppDb::class.java,
                    name = "lpg_app_db"
                )
                .openHelperFactory(factory)
                .fallbackToDestructiveMigration()
                .build()
        }

    }
}