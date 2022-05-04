package org.mule.extension.conditional.until.successful;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.junit.Test;

public class ConditionalUntilSuccessfulOperationsTestCase extends MuleArtifactFunctionalTestCase {

  /**
   * Specifies the mule config xml with the flows that are going to be executed in the tests, this file lives in the test resources.
   */
  @Override
  protected String getConfigFile() {
    return "test-mule-config.xml";
  }

  @Test
  public void executeSayHiOperation() throws Exception {
    String payloadValue = ((String) flowRunner("sayHiFlow").run()
                                      .getMessage()
                                      .getPayload()
                                      .getValue());
    assertThat(payloadValue, is("Hello Mariano Gonzalez!!!"));
  }

  @Test
  public void executeRetrieveInfoOperation() throws Exception {
    String payloadValue = ((String) flowRunner("retrieveInfoFlow")
            .run()
            .getMessage()
            .getPayload()
            .getValue());
    assertThat(payloadValue, is("Using Configuration [configId] with Connection id [aValue:100]"));
  }

  @Test
  public void executeCUSSOperationSuccess() throws Exception {
    String payloadValue = ((String) flowRunner("conditionalUntilSuccessfulFlowSuccess")
            .run()
            .getMessage()
            .getPayload()
            .getValue());
    //assertThat(payloadValue, is("Using Configuration [configId] with Connection id [aValue:100]"));
  }
  @Test
  public void executeCUSSOperationFail() throws Exception {
    String payloadValue = ((String) flowRunner("conditionalUntilSuccessfulFlowFail")
            .run()
            .getMessage()
            .getPayload()
            .getValue());
    //assertThat(payloadValue, is("Using Configuration [configId] with Connection id [aValue:100]"));
  }
}
