package com.devsurfer.data.mapper.userData

import com.devsurfer.data.model.userData.repositoryEvent.HeadResponse
import com.devsurfer.domain.model.userData.Head
object RepositoryHeadMapper {
    fun mapperToModel(response: HeadResponse?): Head? =
        if(response == null){
            null
        }else{
            Head(
                ref = response.ref,
                sha = response.sha
            )
        }
}

