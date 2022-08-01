package com.devsurfer.devtodonote_cleanarchitecture.ui.dialog

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.devsurfer.data.state.ResponseErrorState
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.base.BaseDialog
import com.devsurfer.devtodonote_cleanarchitecture.databinding.DialogAppendLinkBinding
import com.devsurfer.devtodonote_cleanarchitecture.viewModel.AppendLinkViewModel
import com.devsurfer.domain.item.LinkParseData
import com.devsurfer.domain.state.ResourceState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AppendLinkDialog(
    val onAppend: (LinkParseData) -> Unit
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
}