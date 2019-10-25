package sample

import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.content.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.html.*
import java.io.*

actual class Sample {
    actual fun checkMe() = 42
}

actual object Platform {
    actual val name: String = "JVM"
}

fun main() {
    val port = System.getenv("PORT")?.toInt() ?: 8080
    embeddedServer(Netty, port = port) {
        routing {
            get("/") {
                call.respondHtml {
                    head {
                        title("Hello from Ktor!")
                    }
                    body {
                        +"${hello()} from Ktor. Check me value: ${Sample().checkMe()}"
                        div {
                            id = "js-response"
                            +"Loading..."
                        }
                        script(src = "/static/sample-backend.js") {}
                    }
                }
            }
            static("/static") {
                resource("sample-backend.js")
            }
        }
    }.start(wait = true)
}