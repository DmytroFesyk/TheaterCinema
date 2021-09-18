package com.example.theaterkotlinspring.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.nio.file.Path
import java.nio.file.Paths
import javax.servlet.ServletContext

@Configuration
@PropertySource("classpath:custom.properties")
class Configuration : WebMvcConfigurer {



    @Value("\${directory.image.films}")
    lateinit var directory: String

    @Value("\${directory.root}")
    lateinit var rootDirectory: String

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {

        val uploadPath: Path = Paths.get(rootDirectory + directory)
        val path = uploadPath.toFile()
        registry.addResourceHandler("${directory}/**").addResourceLocations("file:${path}/")
    }

}

