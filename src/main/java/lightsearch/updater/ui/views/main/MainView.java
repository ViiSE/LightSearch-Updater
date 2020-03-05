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

package lightsearch.updater.ui.views.main;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import lightsearch.updater.ui.views.main.workspace.MainWorkspace;
import lightsearch.updater.ui.views.main.workspace.RootWorkspace;
import lightsearch.updater.ui.views.main.workspace.file.FilesWorkspaceCreator;
import lightsearch.updater.ui.views.main.workspace.version.APKVersionWorkspaceCreator;
import org.springframework.beans.factory.annotation.Autowired;

@Route
@PWA(name = "LightSearch Updater Admin Panel", shortName = "Admin Panel")
@PageTitle("Admin Panel")
public class MainView extends VerticalLayout {

    public MainView(
            @Autowired APKVersionWorkspaceCreator apkVersionWorkspaceCreator,
            @Autowired FilesWorkspaceCreator filesWorkspaceCreator) {
        FooterMainView footer = new FooterMainView();

        RootWorkspace rootWorkspace = new RootWorkspace(
                apkVersionWorkspaceCreator.create(),
                filesWorkspaceCreator.create());
        MainWorkspace mainWorkspace = new MainWorkspace(footer, rootWorkspace);

        super.add(mainWorkspace);
    }
}
