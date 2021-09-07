package com.example.theaterkotlinspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
class TheaterKotlinSpringApplication

fun main(args: Array<String>) {
    runApplication<TheaterKotlinSpringApplication>(*args)
}
