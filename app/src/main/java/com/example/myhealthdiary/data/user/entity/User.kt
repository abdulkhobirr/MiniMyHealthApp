package com.example.myhealthdiary.data.user.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
        @PrimaryKey
        val Username: String,
        val Password: String,
        val Nama: String,
        val Ttl: String,
        val Gender: String
): Parcelable