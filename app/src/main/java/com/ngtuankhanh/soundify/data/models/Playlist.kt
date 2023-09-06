package com.ngtuankhanh.soundify.data.models

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path


interface SpotifyService {
    @GET("playlists/{playlist_id}")
    fun getPlaylistDetails(@Header("Authorization") authHeader: String, @Path("playlist_id") playlistId: String): Call<Playlist>
}

class Playlist {
    @SerializedName("id")
    var ID: String? = null

    @SerializedName("name")
    var Name: String? = null

    @SerializedName("description")
    var Description: String? = null

    @SerializedName("public")
    var Public: Boolean? = null

    @SerializedName("images")
    var ImagesData: ImageData? = null
    var ImageURL: String? = null
        get() = ImagesData?.URL

    @SerializedName("followers")
    private val FollowersData: FollowerData? = null
    var TotalFollowers: Int? = null
        get() = FollowersData?.Total

    @SerializedName("tracks")
    var tracksData: TrackResponse? = null

    val ListOfTracksID: Array<String>?
        get() = tracksData?.items?.map { it.TrackObject.id }?.toTypedArray()

    companion object {
        private const val BASE_URL = "https://api.spotify.com/v1/"
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        private val service: SpotifyService = retrofit.create(SpotifyService::class.java)

        fun fetchBySpotifyId(accessToken: String, spotifyId: String, callback: (Playlist?) -> Unit) {
            val call = service.getPlaylistDetails("Bearer $accessToken", spotifyId)
            call.enqueue(object : Callback<Playlist> {
                override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                    if (response.isSuccessful) {
                        val playlist = response.body()
                        callback(playlist)
                    } else {
                        callback(null)
                    }
                }

                override fun onFailure(call: Call<Playlist>, t: Throwable) {
                    callback(null)
                }
            })
        }

    }
    /*
        Playlist.fetchBySpotifyId("YOUR_SPOTIFY_ACCESS_TOKEN", "YOUR_SPOTIFY_PLAYLIST_ID") { playlist ->
            if (playlist != null) {
                println("Playlist Name: ${playlist.Name}")
            } else {
                println("Error fetching the playlist.")
            }
        }
     */
}