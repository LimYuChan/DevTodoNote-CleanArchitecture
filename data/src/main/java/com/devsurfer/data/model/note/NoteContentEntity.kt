package com.devsurfer.data.model.note

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "note_content")
data class NoteContentEntity(
    @PrimaryKey(autoGenerate = true) val content_id: Long = 0,
    val content: String,
    val created_at: Long? = 0,
    val updated_at: Long? = 0,
    val repository_id: Int = 0,
    val branch: String? = null,
    val branch_state: String = "none",
    val status:Int = 0
)
