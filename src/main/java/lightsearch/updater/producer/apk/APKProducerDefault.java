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

package lightsearch.updater.producer.apk;

import lightsearch.updater.apk.APK;
import lightsearch.updater.apk.SavableAPK;
import lightsearch.updater.os.Directory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service("apkProducerDefault")
public class APKProducerDefault implements APKProducer {

    private final ApplicationContext ctx;

    public APKProducerDefault(ApplicationContext ctx) {
        this.ctx = ctx;
    }


    @Override
    public APK getSimpleAPKInstance(String name, String version) {
        return (APK) ctx.getBean("simpleAPK", name, version);
    }

    @Override
    public SavableAPK getSavableAPKFileInstance(APK apk, InputStream inputStream, Directory<String> releasesDirectory) {
        return (SavableAPK) ctx.getBean("savableAPKFile", apk, inputStream, releasesDirectory);
    }

    @Override
    public SavableAPK getSavableAPKWithLoggerInstance(SavableAPK apk) {
        return (SavableAPK) ctx.getBean("savableAPKWithLogger", apk);
    }
}
