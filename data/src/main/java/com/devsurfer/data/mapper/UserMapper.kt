package com.devsurfer.data.mapper

import com.devsurfer.data.model.UserResponse
import com.devsurfer.domain.model.User

object UserMapper {
    fun mapperToUser(response: UserResponse): User =
        User(
            id = response.id,
            email = response.email,
            login = response.login,
            publicRepos = response.public_repos,
            totalPrivateRepos = response.total_private_repos,
            ownedPrivateRepos = response.owned_private_repos,
            avatarUrl = response.avatar_url,
            htmlUrl = response.html_url
        )

    fun mapperToUserResponse(user: User): UserResponse =
        UserResponse(
            id = user.id,
            email = user.email,
            login = user.login,
            public_repos = user.publicRepos,
            total_private_repos = user.totalPrivateRepos,
            owned_private_repos = user.ownedPrivateRepos,
            avatar_url = user.avatarUrl,
            html_url = user.htmlUrl
        )
}