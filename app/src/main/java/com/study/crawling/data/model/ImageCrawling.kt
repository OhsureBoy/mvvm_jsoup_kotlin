package com.study.crawling.data.model

//여러가지 크롤링 데이터를 가져온다고 가정
//지금 필요한건 ImageURL, Error
//Error 0 정상 , 1 페이지 불러오기 실패
data class ImageCrawling(
    val ImageUrl: String = "",
    var Error: Int = 0
)