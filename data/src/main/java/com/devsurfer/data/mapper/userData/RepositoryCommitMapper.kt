package com.devsurfer.data.mapper.userData

import com.devsurfer.data.model.userData.repositoryEvent.CommitResponse
import com.devsurfer.domain.model.userData.Commit

object RepositoryCommitMapper {

    fun mapperToModel(response: CommitResponse): Commit =
        Commit(
            sha = response.sha,
            url = response.url
        )

}