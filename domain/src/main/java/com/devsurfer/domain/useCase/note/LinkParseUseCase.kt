package com.devsurfer.domain.useCase.note

import android.util.Log
import com.devsurfer.domain.item.ReferenceLink
import com.devsurfer.domain.state.Failure
import com.devsurfer.domain.state.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import java.io.IOException

/**
 * Update by Yuchan 2022.08.09
 */
class LinkParseUseCase {

    operator fun invoke(link: String): Flow<ResourceState<ReferenceLink>> = flow {
        var linkUrl = link
        val parseData = ReferenceLink()
        if(!linkUrl.startsWith("http")){
            linkUrl = "http://$link"
        }

        val response = Jsoup.connect(linkUrl)
            .ignoreContentType(true)
            .userAgent("Mozilla")
            .referrer("http://www.google.com")
            .timeout(10000)
            .followRedirects(true)
            .execute()
        Log.d(TAG, "invoke: test")
        val document = response.parse()
        val ogTags = document.select("meta[property^=og:]")
        ogTags.forEach{
            when (it.attr("property")) {
                "og:image" -> {
                    parseData.image = (it.attr(OPEN_GRAPH_KEY))
                }
                "og:description" -> {
                    parseData.description = (it.attr(OPEN_GRAPH_KEY))
                }
                "og:url" -> {
                    parseData.url = (it.attr(OPEN_GRAPH_KEY))
                }
                "og:title" -> {
                    parseData.title = (it.attr(OPEN_GRAPH_KEY))
                }
            }
        }
        emit(ResourceState.Success(parseData))
    }
    companion object{
        private const val TAG = "LinkParseUseCase"
        const val OPEN_GRAPH_KEY = "content"
    }
}