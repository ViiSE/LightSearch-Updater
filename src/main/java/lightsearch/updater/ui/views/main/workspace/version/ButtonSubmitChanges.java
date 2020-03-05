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

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import lightsearch.updater.constants.StorageValues;
import lightsearch.updater.exception.APKException;
import lightsearch.updater.exception.StorageException;
import lightsearch.updater.os.Directory;
import lightsearch.updater.os.Storage;
import lightsearch.updater.producer.apk.APKProducer;

import java.io.InputStream;

public class ButtonSubmitChanges extends Button {

    public ButtonSubmitChanges(
            String label,
            Storage storage,
            ComboBoxVersions comboBoxVersions,
            APKProducer apkProducer,
            Directory<String> releasesDirectory) {
        super(label);
        super.setWidthFull();
        super.addClickListener(event -> {
            try {
                String apkName = storage.load(StorageValues.APK_NAME, String.class);
                String apkVersion = storage.load(StorageValues.APK_VERSION, String.class);
                InputStream is = storage.load(StorageValues.APK_INPUT_STREAM, InputStream.class);
                if (apkVersion != null && !(apkVersion.equals(""))) {
                    if (is != null) {
                        try {
                            apkProducer.getSavableAPKWithLoggerInstance(
                                    apkProducer.getSavableAPKFileInstance(
                                            apkProducer.getSimpleAPKInstance(
                                                    apkName,
                                                    apkVersion),
                                            is,
                                            releasesDirectory))
                                    .save();
                            Notification.show("APK was saved successfully!");
                            comboBoxVersions.setInvalid(false);
                        } catch (APKException ex) {
                            Notification.show(ex.getMessage());
                        }
                    } else
                        Notification.show("Upload APK file!");
                } else {
                    comboBoxVersions.setErrorMessage("Select APK version!");
                    comboBoxVersions.setInvalid(true);
                }
            } catch (StorageException ex) {
                Notification.show(ex.getMessage());
            }
        });
    }
}
