package com.issues.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Issue(
    @PrimaryKey(autoGenerate = true) val courseID: Int,
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "user") val user: String?,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "avatar") val avatar: String?,
    @ColumnInfo(name = "comment") val comment: String?
)
