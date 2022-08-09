package com.devsurfer.data.mapper.userData

import com.devsurfer.data.model.userData.repositoryEvent.HeadResponse
import com.devsurfer.domain.model.userData.Head

fun HeadResponse?.toModel(): Head? =
    if(this == null){
        null
    }else{
        Head(
            ref = this.ref,
            sha = this.sha
        )
    }

