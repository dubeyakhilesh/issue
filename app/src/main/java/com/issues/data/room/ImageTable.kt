package com.issues.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageTable (
    @PrimaryKey(autoGenerate = true) val courseID: Int,
    @ColumnInfo(name = "id") val id: String?,
    @ColumnInfo(name = "Image_Data")
    val image: String?
)