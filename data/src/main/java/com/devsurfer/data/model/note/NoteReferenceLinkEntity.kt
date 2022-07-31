package com.devsurfer.data.model.note

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "note_reference_link", foreignKeys = [
    ForeignKey(
        entity = NoteContentEntity::class,
        parentColumns = ["content_id"],
        childColumns = ["note_content_id"]
    )
])
data class NoteReferenceLinkEntity(
    @PrimaryKey(autoGenerate = true) val note_reference_link_id: Long = 0,
    val note_content_id: Long,
    val link: String
)
