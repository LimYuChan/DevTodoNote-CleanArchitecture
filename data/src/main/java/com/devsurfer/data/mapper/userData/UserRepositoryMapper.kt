package com.devsurfer.data.mapper.userData

import com.devsurfer.data.model.userData.UserRepositoryResponse
import com.devsurfer.domain.model.userData.UserRepository

object UserRepositoryMapper {

    fun mapperToModel(response: UserRepositoryResponse): UserRepository =
        UserRepository(
            id = response.id,
            name = response.name,
            private = response.private,
            htmlUrl = response.html_url,
            description = response.description,
            createdAt = response.created_at,
            updatedAt = response.updated_at,
            language = response.language,
            defaultBranch = response.default_branch
        )

    fun mapperToResponse(userRepository: UserRepository): UserRepositoryResponse =
        UserRepositoryResponse(
            id = userRepository.id,
            name = userRepository.name,
            private = userRepository.private,
            html_url = userRepository.htmlUrl,
            description = userRepository.description,
            created_at = userRepository.createdAt,
            updated_at = userRepository.updatedAt,
            language = userRepository.language,
            default_branch = userRepository.defaultBranch
        )
}