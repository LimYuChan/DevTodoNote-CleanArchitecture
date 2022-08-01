package com.devsurfer.data.mapper.userData

import com.devsurfer.data.model.userData.repositoryEvent.RepositoryEventResponse
import com.devsurfer.domain.model.userData.RepositoryEvent

object RepositoryEventMapper {
    fun mapperToModel(response: RepositoryEventResponse): RepositoryEvent =
        RepositoryEvent(
            id = response.id,
            type = response.type,
            payloadRef = response.payload?.ref,
            commitList = response.payload?.commits?.map { RepositoryCommitMapper.mapperToModel(it) },
            merged = response.payload?.pull_request?.merged,
            mergeCommitSha = response.payload?.pull_request?.merge_commit_sha,
            head = RepositoryHeadMapper.mapperToModel(response.payload?.pull_request?.head)
        )
}