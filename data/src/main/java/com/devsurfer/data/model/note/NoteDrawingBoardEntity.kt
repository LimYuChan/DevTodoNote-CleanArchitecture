package com.devsurfer.data.model.note

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "note_drawing_board", foreignKeys = [
    ForeignKey(
        entity = NoteContentEntity::class,
        parentColumns = ["content_id"],
        childColumns = ["note_content_id"]
    )
])
data class NoteDrawingBoardEntity(
    @PrimaryKey(autoGenerate = true) val drawing_board_id: Long = 0,
    val note_content_id: Long,
    val file_json_string: String,
    val file_image_url: String
)