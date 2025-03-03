package com.mantisbayne.mysteamprofile.data.model

data class OwnedGamesResponse(
    val game_count: Int,
    val games: List<Game>?
)

data class Game(
    val appid: Int,
    val name: String,
    val playtime_2weeks: Int,
    val playtime_forever: Int,
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