package com.example.cmpstudy.bookpedia.book.data.database

// NOTE: Room Database is not supported on Web platform
actual class DatabaseFactory {
    actual fun create(): androidx.room.RoomDatabase.Builder<FavoriteBookDatabase> {
        error("Room Database is not supported on Web platform. Consider using in-memory storage or IndexedDB instead.")
    }
}
