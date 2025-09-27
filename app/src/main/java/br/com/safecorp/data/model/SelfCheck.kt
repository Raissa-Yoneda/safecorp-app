package br.com.safecorp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "self_checks")
data class SelfCheck(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: Date,
    val mood: String,
    val note: String?
) 