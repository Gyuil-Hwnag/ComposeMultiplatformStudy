package com.example.cmpstudy.bookpedia.book.data.datasource

import com.example.cmpstudy.bookpedia.book.data.BASE_URL
import com.example.cmpstudy.bookpedia.book.data.model.BookWorkDto
import com.example.cmpstudy.bookpedia.book.data.model.SearchResponseDto
import com.example.cmpstudy.core.data.safeCall
import com.example.cmpstudy.core.domain.DataError
import com.example.cmpstudy.core.domain.Result
import io.ktor.client.*
import io.ktor.client.request.*

class RemoteBookDataSourceImpl(
    private val httpClient: HttpClient
) : RemoteBookDataSource {

    override suspend fun searchBooks(
        query: String,
        resultLimit: Int?
    ): Result<SearchResponseDto, DataError.Remote> {
        return safeCall<SearchResponseDto> {
            httpClient.get(urlString = "${BASE_URL}/search.json") {
                parameter("q", query)
                parameter("limit", resultLimit)
                parameter("language", "eng")
                parameter(
                    "fields",
                    "key,title,author_name,author_key,cover_edition_key,cover_i,ratings_average,ratings_count,first_publish_year,language,number_of_pages_median,edition_count"
                )
            }
        }
    }

    override suspend fun getBookDetails(bookWorkId: String): Result<BookWorkDto, DataError.Remote> {
        return safeCall<BookWorkDto> {
            httpClient.get(urlString = "$BASE_URL/works/$bookWorkId.json")
        }
    }
}
