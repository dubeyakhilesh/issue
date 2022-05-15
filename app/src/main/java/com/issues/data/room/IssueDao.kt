package com.issues.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IssueDao {
    @Query("SELECT * FROM Issue")
    suspend fun getAll(): List<Issue>

    @Insert
    suspend fun insertAll(issue: List<Issue>)

    @Delete
    suspend fun delete(issue: Issue)
}