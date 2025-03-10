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
    val has_community_visible_stats: Boolean = false
) {
    fun getIconUrl(hash: String): String? {
        return img_icon_url?.let { "http://media.steampowered.com/steamcommunity/public/images/apps/$appid/$hash.jpg" }
    }

    fun getLogoUrl(hash: String): String? {
        return img_logo_url?.let { "http://media.steampowered.com/steamcommunity/public/images/apps/$appid/$hash.jpg" }
    }

    fun getStatsUrl(steamId: String): String {
        return "http://steamcommunity.com/profiles/$steamId/stats/$appid"
    }
}