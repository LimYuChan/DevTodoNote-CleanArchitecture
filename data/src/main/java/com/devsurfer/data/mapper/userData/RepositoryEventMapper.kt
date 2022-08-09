package com.devsurfer.data.mapper.userData

import com.devsurfer.data.model.userData.repositoryEvent.RepositoryEventResponse
import com.devsurfer.domain.model.userData.RepositoryEvent

fun RepositoryEventResponse.toModel(): RepositoryEvent =
    RepositoryEvent(
        id = this.id,
        type = this.type,
        payloadRef = this.payload?.ref,
        commitList = this.payload?.commits?.map { it.toModel() },
        merged = this.payload?.pull_request?.merged,
        mergeCommitSha = this.payload?.pull_request?.merge_commit_sha,
        head = this.payload?.pull_request?.head.toModel()
    )