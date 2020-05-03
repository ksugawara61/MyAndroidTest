package net.listadoko.mytodomvp.http

import com.google.gson.annotations.SerializedName


data class Repo(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("html_url") val htmlUrl: String? = null,
    @SerializedName("private") val isPrivate: Boolean,
    @SerializedName("owner") val owner: Owner
) {
//    constructor() {}
//
//    constructor(id: Long, name: String, fullName: String, isPrivate: Boolean, owner: Owner) {
//        this.id = id
//        this.name = name
//        this.fullName = fullName
//        this.isPrivate = isPrivate
//        this.owner = owner
//    }

    override fun toString(): String {
        return "Repo{" +
                "id=" + id +
                ", name='" + name + '\''.toString() +
                ", fullName='" + fullName + '\''.toString() +
                ", htmlUrl='" + htmlUrl + '\''.toString() +
                ", isPrivate=" + isPrivate +
                ", owner=" + owner +
                '}'.toString()
    }
}