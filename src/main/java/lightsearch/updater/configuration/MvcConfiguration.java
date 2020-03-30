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

package lightsearch.updater.configuration;

import lightsearch.updater.os.Directory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan
public class MvcConfiguration implements WebMvcConfigurer {

    private final Directory<String> infoDirectory;
    private final Directory<String> releasesDirectory;

    public MvcConfiguration(
            @Qualifier("infoDirectoryWindows") Directory<String> infoDirectory,
            @Qualifier("releasesDirectoryWindows") Directory<String> releasesDirectory) {
        this.infoDirectory = infoDirectory;
        this.releasesDirectory = releasesDirectory;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/update/info/**")
                .addResourceLocations(infoDirectory.name());
        registry.addResourceHandler("/update/releases/**")
                .addResourceLocations(releasesDirectory.name());
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/app/**")
                .addResourceLocations("classpath:/app/");
    }
}
