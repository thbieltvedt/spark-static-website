package thb.poc.fonttheater.application

import spark.Request
import spark.Response
import spark.Spark
import spark.Spark.get
import spark.Spark.*
import spark.SparkBase.*

public object WebApplication {

    fun helloWorld(): String = "Hei på deg!! WebApplication"

    val hw: (Request, Response) -> String =
            { req: Request, res: Response -> helloWorld() }

    fun init() {
        println("Initializing Spark WebApplication KOTLIN!")

        //staticFileLocation("/webapp")
        externalStaticFileLocation("/Users/torhaavard/Dev/GitHub/fonttheater-angularjs")

        get("/hello", hw)

    }

}

public fun main(args: Array<String>) {
    WebApplication.init()
}


