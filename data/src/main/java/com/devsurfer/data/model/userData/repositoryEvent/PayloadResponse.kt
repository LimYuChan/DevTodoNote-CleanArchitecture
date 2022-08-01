package com.devsurfer.data.model.userData.repositoryEvent

data class PayloadResponse(
    val ref: String? = null,
    val commits: List<CommitResponse>? = emptyList(),
    val pull_request: PullRequestResponse? = null
)
