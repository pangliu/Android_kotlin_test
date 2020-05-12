package infinitas.com.indochat.kotlindemo.data

import com.google.gson.annotations.SerializedName

data class UserData (val userName:String, val password:String) {
    override fun toString(): String {
        return "$userName - $password"
    }
}

open class BaseInfo {
    var msg: String? = ""
    var lang: String? = ""
    var status: Int? = 0
}

data class VersionInfo(
    @SerializedName("version")
    val version: String
): BaseInfo()


data class StationArray(
    @SerializedName("datas")
    val datas: List<StationInfo>
): BaseInfo() {
    data class StationInfo(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("lock_num")
        val lock_num: String,
        @SerializedName("lock_max")
        val lock_max: String,
        @SerializedName("lat")
        val lat: String,
        @SerializedName("lon")
        val lon: String
    )
}
