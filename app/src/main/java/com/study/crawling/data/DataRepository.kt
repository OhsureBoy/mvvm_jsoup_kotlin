package com.study.crawling.data

import com.study.crawling.data.model.ImageCrawling
import com.study.crawling.data.remote.RemoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataRepository @Inject constructor(private val remoteRepository: RemoteData, private val ioDispatcher: CoroutineContext) : DataRepositorySource{
    override suspend fun requestCrawlingImage(page: Int): Flow<ArrayList<ImageCrawling>> {
        return flow {
            emit(remoteRepository.requestCrawlingImage(page))
        }.flowOn(ioDispatcher)
    }
}