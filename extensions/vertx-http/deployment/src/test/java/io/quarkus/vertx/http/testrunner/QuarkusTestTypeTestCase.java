package io.quarkus.vertx.http.testrunner;

import java.util.function.Supplier;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkus.test.QuarkusDevModeTest;
import io.quarkus.vertx.http.deployment.devmode.tests.TestStatus;

public class QuarkusTestTypeTestCase {

    @RegisterExtension
    static QuarkusDevModeTest test = new QuarkusDevModeTest()
            .setArchiveProducer(new Supplier<JavaArchive>() {
                @Override
                public JavaArchive get() {
                    return ShrinkWrap.create(JavaArchive.class).addClass(HelloResource.class)
                            .add(new StringAsset(TestRunnerTestUtils.appProperties("quarkus.test.type=quarkus-test")),
                                    "application.properties");
                }
            })
            .setTestArchiveProducer(new Supplier<JavaArchive>() {
                @Override
                public JavaArchive get() {
                    return ShrinkWrap.create(JavaArchive.class).addClasses(SimpleET.class, UnitET.class);
                }
            });

    @Test
    public void testQuarkusTestMode() throws InterruptedException {
        TestStatus ts = TestRunnerTestUtils.waitForFirstRunToComplete();
        Assertions.assertEquals(1L, ts.getLastRun());
        Assertions.assertEquals(1L, ts.getTestsFailed());
        Assertions.assertEquals(1L, ts.getTestsPassed());
        Assertions.assertEquals(0L, ts.getTestsSkipped());
        Assertions.assertEquals(-1L, ts.getRunning());
    }
}
