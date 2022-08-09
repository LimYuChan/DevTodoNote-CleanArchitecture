package com.devsurfer.domain.useCase.userData

import android.util.Log
import com.devsurfer.domain.enums.BranchState
import com.devsurfer.domain.model.userData.RepositoryEvent
import com.devsurfer.domain.repository.userData.UserDataRepository
import com.devsurfer.domain.state.ResourceState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

/**
 * Update by Yuchan 2022.08.09
 */
class GetBranchEventUseCase @Inject constructor(
    private val repository: UserDataRepository
) {
    operator fun invoke(
        owner: String,
        repo: String,
        branchName: String
    ): Flow<BranchState> = flow {
        val result = withContext(Dispatchers.IO) {
            repository.getUserRepositoryEvents(
                owner,
                repo
            )
        }
        val isMergeResult = withContext(Dispatchers.Default){
            isMerge(result, branchName)
        }
        emit(isMergeResult)
    }

    private suspend fun isMerge(
        list: List<RepositoryEvent>,
        branchName: String
    ): BranchState = coroutineScope{
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

        return@coroutineScope if (branchEvent.isEmpty()) {
            BranchState.NONE
        } else {
           if(branchEvent.count{ it.merged == true} > 0){
               BranchState.MERGE
           }else{
               BranchState.COMMIT
           }
        }
    }
    companion object{
        private const val TAG = "GetBranchEventUseCase"
    }
}