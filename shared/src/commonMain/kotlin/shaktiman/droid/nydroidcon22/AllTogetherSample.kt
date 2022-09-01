package shaktiman.droid.nydroidcon22

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@Serializable
@JsExport
class AstrosApiResponse(val number: Int, val people: Array<People>, val message: String)

@Serializable
@JsExport
class People(val name: String, val craft: String)

@JsExport
class Astros(val datetime: String, val response: AstrosApiResponse)

@JsExport
class AstrosApiAll {

    private val client = HttpClient()

    @DelicateCoroutinesApi
    fun getAstros(callback: (Astros) -> Unit) {
        GlobalScope.launch(ApplicationDispatcher) {
            val result: String = client.get {
                url("http://api.open-notify.org/astros.json")
            }.bodyAsText()

            val response = Json.decodeFromString<AstrosApiResponse>(result)
            callback(Astros(Clock.System.now().toString(), response))
        }
    }
}