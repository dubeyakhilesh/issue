package com.issues.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ImageDao {
    @Insert
    public fun insert(image:ImageTable):Long

    @Query("SELECT * FROM ImageTable where id = :id")
    public fun getImage(id:String):List<ImageTable>
}