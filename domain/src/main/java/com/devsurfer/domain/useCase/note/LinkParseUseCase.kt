package com.devsurfer.domain.useCase.note

import com.devsurfer.domain.item.ReferenceLink
import com.devsurfer.domain.state.Failure
import com.devsurfer.domain.state.ResourceState
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import java.io.IOException

class LinkParseUseCase {

    operator fun invoke(link: String): ResourceState<ReferenceLink> = runBlocking {
        try{
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
            return@runBlocking ResourceState.Success(parseData)
        }catch (e: IOException){
            return@runBlocking ResourceState.Error(failure = Failure.UnHandleError("호스트 \"$link\"에 연결된 주소가 없습니다."))
        }
    }
    companion object{
        private const val TAG = "LinkParseUseCase"
        const val OPEN_GRAPH_KEY = "content"
    }
}