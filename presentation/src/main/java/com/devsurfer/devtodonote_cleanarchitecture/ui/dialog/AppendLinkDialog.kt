package com.devsurfer.devtodonote_cleanarchitecture.ui.dialog

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.base.BaseDialog
import com.devsurfer.devtodonote_cleanarchitecture.databinding.DialogAppendLinkBinding
import com.devsurfer.devtodonote_cleanarchitecture.viewModel.AppendLinkViewModel
import com.devsurfer.domain.item.ReferenceLink
import com.devsurfer.domain.state.ResourceState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AppendLinkDialog(
    val onAppend: (ReferenceLink) -> Unit
) : BaseDialog<DialogAppendLinkBinding>(R.layout.dialog_append_link) {

    private val viewModel: AppendLinkViewModel by viewModels()

    override fun initData() {

    }

    override fun initUI() {
        with(binding) {
            dialog = this@AppendLinkDialog
            buttonDone.setOnClickListener {
                if (etAppendLink.text.isNullOrBlank()) {
                    showShortToast(getString(R.string.toast_error_blank_content))
                } else {
                    viewModel.parseLink(etAppendLink.text.toString())
                }
            }
        }
    }

    override fun initListener() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.linkParseState.collectLatest {
                when (it) {
                    is ResourceState.Success -> {
                        Log.d(TAG, "initListener: ${it.data}")
                        binding.layoutLoadingProgress.root.visibility = View.GONE
                        onAppend(it.data)
                        dismiss()
                    }
                    is ResourceState.Error -> {
                        binding.layoutLoadingProgress.root.visibility = View.GONE
                        showShortToast(it.failure.message)
                    }
                    else -> {
                        binding.layoutLoadingProgress.root.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    companion object{
        private const val TAG = "AppendLinkDialog"
    }
}