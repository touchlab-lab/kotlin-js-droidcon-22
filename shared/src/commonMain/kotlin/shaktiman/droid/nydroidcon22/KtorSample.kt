package shaktiman.droid.nydroidcon22

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.native.concurrent.SharedImmutable

@JsExport
class AstrosApi {

    private val client = HttpClient()

    @DelicateCoroutinesApi
    fun getAstros(callback: (String) -> Unit) {
        GlobalScope.launch(ApplicationDispatcher) {
            val result: String = client.get {
                url("http://api.open-notify.org/astros.json")
            }.bodyAsText()

            callback(result)
        }
    }
}