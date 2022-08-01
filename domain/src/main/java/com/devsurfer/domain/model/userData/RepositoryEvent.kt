package com.devsurfer.domain.model.userData

data class RepositoryEvent(
    val id: Long,
    val type: String,
    val payloadRef: String? = null,
    val commitList: List<Commit>? = emptyList(),
    val merged: Boolean? = false,
    val mergeCommitSha: String? = null,
    val head: Head? = null
)
