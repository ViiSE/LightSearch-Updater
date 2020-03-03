/*
 *  Copyright 2019 ViiSE
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package lightsearch.updater.release.info;

import lightsearch.updater.LightSearchUpdater;
import lightsearch.updater.exception.ReleaseInfoException;
import lightsearch.updater.producer.release.info.ReleaseInfoCheckerProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static lightsearch.updater.message.TestMessage.*;
import static org.testng.Assert.assertNotNull;

@SpringBootTest(classes = LightSearchUpdater.class)
public class ReleaseInfoCheckerTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    public ReleaseInfoCheckerProducer releaseInfoCheckerProducer;

    @Test
    @Parameters({"infoContent"})
    public void check(String infoContent) {
        testBegin("ReleaseInfoChecker", "check()");

        ReleaseInfoChecker checker = releaseInfoCheckerProducer.getReleaseInfoCheckerJSONInstance();
        assertNotNull(checker, "ReleaseInfoChecker is null!");

        try {
            checker.check(infoContent);
            System.out.println("Check is done!");
        } catch (ReleaseInfoException ex) {
            catchMessage(ex);
        }

        testEnd("ReleaseInfoChecker", "check()");
    }
}
