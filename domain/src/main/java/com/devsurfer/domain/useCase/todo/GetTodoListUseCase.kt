package com.devsurfer.domain.useCase.todo

import com.devsurfer.domain.enums.TodoState
import com.devsurfer.domain.model.note.Note
import com.devsurfer.domain.repository.note.NoteRepository
import com.devsurfer.domain.state.ResourceState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTodoListUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    operator fun invoke(repositoryId: Int, state: TodoState): Flow<ResourceState<List<Note>>> = flow {
        emit(ResourceState.Loading())
        val result = when(state){
            TodoState.TODO -> repository.getTodoNotesByRepositoryId(repositoryId = repositoryId)
            TodoState.DONE -> repository.getDoneNotesByRepositoryId(repositoryId = repositoryId)
            else -> repository.getNotesByRepositoryId(repositoryId = repositoryId)
        }
        emit(ResourceState.Success(result))
    }
}