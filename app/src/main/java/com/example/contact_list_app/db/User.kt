package com.example.contact_list_app.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val emailAddress: String,
    @ColumnInfo(name = "password") val password: String

)