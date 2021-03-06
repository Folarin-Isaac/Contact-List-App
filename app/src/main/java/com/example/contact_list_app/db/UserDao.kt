package com.example.contact_list_app.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User):Long

    @Query("SELECT * from users WHERE emailAddress = (:email) LIMIT 1")
    fun findUser(email:String): LiveData<User>

}