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
import lightsearch.updater.os.Directory;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component("savableAPKFile")
@Scope("prototype")
public class SavableAPKFile implements SavableAPK {

    private final APK apk;
    private final InputStream inputStream;
    private final Directory<String> releasesDirectory;

    public SavableAPKFile(
            APK apk,
            InputStream inputStream,
            Directory<String> releasesDirectory) {
        this.apk = apk;
        this.inputStream = inputStream;
        this.releasesDirectory = releasesDirectory;
    }

    @Override
    synchronized public void save() throws APKException {
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            Path apkPath = Paths.get(
                    releasesDirectory.name() + File.separator + apk.version() + File.separator + apk.name());
            Files.write(apkPath, content);
        } catch (IOException ex) {
            throw new APKException(ex.getMessage());
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
