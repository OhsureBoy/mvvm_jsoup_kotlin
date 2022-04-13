package com.study.crawling.data.remote

import com.study.crawling.data.model.ImageCrawling

internal interface RemoteDataSource {
    suspend fun requestCrawlingImage(page : Int) : ArrayList<ImageCrawling>
}