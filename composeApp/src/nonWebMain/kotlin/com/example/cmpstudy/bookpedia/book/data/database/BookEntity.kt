package com.example.cmpstudy.bookpedia.book.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.cmpstudy.bookpedia.book.data.model.BookDto

@Entity(tableName = "BookEntity")
@TypeConverters(StringListTypeConverter::class)
data class BookEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val title: String,
    val description: String?,
    val imageUrl: String,
    val languages: List<String>,
    val authors: List<String>,
    val firstPublishYear: String?,
    val ratingsAverage: Double?,
    val ratingsCount: Int?,
    val numPagesMedian: Int?,
    val numEditions: Int
)

fun BookDto.toEntity() = BookEntity(
    id = id,
    title = title,
    description = description,
    imageUrl = imageUrl,
    languages = languages,
    authors = authors,
    firstPublishYear = firstPublishYear,
    ratingsAverage = ratingsAverage,
    ratingsCount = ratingsCount,
    numPagesMedian = numPagesMedian,
    numEditions = numEditions
)

fun BookEntity.toModel() = BookDto(
    id = id,
    title = title,
    description = description,
    imageUrl = imageUrl,
    languages = languages,
    authors = authors,
    firstPublishYear = firstPublishYear,
    ratingsAverage = ratingsAverage,
    ratingsCount = ratingsCount,
    numPagesMedian = numPagesMedian,
    numEditions = numEditions
)
