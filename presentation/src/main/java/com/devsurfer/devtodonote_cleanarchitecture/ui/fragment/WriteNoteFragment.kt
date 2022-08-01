package com.devsurfer.devtodonote_cleanarchitecture.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.adapter.SelectedImageAdapter
import com.devsurfer.devtodonote_cleanarchitecture.base.BaseFragment
import com.devsurfer.devtodonote_cleanarchitecture.databinding.FragmentWriteNoteBinding
import com.devsurfer.devtodonote_cleanarchitecture.ui.bottomSheet.DrawingBoardBottomSheet
import com.devsurfer.devtodonote_cleanarchitecture.ui.bottomSheet.OpenUserProfileBottomSheet
import com.devsurfer.devtodonote_cleanarchitecture.ui.dialog.AppendLinkDialog
import com.devsurfer.devtodonote_cleanarchitecture.uiEvent.CreateNoteUiEvent
import com.devsurfer.devtodonote_cleanarchitecture.viewModel.WriteNoteViewModel
import com.devsurfer.domain.useCase.note.LinkParseUseCase
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerLauncher
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.registerImagePicker
import com.esafirm.imagepicker.model.Image
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WriteNoteFragment: BaseFragment<FragmentWriteNoteBinding>(R.layout.fragment_write_note) {

    private val viewModel: WriteNoteViewModel by viewModels()
    private val args: WriteNoteFragmentArgs by navArgs()

    private var imagePickerLauncher: ImagePickerLauncher? = null
    private val selectedImageAdapter = SelectedImageAdapter{
        viewModel.onEvent(CreateNoteUiEvent.RemoveImage(it))
    }

    override fun initData() {
        viewModel.initData(args.itemNote)
        viewModel.initRepositoryId(args.itemRepositoryId)
        imagePickerLauncher = registerImagePicker { result ->
            viewModel.onEvent(CreateNoteUiEvent.AddImages(result))
        }
    }

    override fun initUI() {
        with(binding){
            //viewModel data binding
            vm = viewModel
            //action bar
            actionBar.setOnMenuItemClickListener {
                if(it.itemId == R.id.menu_button_done){
                    if(etContent.text.isNullOrBlank()){
                        showShortToast(getString(R.string.toast_error_blank_content))
                    }else{
                        viewModel.onEvent(CreateNoteUiEvent.Submit(etContent.text.toString()))
                    }
                }
                true
            }
            //Resource layout
            rvReferenceImage.adapter = selectedImageAdapter
            //bottom button
            buttonAddImage.setOnClickListener {
                val config = ImagePickerConfig(
                    mode = ImagePickerMode.MULTIPLE,
                    theme = R.style.Theme_DevTodoNoteCleanArchitecture
                )
                imagePickerLauncher?.launch(config)
            }
            buttonAddDrawingBoard.setOnClickListener {
                val bottomSheet = DrawingBoardBottomSheet{

                }
                if(activity != null && isAdded){
                    bottomSheet.show(childFragmentManager, "drawingBoardSheet")
                }
            }

            buttonAddReferenceLink.setOnClickListener {
                val dialog = AppendLinkDialog{

                }
                if(activity != null && isAdded){
                    dialog.show(childFragmentManager ,"appendLinkDialog")
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initListener() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.imageList.observe(viewLifecycleOwner){
                binding.layoutAddedImage.visibility = if(it.isNotEmpty()) View.VISIBLE else View.GONE
                selectedImageAdapter.submitList(it)
                selectedImageAdapter.notifyDataSetChanged()
            }
        }
    }

    companion object{
        private const val TAG = "WriteNoteFragment"
    }
}