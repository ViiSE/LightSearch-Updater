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

package lightsearch.updater.ui.views.main.workspace.file;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import lightsearch.updater.exception.ReleaseInfoException;
import lightsearch.updater.release.info.ReleaseInfo;

public class ButtonChangeInfo extends Button {

    public ButtonChangeInfo(String text, ReleaseEditor editor, ReleaseInfo<String> info) {
        super(text);
        super.setWidth("100%");
        super.addClickListener(event -> {
            try {
                info.save(editor.getValue());
                Notification.show("Release information is changed!");
            } catch(ReleaseInfoException ex) {
                Notification.show(ex.getMessage());
            }
        });
    }
}
