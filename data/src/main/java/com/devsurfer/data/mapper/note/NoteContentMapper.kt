package com.devsurfer.data.mapper.note

import com.devsurfer.data.model.note.NoteContentEntity
import com.devsurfer.domain.model.note.NoteContent

object NoteContentMapper {
    fun mapperToModel(entity: NoteContentEntity): NoteContent =
        NoteContent(
            contentId = entity.content_id,
            content = entity.content,
            createdAt = entity.created_at,
            updatedAt = entity.updated_at,
            repositoryId = entity.repository_id,
            branch = entity.branch,
            branchState = entity.branch_state,
            status = entity.status
        )

    fun mapperToEntity(model: NoteContent): NoteContentEntity =
        NoteContentEntity(
            content_id = model.contentId,
            content = model.content,
            created_at = model.createdAt,
            updated_at = model.updatedAt,
            repository_id = model.repositoryId,
            branch = model.branch,
            branch_state = model.branchState,
            status = model.status
        )
}