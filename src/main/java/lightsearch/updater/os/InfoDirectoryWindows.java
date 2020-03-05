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
package lightsearch.updater.os;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;

@Component("infoDirectoryWindows")
public class InfoDirectoryWindows implements Directory<String> {

    private final Directory<String> currentDirectory;

    public InfoDirectoryWindows(@Qualifier("currentDirectoryFromFile") Directory<String> currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    @Override
    public String name() {
        return "file:" + File.separator + File.separator + File.separator +
                currentDirectory.name() + "update" + File.separator + "info" + File.separator;
    }
}
