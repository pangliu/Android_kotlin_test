package infinitas.com.indochat.kotlindemo.data

data class UserData (val userName:String, val password:String) {
    override fun toString(): String {
        return "$userName - $password"
    }
}

data class VersionInfo(
    var version: String = "",
    var msg: String = "",
    var lang: String = "",
    var status: Int
)

data class StationInfo (
    var id: Int,
    var name: String,
    var lockNum: Int,
    var lockMax: Int,
    var lat: String,
    var lon: String)

data class StationArray(
    var datas: ArrayList<StationInfo>,
    var status: Int,
    var msg: String = "",
    var lang: String = "")