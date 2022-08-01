package com.devsurfer.data.model.userData.repositoryEvent

data class RepositoryEventResponse(
    val id: Long,
    val type: String,
    val payload: PayloadResponse? = null
)