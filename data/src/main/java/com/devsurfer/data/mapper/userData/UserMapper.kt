package com.devsurfer.data.mapper.userData

import com.devsurfer.data.model.userData.UserResponse
import com.devsurfer.domain.model.userData.User

fun UserResponse.toModel(): User =
    User(
        id = this.id,
        email = this.email,
        login = this.login,
        publicRepos = this.public_repos,
        totalPrivateRepos = this.total_private_repos,
        ownedPrivateRepos = this.owned_private_repos,
        avatarUrl = this.avatar_url,
        htmlUrl = this.html_url
    )

fun User.toResponse(): UserResponse =
    UserResponse(
        id = this.id,
        email = this.email,
        login = this.login,
        public_repos = this.publicRepos,
        total_private_repos = this.totalPrivateRepos,
        owned_private_repos = this.ownedPrivateRepos,
        avatar_url = this.avatarUrl,
        html_url = this.htmlUrl
    )