package com.devsurfer.devtodonote_cleanarchitecture.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devsurfer.domain.item.LinkParseData
import com.devsurfer.domain.state.ResourceState
import com.devsurfer.domain.useCase.note.LinkParseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppendLinkViewModel @Inject constructor(
    private val useCase: LinkParseUseCase
): ViewModel(){

    private val _linkParseState = Channel<ResourceState<LinkParseData>>()
    val linkParseState = _linkParseState.receiveAsFlow()

    fun parseLink(link: String){
        CoroutineScope(Dispatchers.IO).launch {
            _linkParseState.send(ResourceState.Loading())
            _linkParseState.send(useCase(link = link))
        }
    }
}
