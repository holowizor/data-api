package com.devbuild.api.data

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.routing

fun Route.versionRouting() {
    get("/") {
        call.respondText("data-api")
    }
}

fun Application.registerVersionRoutes() {
    routing {
        versionRouting()
    }
}