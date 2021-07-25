package com.devbuild.api.data.infections

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.routing

fun Route.infectionRouting() {
    get("/infections") {
        val infection = InfectionExternalApiClient.getInfection()
        call.respond(HttpStatusCode.OK, infection)
    }
}

fun Application.registerInfectionRoutes() {
    routing {
        infectionRouting()
    }
}