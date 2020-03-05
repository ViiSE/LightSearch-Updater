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

import lightsearch.updater.os.Directory;
import lightsearch.updater.util.ComparatorStringToNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component("apkVersionsUploaderDefault")
public class APKVersionsUploaderDefault implements APKVersionsUploader {

    private final Logger logger = LoggerFactory.getLogger(APKVersionsUploaderDefault.class);
    private final Directory releasesDirectory;

    public APKVersionsUploaderDefault(@Qualifier("releasesDirectoryDefault") Directory releasesDirectory) {
        this.releasesDirectory = releasesDirectory;
    }

    @Override
    public Collection<String> uploadAll() {
        try {
            Path root = Paths.get(releasesDirectory.path());
            List<Path> subFolders = Files.walk(root, 1).filter(Files::isDirectory).collect(Collectors.toList());
            logger.info("APK versions is uploaded");
            return subFolders.stream()
                    .skip(1).map((path) -> path.getFileName().toString())
                    .sorted(new ComparatorStringToNumber())
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            logger.error("uploadVersions: " + ex.getMessage() + ". Create empty versions list.");
            return Collections.emptyList();
        }
    }
}
