package com.devsurfer.devtodonote_cleanarchitecture.ui.fragment

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.adapter.DrawingBoardAdapter
import com.devsurfer.devtodonote_cleanarchitecture.adapter.ImageAdapter
import com.devsurfer.devtodonote_cleanarchitecture.adapter.ReferenceLinkAdapter
import com.devsurfer.devtodonote_cleanarchitecture.base.BaseFragment
import com.devsurfer.devtodonote_cleanarchitecture.databinding.FragmentWriteNoteBinding
import com.devsurfer.devtodonote_cleanarchitecture.enums.WriteNoteState
import com.devsurfer.devtodonote_cleanarchitecture.ui.bottomSheet.ActionMoreBottomSheet
import com.devsurfer.devtodonote_cleanarchitecture.ui.bottomSheet.DrawingBoardBottomSheet
import com.devsurfer.devtodonote_cleanarchitecture.ui.dialog.AppendLinkDialog
import com.devsurfer.devtodonote_cleanarchitecture.ui.dialog.SetBranchNameDialog
import com.devsurfer.devtodonote_cleanarchitecture.uiEvent.CreateNoteUiEvent
import com.devsurfer.devtodonote_cleanarchitecture.viewModel.WriteNoteViewModel
import com.devsurfer.domain.state.ResourceState
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerLauncher
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.registerImagePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class WriteNoteFragment : BaseFragment<FragmentWriteNoteBinding>(R.layout.fragment_write_note) {

    private val viewModel: WriteNoteViewModel by viewModels()
    private val args: WriteNoteFragmentArgs by navArgs()

    private var imagePickerLauncher: ImagePickerLauncher? = null

    private val imageAdapter = ImageAdapter {
        if (isAttachInActivity()) {
            val moreBottomSheet = ActionMoreBottomSheet(isOnlyDelete = true, onDelete = {
                viewModel.onEvent(CreateNoteUiEvent.RemoveImage(it))
            })
            moreBottomSheet.show(childFragmentManager, "moreSheet")
        }
    }

    private val drawingBoardAdapter = DrawingBoardAdapter { selectDrawingBoard ->
        if (isAttachInActivity()) {
            val moreBottomSheet = ActionMoreBottomSheet(onDelete = {
                viewModel.onEvent(CreateNoteUiEvent.RemoveDrawingBoard(selectDrawingBoard))
            }, onEdit = {
                val drawingBoardBottomSheet =
                    DrawingBoardBottomSheet(selectDrawingBoard) { newDrawingBoard ->
                        viewModel.onEvent(CreateNoteUiEvent.RemoveDrawingBoard(selectDrawingBoard))
                        viewModel.onEvent(CreateNoteUiEvent.AddDrawingBoard(newDrawingBoard))
                    }
                drawingBoardBottomSheet.show(childFragmentManager, "drawingBoardSheet")
            })
            moreBottomSheet.show(childFragmentManager, "moreSheet")
        }
    }
    private val referenceLinkAdapter = ReferenceLinkAdapter {
        if (isAttachInActivity()) {
            val moreBottomSheet = ActionMoreBottomSheet(
                isOnlyDelete = true,
                onDelete = {
                    viewModel.onEvent(CreateNoteUiEvent.RemoveReferenceLink(it))
                }
            )
            moreBottomSheet.show(childFragmentManager, "moreSheet")
        }
    }

    override fun initData() {
        viewModel.initData(args.itemNote)
        viewModel.initRepositoryId(args.itemRepositoryId)
        imagePickerLauncher = registerImagePicker { result ->
            viewModel.onEvent(CreateNoteUiEvent.AddImages(result))
        }
    }

    override fun initUI() {
        with(binding) {
            //viewModel data binding
            branch = viewModel.getNowBranch()
            //action bar
            actionBar.setOnMenuItemClickListener {
                if (it.itemId == R.id.menu_button_done) {
                    if (etContent.text.isNullOrBlank()) {
                        showShortToast(getString(R.string.toast_error_blank_content))
                    } else {
                        viewModel.onEvent(CreateNoteUiEvent.Submit(etContent.text.toString()))
                    }
                }else if(it.itemId == android.R.id.home){
                    onBackPress()
                }
                true
            }
            //Resource layout
            rvReferenceImage.adapter = imageAdapter
            rvReferenceDrawingBoard.adapter = drawingBoardAdapter
            rvReferenceLink.adapter = referenceLinkAdapter
            //bottom button
            buttonAddImage.setOnClickListener {
                val config = ImagePickerConfig(
                    mode = ImagePickerMode.MULTIPLE,
                    theme = R.style.Theme_DevTodoNoteCleanArchitecture
                )
                imagePickerLauncher?.launch(config)
            }
            buttonAddDrawingBoard.setOnClickListener {
                val bottomSheet = DrawingBoardBottomSheet {
                    viewModel.onEvent(CreateNoteUiEvent.AddDrawingBoard(it))
                }
                if (isAttachInActivity()) {
                    bottomSheet.show(childFragmentManager, "drawingBoardSheet")
                }
            }

            buttonAddReferenceLink.setOnClickListener {
                val dialog = AppendLinkDialog {
                    viewModel.onEvent(CreateNoteUiEvent.AddReferenceLink(it))
                }
                if (isAttachInActivity()) {
                    dialog.show(childFragmentManager, "appendLinkDialog")
                }
            }

            tvBranch.setOnClickListener {
                if(viewModel.getWriterState() == WriteNoteState.Edit){
                    return@setOnClickListener
                }
                val dialog = SetBranchNameDialog(
                    branchName = viewModel.getNowBranch(),
                    onChange = {
                        viewModel.updateBranchName(it)
                    },
                    onReset = {
                        viewModel.updateBranchName()
                    })
                if(isAttachInActivity()){
                    dialog.show(childFragmentManager, "branchDialog")
                }
            }

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initListener() {
        viewModel.content.observe(viewLifecycleOwner){
            binding.branch = it.branch
        }
        viewModel.imageList.observe(viewLifecycleOwner) {
            binding.layoutAddedImage.visibility = if (it.isNotEmpty()) View.VISIBLE else View.GONE
            imageAdapter.submitList(it)
            imageAdapter.notifyDataSetChanged()
        }

        viewModel.drawingBoardList.observe(viewLifecycleOwner) {
            binding.layoutAddedDrawingBoard.visibility =
                if (it.isNotEmpty()) View.VISIBLE else View.GONE
            drawingBoardAdapter.submitList(it)
            drawingBoardAdapter.notifyDataSetChanged()
        }

        viewModel.referenceLinkList.observe(viewLifecycleOwner) {
            binding.layoutAddedReferenceLink.visibility =
                if (it.isNotEmpty()) View.VISIBLE else View.GONE
            referenceLinkAdapter.submitList(it)
            referenceLinkAdapter.notifyDataSetChanged()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.submit.collectLatest {
                when(it){
                    is ResourceState.Success->{
                        binding.layoutLoadingProgress.root.visibility = View.GONE
                        onBackPress()
                    }
                    is ResourceState.Error->{
                        binding.layoutLoadingProgress.root.visibility = View.GONE
                        errorHandler(it.failure)
                    }
                    else->{
                        binding.layoutLoadingProgress.root.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    companion object {
        private const val TAG = "WriteNoteFragment"
    }
}