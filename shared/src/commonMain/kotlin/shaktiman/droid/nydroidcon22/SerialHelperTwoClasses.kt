//package shaktiman.droid.nydroidcon22
//
//import kotlinx.serialization.Serializable
//import kotlinx.serialization.decodeFromString
//import kotlinx.serialization.encodeToString
//import kotlinx.serialization.json.Json
//import kotlin.js.JsExport
//
//@Serializable
//data class ProjectWithLang(val name: String, val language: Language)
//
//@Serializable
//@JsExport
//data class Language(val name: String, val version: String)
//
//@JsExport
//class SerialHelperTwoClasses {
//    fun getSerializedData(): Project {
//        // Serializing objects
//        val data = ProjectWithLang("kotlinx.serialization", Language("Kotlin", "1.6.10"))
//        val string = Json.encodeToString(data)
//        println(string) // {"name":"kotlinx.serialization","language":"Kotlin"}
//        // Deserializing back into objects
//        val obj = Json.decodeFromString<Project>(string)
//        println(obj) // Project(name=kotlinx.serialization, language=Kotlin)
//        return obj
//    }
//}