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

import com.vaadin.flow.component.combobox.ComboBox;
import lightsearch.updater.apk.APKVersionsUploader;
import lightsearch.updater.constants.StorageValues;
import lightsearch.updater.os.Storage;

public class ComboBoxVersions extends ComboBox<String> {

    public ComboBoxVersions(String label, APKVersionsUploader apkVersionsUploader, Storage storage) {
        super(label);
        super.setPreventInvalidInput(true);
        super.setWidthFull();
        super.setItems(apkVersionsUploader.uploadAll());
        super.addValueChangeListener(event ->  {
            storage.save(StorageValues.APK_VERSION, event.getValue());
            if(!event.getValue().isEmpty())
                super.setInvalid(false);
        });
    }
}
