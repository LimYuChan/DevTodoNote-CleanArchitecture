package com.devsurfer.data.mapper.userData

import com.devsurfer.data.model.userData.UserRepositoryResponse
import com.devsurfer.domain.model.userData.UserRepository

fun UserRepositoryResponse.toModel(): UserRepository =
    UserRepository(
        id = this.id,
        name = this.name,
        private = this.private,
        htmlUrl = this.html_url,
        description = this.description,
        createdAt = this.created_at,
        updatedAt = this.updated_at,
        language = this.language,
        defaultBranch = this.default_branch
    )

fun UserRepository.toResponse(): UserRepositoryResponse =
    UserRepositoryResponse(
        id = this.id,
        name = this.name,
        private = this.private,
        html_url = this.htmlUrl,
        description = this.description,
        created_at = this.createdAt,
        updated_at = this.updatedAt,
        language = this.language,
        default_branch = this.defaultBranch
    )