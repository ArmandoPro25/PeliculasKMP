package org.example.project.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MoviesServices(
    private val apiKey: String,
    private val client: HttpClient
) {
    suspend fun fetchPopularMovies(): RemoteResult {
        return client
            .get("https://api.themoviedb.org/3/discover/movie?api_key=$apiKey&sort_by=popularity.desc")
            .body()
    }

    suspend fun fetchMovieById(id: Int): RemoteMovie {
        return client
            .get("https://api.themoviedb.org/3/movie/$id?api_key=$apiKey")
            .body()
    }
}