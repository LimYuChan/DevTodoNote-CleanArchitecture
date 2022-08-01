package com.devsurfer.data.model.note

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "note_image", foreignKeys = [
    ForeignKey(
        entity = NoteContentEntity::class,
        parentColumns = ["content_id"],
        childColumns = ["note_content_id"],
        onDelete = ForeignKey.CASCADE
    )
])
data class NoteImageEntity(
    @PrimaryKey(autoGenerate = true) val image_id: Long = 0,
    val note_content_id: Long,
    val file_id: Long,
    val file_name: String,
    val file_url: String
)
