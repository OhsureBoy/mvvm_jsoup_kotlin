package com.study.crawling.data.remote

import android.util.Log
import com.study.crawling.data.model.ImageCrawling
import com.study.crawling.util.CRAWLING_URL
import com.study.crawling.util.USER_AGENT
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.io.IOException
import java.net.MalformedURLException
import javax.inject.Inject

class RemoteData @Inject() constructor() : RemoteDataSource{
    private val imageCrawling : ArrayList<ImageCrawling> = ArrayList()

    // Jsoup 이용한 크롤링, ArrayList 를 return 한다.
    // Error 가 나면 마지막에 Error 를 1로 변경한다
    override suspend fun requestCrawlingImage(page: Int): ArrayList<ImageCrawling> {
        val tag = "RemoteData-> requestCrawlingImage "

        //inappropriate blocking method call coroutine Waring 수정
        val response = {
            val url = CRAWLING_URL + page
            try {
                val doc = Jsoup.connect(url).userAgent(USER_AGENT).get()
                val elements: Elements =
                    doc.select("div.GalleryItems-module__searchContent___DbMmK div article a figure picture img")

                run elemLoop@{
                    elements.forEachIndexed { _, elem ->
                        val poster = elem.absUrl("src")
                        imageCrawling.add(ImageCrawling(poster))
                    }
                }
            } catch (e: IOException) {
                Log.e(tag, "IOException: $e")
                imageCrawling.last().Error = 1
            }
            imageCrawling
        }
        return response.invoke()
    }
}