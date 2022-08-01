package com.devsurfer.devtodonote_cleanarchitecture.ui.fragment

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.adapter.DrawingBoardAdapter
import com.devsurfer.devtodonote_cleanarchitecture.adapter.ImageAdapter
import com.devsurfer.devtodonote_cleanarchitecture.adapter.ReferenceLinkAdapter
import com.devsurfer.devtodonote_cleanarchitecture.base.BaseFragment
import com.devsurfer.devtodonote_cleanarchitecture.databinding.FragmentTodoNoteViewerBinding
import com.devsurfer.devtodonote_cleanarchitecture.ui.bottomSheet.ActionMoreBottomSheet
import com.devsurfer.devtodonote_cleanarchitecture.ui.bottomSheet.DrawingBoardBottomSheet
import com.devsurfer.devtodonote_cleanarchitecture.ui.bottomSheet.ImageViewerBottomSheet
import com.devsurfer.devtodonote_cleanarchitecture.uiEvent.CreateNoteUiEvent
import com.devsurfer.devtodonote_cleanarchitecture.util.Utils
import com.devsurfer.devtodonote_cleanarchitecture.viewModel.TodoNoteViewerViewModel
import com.devsurfer.domain.item.DrawingBoard
import com.devsurfer.domain.item.ReferenceLink
import com.devsurfer.domain.state.ResourceState
import com.esafirm.imagepicker.model.Image
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class TodoNoteViewerFragment :
    BaseFragment<FragmentTodoNoteViewerBinding>(R.layout.fragment_todo_note_viewer) {

    private val viewModel: TodoNoteViewerViewModel by viewModels()
    private val args: TodoNoteViewerFragmentArgs by navArgs()

    private val imageAdapter = ImageAdapter(onViewItem = { position ->
        val viewerSheet = ImageViewerBottomSheet(viewModel.note.value?.imageList?.map { it.fileUrl } ?: emptyList(), viewIndex = position)
        if(isAttachInActivity()){
            viewerSheet.show(childFragmentManager, "viewerSheet")
        }
    })

    private val drawingBoardAdapter = DrawingBoardAdapter(onViewItemClick = { position ->
        val viewerSheet = ImageViewerBottomSheet(viewModel.note.value?.drawingBoardList?.map { it.fileImageUrl } ?: emptyList(), viewIndex = position, isCacheFile = true)
        if(isAttachInActivity()){
            viewerSheet.show(childFragmentManager, "viewerSheet")
        }
    })

    private val referenceLinkAdapter = ReferenceLinkAdapter {
        if(isAttachInActivity()){
            Utils.openProfileBottomSheet(childFragmentManager, it.url)
        }
    }

    override fun initData() {
        viewModel.initData(args.itemNote)
        viewModel.updateBranchState(args.itemRepositoryName)
    }

    override fun initUI() {
        with(binding) {
            repositoryName = args.itemRepositoryName
            actionBar.setNavigationOnClickListener {
                onBackPress()
            }
            actionBar.setOnMenuItemClickListener {
                if (it.itemId == R.id.menu_button_more) {
                    val bottomSheet = ActionMoreBottomSheet(
                        onDelete = {
                                   viewModel.deleteNote()
                        },
                        onEdit = {
                            val action =
                                TodoNoteViewerFragmentDirections.actionTodoNoteViewerFragmentToWriteNoteFragment(
                                    itemRepositoryId = args.itemNote.content.repositoryId,
                                    itemNote = viewModel.note.value
                                )
                            view?.let { view ->
                                Navigation.findNavController(view).navigate(action)
                            }
                        }
                    )
                    if(isAttachInActivity()){
                        bottomSheet.show(childFragmentManager, "moreSheet")
                    }
                }
                true
            }
            rvReferenceImage.adapter = imageAdapter
            rvReferenceDrawingBoard.adapter = drawingBoardAdapter
            rvReferenceLink.adapter = referenceLinkAdapter

            swipeRefreshLayout.setOnRefreshListener {
                viewModel.getNoteData()
            }
        }
    }

    override fun initListener() {
        viewModel.note.observe(viewLifecycleOwner) { observeNote ->
            with(binding) {

                note = observeNote
                layoutAddedImage.visibility =
                    if (observeNote.imageList.isNotEmpty()) View.VISIBLE else View.GONE
                imageAdapter.submitList(observeNote.imageList.map {
                    Image(
                        id = it.fileId,
                        name = it.fileName,
                        path = it.fileUrl
                    )
                })

                layoutAddedDrawingBoard.visibility =
                    if (observeNote.drawingBoardList.isNotEmpty()) View.VISIBLE else View.GONE
                drawingBoardAdapter.submitList(observeNote.drawingBoardList.map {
                    DrawingBoard(
                        fileImageUrl = it.fileImageUrl,
                        fileJsonString = it.fileJsonString
                    )
                })

                layoutAddedReferenceLink.visibility =
                    if (observeNote.referenceLinkList.isNotEmpty()) View.VISIBLE else View.GONE
                referenceLinkAdapter.submitList(observeNote.referenceLinkList.map {
                    ReferenceLink(
                        title = it.title,
                        description = it.description,
                        url = it.link,
                        image = it.image
                    )
                })

                swipeRefreshLayout.isRefreshing = false
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.deleteState.collectLatest {
                when(it){
                    is ResourceState.Success->{
                        binding.layoutLoadingProgress.root.visibility = View.GONE
                        showShortToast(getString(R.string.toast_complete_delete_note))
                        onBackPress()
                    }
                    is ResourceState.Error->{
                        binding.layoutLoadingProgress.root.visibility = View.GONE
                        errorHandler(it.failure)
                    }
                    is ResourceState.Loading->{
                        binding.layoutLoadingProgress.root.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}