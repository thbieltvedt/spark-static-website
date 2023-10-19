package thb.poc.fonttheater.application

import spark.Request
import spark.Response
import spark.Spark.get
import spark.SparkBase.externalStaticFileLocation
import kotlin.collections.first
import kotlin.collections.isEmpty

public object WebApplication {

    fun helloWorld(): String = "Hei på deg!! WebApplication"

    val hw: (Request, Response) -> String =
            { req: Request, res: Response -> helloWorld() }

    fun init(commandLineArguments: Array<String>) {
        val locationSource: LocationSource =
                if (!commandLineArguments.isEmpty())
                    LocationSource.COMMAND_LINE_ARGUMENT
                else if (System.getProperty("user.dir") != null)
                    LocationSource.WORKING_DIR
                else
                    LocationSource.CLASS_LOADER_DIR

        val location: String = when (locationSource) {
            LocationSource.COMMAND_LINE_ARGUMENT -> commandLineArguments.first()
            LocationSource.WORKING_DIR -> System.getProperty("user.dir")
            LocationSource.CLASS_LOADER_DIR -> javaClass.getClassLoader().getResource("").getPath() // TODO!
        }

        println("Resolved static files location from ${locationSource}.")
        println("Static file location: ${location}")

        externalStaticFileLocation(location)

        get("/hello", hw)

    }

    enum class LocationSource {
        COMMAND_LINE_ARGUMENT,
        WORKING_DIR,
        CLASS_LOADER_DIR
    }
}

public fun main(args: Array<String>) {
    WebApplication.init(args)
}


