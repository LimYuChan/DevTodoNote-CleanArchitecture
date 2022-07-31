package com.devsurfer.data.di

import com.devsurfer.data.repository.auth.AuthRepositoryImpl
import com.devsurfer.data.repository.auth.dataSource.AuthRemoteDataSource
import com.devsurfer.data.repository.note.*
import com.devsurfer.data.repository.note.dataSource.*
import com.devsurfer.data.repository.userData.UserDataRepositoryImpl
import com.devsurfer.data.repository.userData.dataSource.UserDataRemoteDataSource
import com.devsurfer.domain.repository.auth.AuthRepository
import com.devsurfer.domain.repository.note.*
import com.devsurfer.domain.repository.userData.UserDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAuthRepository(dataSource: AuthRemoteDataSource): AuthRepository =
        AuthRepositoryImpl(dataSource = dataSource)

    @Singleton
    @Provides
    fun provideUserDataRepository(dataSource: UserDataRemoteDataSource): UserDataRepository =
        UserDataRepositoryImpl(dataSource = dataSource)

    @Singleton
    @Provides
    fun provideNoteRepository(dao: NoteDao): NoteRepository =
        NoteRepositoryImpl(dao = dao)

    @Singleton
    @Provides
    fun provideNoteContentRepository(dao: NoteContentDao): NoteContentRepository =
        NoteContentRepositoryImpl(dao = dao)

    @Singleton
    @Provides
    fun provideNoteImageRepository(dao: NoteImageDao): NoteImageRepository =
        NoteImageRepositoryImpl(dao = dao)

    @Singleton
    @Provides
    fun provideNoteDrawingBoardRepository(dao: NoteDrawingBoardDao): NoteDrawingBoardRepository =
        NoteDrawingBoardRepositoryImpl(dao = dao)

    @Singleton
    @Provides
    fun provideNoteReferenceLinkRepository(dao: NoteReferenceLinkDao): NoteReferenceLinkRepository =
        NoteReferenceLinkRepositoryImpl(dao = dao)
}