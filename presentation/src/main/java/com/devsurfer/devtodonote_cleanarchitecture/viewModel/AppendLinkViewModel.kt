package com.devsurfer.devtodonote_cleanarchitecture.viewModel

import androidx.lifecycle.ViewModel
import com.devsurfer.devtodonote_cleanarchitecture.base.BaseViewModel
import com.devsurfer.domain.item.ReferenceLink
import com.devsurfer.domain.state.ResourceState
import com.devsurfer.domain.useCase.note.LinkParseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppendLinkViewModel @Inject constructor(
    private val useCase: LinkParseUseCase
): BaseViewModel(){

    private val _linkParseState = Channel<ResourceState<ReferenceLink>>()
    val linkParseState = _linkParseState.receiveAsFlow()

    fun parseLink(link: String){
        modelScope.launch {
            useCase.invoke(link).onStart {
                _linkParseState.send(ResourceState.Loading())
            }.onEach {
                _linkParseState.send(it)
            }.launchIn(modelScope)
        }
    }
}
