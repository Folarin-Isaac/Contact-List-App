package com.example.contact_list_app.db

import android.content.Context
import androidx.room.Room

object UserDatabaseInstance {
    private lateinit var db: UserDatabase

    fun getInstance(applicationContext: Context): UserDatabase {

        if ((!this::db.isInitialized)) {
            db = Room.databaseBuilder(
                applicationContext,
                UserDatabase::class.java, UserDatabase.databaseName
            ).build()
        }

        return db

    }

}
