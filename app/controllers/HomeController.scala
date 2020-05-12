package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import java.io.{BufferedInputStream, BufferedOutputStream, File, FileOutputStream}
import java.util.Properties

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.s3.model.{GetObjectRequest, PutObjectRequest}
import com.amazonaws.services.s3.{AmazonS3Client, AmazonS3ClientBuilder}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

   def copyFileFromS3ToLocal(S3destinationPath: String, FileObject: File): Unit = {

    /** ******** Use below code for running from EMR  - ***/
    val bucketName = "tivo-session-store"
    var ENDPOINT = "s3.us-east-1.amazonaws.com"
    var REGION = "us-east-1"
    val amazonS3Client = AmazonS3ClientBuilder.standard()
      .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT, REGION)).build()

    amazonS3Client.getObject(new GetObjectRequest(bucketName,S3destinationPath),FileObject) 
    println("File copied from S3 Successfully")

  }

  def copyFileFromLocalToS3(S3destinationPath: String, FileObject: File): Unit = {

    /** ******** Use below code for running from EMR  - ***/
    val bucketName = "tivo-session-store"
    var ENDPOINT = "s3.us-east-1.amazonaws.com"
    var REGION = "us-east-1"
    val amazonS3Client = AmazonS3ClientBuilder.standard()
      .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT, REGION)).build()

    amazonS3Client.putObject(new PutObjectRequest(bucketName, S3destinationPath, FileObject))
    println("File copied to S3 Successfully")

  }

  def processFile(fileName: String): Unit = {
    val src = Source.fromFile(fileName)
    

  }

  def upload(word: String) = Action { implicit request: Request[AnyContent] =>
      copyFileFromS3ToLocal("users/sanni/jars/ads_lookup_system_population_history.csv",new File("ads_lookup_system_population_history.csv"))

      copyFileFromLocalToS3("users/sanni/jars/ads_lookup_system_population_history.csv",new File("ads_lookup_system_population_history.csv"))
      Ok("File copied to S3 Successfully")
  }
}
