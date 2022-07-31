package com.devsurfer.data.di

import android.content.Context
import androidx.room.Room
import com.devsurfer.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "devTodoNote_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideNote(database: AppDatabase) = database.noteDao()

    @Provides
    fun provideNoteContentDao(database: AppDatabase) = database.noteContentDao()

    @Provides
    fun provideNoteImageDao(database: AppDatabase) = database.noteImageDao()

    @Provides
    fun provideNoteDrawingBoardDao(database: AppDatabase) = database.noteDrawingBoardDao()

    @Provides
    fun provideNoteReferenceLinkDao(database: AppDatabase) = database.noteReferenceLinkDao()
}