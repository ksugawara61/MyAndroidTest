package net.listadoko.mytodomvp.http

//import com.google.common.annotations.VisibleForTesting
import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("id") val id: Long,
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("url") val url: String
) {
//    constructor() {}
//
//    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
//    constructor(id: Long, login: String, avatarUrl: String, url: String) {
//        this.id = id
//        this.login = login
//        this.avatarUrl = avatarUrl
//        this.url = url
//    }

    override fun toString(): String {
        return "Owner{" +
                "id=" + id +
                ", login='" + login + '\''.toString() +
                ", avatarUrl='" + avatarUrl + '\''.toString() +
                ", url='" + url + '\''.toString() +
                '}'.toString()
    }
}
