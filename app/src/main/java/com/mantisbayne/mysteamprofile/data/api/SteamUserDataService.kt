package com.mantisbayne.mysteamprofile.data.api

import com.mantisbayne.mysteamprofile.BuildConfig
import com.mantisbayne.mysteamprofile.data.model.OwnedGamesResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "http://api.steampowered.com/"

val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface SteamUserDataService {
    @GET("IPlayerService/GetOwnedGames/v0001/")
    suspend fun getOwnedGames(
        @Query("steamid") steamId: String,
        @Query("include_appinfo") includeAppInfo: Boolean = true,
        @Query("include_played_free_games") includePlayedFreeGames: Boolean = false,
        @Query("format") format: String = "json",
        @Query("key") apiKey: String = BuildConfig.STEAM_API_KEY
    ): OwnedGamesResponse
}

object Api {
    val steamUserDataService: SteamUserDataService by lazy {
        retrofit.create(SteamUserDataService::class.java)
    }
}
