package com.devsurfer.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devsurfer.data.model.note.NoteContentEntity
import com.devsurfer.data.model.note.NoteDrawingBoardEntity
import com.devsurfer.data.model.note.NoteImageEntity
import com.devsurfer.data.model.note.NoteReferenceLinkEntity
import com.devsurfer.data.repository.note.dataSource.*

@Database(
    entities = [NoteContentEntity::class, NoteImageEntity::class, NoteDrawingBoardEntity::class, NoteReferenceLinkEntity::class],
    version = 2
)
abstract class AppDatabase: RoomDatabase(){
    abstract fun noteDao(): NoteDao
    abstract fun noteContentDao(): NoteContentDao
    abstract fun noteImageDao(): NoteImageDao
    abstract fun noteDrawingBoardDao(): NoteDrawingBoardDao
    abstract fun noteReferenceLinkDao(): NoteReferenceLinkDao
}