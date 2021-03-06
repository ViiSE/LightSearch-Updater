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

import lightsearch.updater.release.info.ReleaseInfo;
import lightsearch.updater.ui.views.main.LabelWorkspace;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("filesWorkspaceCreatorDefault")
public class FilesWorkspaceCreatorDefault implements FilesWorkspaceCreator {

    private final ReleaseInfo<String> info;

    public FilesWorkspaceCreatorDefault(@Qualifier("releaseInfoWithLoggerLikeString") ReleaseInfo<String> info) {
        this.info = info;
    }

    @Override
    public FilesWorkspace create() {
        LabelWorkspace label = new LabelWorkspace("Release Information Editor");
        ReleaseEditor editor = new ReleaseEditor(info);
        ButtonChangeInfo buttonChangeInfo = new ButtonChangeInfo("Change info", editor, info);
        TextAreaWorkspace textAreaWorkspace = new TextAreaWorkspace(label, editor);

        return new FilesWorkspace(textAreaWorkspace, buttonChangeInfo);
    }
}
