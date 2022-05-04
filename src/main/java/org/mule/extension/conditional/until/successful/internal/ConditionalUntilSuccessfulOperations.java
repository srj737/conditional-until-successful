package org.mule.extension.conditional.until.successful.internal;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.runtime.process.CompletionCallback;
import org.mule.runtime.extension.api.runtime.route.Chain;


/**
 * This class is a container for operations, every public method in this class will be taken as an extension operation.
 */
public class ConditionalUntilSuccessfulOperations {

  /**
   * Example of an operation that uses the configuration and a connection instance to perform some action.
   */
  @MediaType(value = ANY, strict = false)
  public String retrieveInfo(@Config ConditionalUntilSuccessfulConfiguration configuration, @Connection ConditionalUntilSuccessfulConnection connection){
    return "Using Configuration [" + configuration.getConfigId() + "] with Connection id [" + connection.getId() + "]";
  }

  /**
   * Example of a simple operation that receives a string parameter and returns a new string message that will be set on the payload.
   */
  @MediaType(value = ANY, strict = false)
  public String sayHi(String person) {
    return buildHelloMessage(person);
  }

  /**
   * Private Methods are not exposed as operations
   */
  private String buildHelloMessage(String person) {
    return "Hello " + person + "!!!";
  }

  @MediaType(value = ANY, strict = false)
  public void logDecorator(Chain operations, CompletionCallback<Object, Object> callback) {
    //LOGGER.debug("Invoking child operations")
    System.out.println("Invoking child operations");
    operations.process(
            result -> {
              //LOGGER.debug(result.getOutput());
              System.out.println(result.getOutput());
              callback.success(result);
            },
            (error, previous) -> {
              //LOGGER.error(error.getMessage());
              System.out.println(error.getMessage());
              callback.error(error);
            });
  }
}
