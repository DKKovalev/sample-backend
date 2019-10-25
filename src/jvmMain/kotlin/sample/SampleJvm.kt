package sample

import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.*
import io.ktor.response.respond
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
                call.respond(HttpStatusCode.Accepted)
            }
            get("hello") {
                call.respond(HttpStatusCode.Accepted, "Well hello there")
            }
        }
    }.start(wait = true)
}