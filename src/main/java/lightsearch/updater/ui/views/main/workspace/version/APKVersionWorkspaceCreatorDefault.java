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

package lightsearch.updater.ui.views.main.workspace.version;

import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import lightsearch.updater.apk.APKVersionCreator;
import lightsearch.updater.apk.APKVersionsUploader;
import lightsearch.updater.os.Directory;
import lightsearch.updater.os.Storage;
import lightsearch.updater.producer.apk.APKProducer;
import lightsearch.updater.ui.views.main.LabelWorkspace;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("apkVersionWorkspaceCreatorDefault")
public class APKVersionWorkspaceCreatorDefaultImpl implements APKVersionWorkspaceCreator {

    private final APKVersionsUploader apkVersionsUploader;
    private final APKVersionCreator apkVersionCreator;
    private final Storage storage;
    private final APKProducer apkProducer;
    private final Directory<String> releasesDirectory;

    public APKVersionWorkspaceCreatorDefaultImpl(
            APKVersionsUploader apkVersionsUploader,
            APKVersionCreator apkVersionCreator,
            Storage storage,
            APKProducer apkProducer,
            @Qualifier("releasesDirectoryDefault") Directory<String> releasesDirectory) {
        this.apkVersionsUploader = apkVersionsUploader;
        this.apkVersionCreator = apkVersionCreator;
        this.storage = storage;
        this.apkProducer = apkProducer;
        this.releasesDirectory = releasesDirectory;
    }

    @Override
    public APKVersionWorkspace create() {
        LabelWorkspace label = new LabelWorkspace("Releases Manager");
        ComboBoxVersions comboBoxVersions = new ComboBoxVersions("Versions Releases", apkVersionsUploader, storage);
        DialogNewVersion dialogNewVersion = new DialogNewVersion(apkVersionCreator, apkVersionsUploader, comboBoxVersions);
        ButtonCreateNewVersion buttonCreateNewVersion = new ButtonCreateNewVersion("Create new version release", dialogNewVersion);
        UploadAPK uploadAPK = new UploadAPK(storage, new MemoryBuffer());
        ButtonSubmitChanges buttonSubmitChanges = new ButtonSubmitChanges(
                "Submit changes",
                storage,
                comboBoxVersions,
                apkProducer,
                releasesDirectory);

        return new APKVersionWorkspace(label, comboBoxVersions, buttonCreateNewVersion, uploadAPK, buttonSubmitChanges);
    }
}
