package com.devsurfer.data.mapper.userData

import com.devsurfer.data.model.userData.repositoryEvent.CommitResponse
import com.devsurfer.domain.model.userData.Commit

fun CommitResponse.toModel(): Commit =
    Commit(
        sha = this.sha,
        url = this.url
    )