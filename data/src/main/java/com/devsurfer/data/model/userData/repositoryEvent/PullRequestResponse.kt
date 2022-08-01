package com.devsurfer.data.model.userData.repositoryEvent

data class PullRequestResponse(
    val merged: Boolean? = false,
    val head: HeadResponse? = null,
    val merge_commit_sha: String? = null
)
