package com.devsurfer.domain.useCase.userData

import android.util.Log
import com.devsurfer.domain.enums.BranchState
import com.devsurfer.domain.model.userData.RepositoryEvent
import com.devsurfer.domain.repository.userData.UserDataRepository
import com.devsurfer.domain.state.ResourceState
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject

class GetBranchEventUseCase @Inject constructor(
    private val repository: UserDataRepository
) {
    operator fun invoke(
        owner: String,
        repo: String,
        branchName: String
    ): ResourceState<BranchState> = runBlocking(Dispatchers.IO) {
        val result = withContext(Dispatchers.Default) {
            repository.getUserRepositoryEvents(
                owner,
                repo
            )
        }
        when (result) {
            is ResourceState.Success -> {
                return@runBlocking isMerge(result.data, branchName)
            }
            is ResourceState.Error -> {
                return@runBlocking ResourceState.Error(failure = result.failure)
            }
            else -> {
                return@runBlocking ResourceState.Loading()
            }
        }
    }

    private fun isMerge(
        list: List<RepositoryEvent>,
        branchName: String
    ): ResourceState<BranchState> = runBlocking{
        val branchList = arrayListOf<RepositoryEvent>()
        val mapperList = list
            .map { it.copy(payloadRef = it.payloadRef?.replace("refs/heads/", "")) }

        mapperList
            .forEach{
            val ref = it.payloadRef ?: it.head?.ref
            if(ref != null){
                branchList.add(it)
            }
        }
        val branchEvent =
            branchList
                .filter { it.payloadRef.equals(branchName, ignoreCase = true) || it.head?.ref?.equals(branchName, ignoreCase = true) == true }

        return@runBlocking if (branchEvent.isEmpty()) {
            ResourceState.Success(BranchState.NONE)
        } else {
           if(branchEvent.count{ it.merged == true} > 0){
               ResourceState.Success(BranchState.MERGE)
           }else{
               ResourceState.Success(BranchState.COMMIT)
           }
        }
    }
    companion object{
        private const val TAG = "GetBranchEventUseCase"
    }
}