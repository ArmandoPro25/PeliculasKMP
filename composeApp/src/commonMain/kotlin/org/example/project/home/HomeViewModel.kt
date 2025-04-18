package org.example.project.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.example.project.data.Movie
import org.example.project.data.MoviesServices
import org.example.project.data.RemoteMovie

class HomeViewModel(
    private val moviesServices: MoviesServices
) : ViewModel() {
    var state by mutableStateOf(UiState())
        private set

    init {
        loadPopularMovies()
    }

    fun loadPopularMovies() {
        viewModelScope.launch {
            try {
                state = state.copy(loading = true, error = null)
                val result = moviesServices.fetchPopularMovies()
                state = UiState(
                    movies = result.results.map { it.toDomainMovie() },
                    loading = false
                )
            } catch (e: Exception) {
                state = state.copy(
                    loading = false,
                    error = "Error al cargar películas: ${e.message}"
                )
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movies: List<Movie> = emptyList(),
        val error: String? = null
    )
}

private fun RemoteMovie.toDomainMovie() = Movie(
    id = id,
    title = title,
    poster = "https://image.tmdb.org/t/p/w500$posterPath"
)