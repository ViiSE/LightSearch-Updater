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

package lightsearch.updater.apk;

import lightsearch.updater.exception.APKException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("savableAPKWithLogger")
@Scope("prototype")
public class SavableAPKWithLogger implements SavableAPK {

    private final Logger logger = LoggerFactory.getLogger(SavableAPK.class);
    private final SavableAPK apk;

    public SavableAPKWithLogger(SavableAPK apk) {
        this.apk = apk;
    }

    @Override
    public void save() {
        try {
            apk.save();
            logger.info("APK is saved: version - " + apk.version() + ", name - " + apk.name());
        } catch (APKException ex) {
            logger.error("save: " + ex.getMessage());
        }
    }

    @Override
    public String name() {
        return apk.name();
    }

    @Override
    public String version() {
        return apk.version();
    }
}
