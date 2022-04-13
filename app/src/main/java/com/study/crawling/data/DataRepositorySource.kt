package com.study.crawling.data

import com.study.crawling.data.model.ImageCrawling
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource {
    suspend fun requestCrawlingImage(page : Int) : Flow<ArrayList<ImageCrawling>>
}