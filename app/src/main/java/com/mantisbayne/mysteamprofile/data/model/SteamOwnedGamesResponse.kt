package com.mantisbayne.mysteamprofile.data.model

data class OwnedGamesResponse(
    val response: OwnedGamesData? = null
)

data class OwnedGamesData(
    val game_count: Int = 0,
    val games: List<Game>? = emptyList()
)

data class Game(
    val appid: Int = 0,
    val name: String = "",
    val playtime_2weeks: Int = 0,
    val playtime_forever: Int = 0,
    val img_icon_url: String? = null,
    val img_logo_url: String? = null,
    val has_community_visible_stats: Boolean = false,
    val rtime_last_played: Long = 0L
) {
    fun getIconUrl(): String? {
        return img_icon_url?.let { "https://media.steampowered.com/steamcommunity/public/images/apps/$appid/$it.jpg" }
    }

    fun getLogoUrl(): String? {
        return img_logo_url?.let { "https://media.steampowered.com/steamcommunity/public/images/apps/$appid/$it.jpg" }
    }

    fun getStatsUrl(steamId: String): String {
        return "https://steamcommunity.com/profiles/$steamId/stats/$appid"
    }
}