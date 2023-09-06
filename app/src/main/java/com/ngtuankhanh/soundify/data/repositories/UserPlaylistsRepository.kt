package com.ngtuankhanh.soundify.data.repositories
//import com.ngtuankhanh.soundify.data.models.Playlist
//import retrofit2.Call
//import retrofit2.Response
//import retrofit2.http.GET
//import retrofit2.http.Header
//import retrofit2.http.Path
//import retrofit2.Callback
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//data class PlaylistSummaryResponse(val items: List<PlaylistSummaryItemAPI>)
//
//data class PlaylistSummaryItemAPI(val id: String?)
//
//object SpotifyAPI {
//    private const val BASE_URL = "https://api.spotify.com/v1/"
//
//    private val retrofit: Retrofit = Retrofit.Builder()
//        .baseUrl(BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    val service: SpotifyService = retrofit.create(SpotifyService::class.java)
//}
//
//interface SpotifyService {
//    @GET("users/{user_id}/playlists")
//    fun getUserPlaylists(@Header("Authorization") authHeader: String, @Path("user_id") userId: String): Call<PlaylistSummaryResponse>
//
//    @GET("playlists/{playlist_id}")
//    fun getPlaylistDetails(@Header("Authorization") authHeader: String, @Path("playlist_id") playlistId: String): Call<Playlist>
//}
//
//class UserPlaylistsRepository {
//
//    var storedPlaylistIds: List<String> = listOf()
//
//    fun fetchPlaylistIDs(accessToken: String, userId: String, onPlaylistIdsLoaded: (List<String>) -> Unit, onError: (Throwable) -> Unit) {
//        SpotifyAPI.service.getUserPlaylists("Bearer $accessToken", userId).enqueue(object : Callback<PlaylistSummaryResponse> {
//            override fun onResponse(call: Call<PlaylistSummaryResponse>, response: Response<PlaylistSummaryResponse>) {
//                if (response.isSuccessful) {
//                    val apiItems = response.body()?.items
//                    val playlistIds = apiItems?.mapNotNull { it.id } ?: listOf()
//                    storedPlaylistIds = playlistIds
//                    onPlaylistIdsLoaded(playlistIds)
//                } else {
//                    onError(Throwable("API call unsuccessful. Status code: ${response.code()}"))
//                }
//            }
//
//            override fun onFailure(call: Call<PlaylistSummaryResponse>, t: Throwable) {
//                onError(t)
//            }
//        })
//    }
//}
//
///*
//
//val repository = UserPlaylistsRepository()
//
//repository.fetchPlaylistIDs("YOUR_SPOTIFY_ACCESS_TOKEN", "TARGET_SPOTIFY_USER_ID",
//    onPlaylistIdsLoaded = { playlistIds ->
//        // Sử dụng `playlistIds` ngay trong callback
//        playlistIds.forEach { id ->
//            println("Fetched Playlist ID: $id")
//        }
//
//        // Vì storedPlaylistIds đã được cập nhật, bạn cũng có thể sử dụng nó ở đây
//        if (repository.storedPlaylistIds.isNotEmpty()) {
//            println("First stored Playlist ID: ${repository.storedPlaylistIds[0]}")
//        }
//    },
//    onError = { error ->
//        println("Error fetching playlist IDs: ${error.message}")
//    }
//)
//
//// Hoặc sử dụng `storedPlaylistIds` sau khi chắc chắn rằng dữ liệu đã được tải
//// (ví dụ: trong một hàm hoặc sự kiện khác của app)
//fun someOtherFunctionInYourApp() {
//    if (repository.storedPlaylistIds.isNotEmpty()) {
//        println("Using stored Playlist ID elsewhere in the app: ${repository.storedPlaylistIds[0]}")
//    }
//}
//
//
//*/