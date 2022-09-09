package com.lavkesh.plugin.springBootCommon

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.compile.JavaCompile

class JavaPlugin implements Plugin<Project>{

    void apply(Project project) {
        project.logger.info("Applying Spring Boot Common Java Plugin ");

        project.pluginManager.apply(org.gradle.api.plugins.JavaPlugin.class);

        project.pluginManager.apply(org.gradle.api.plugins.GroovyPlugin.class);


        def type = project.extensions.getByType(JavaPluginExtension.class)
        type.targetCompatibility = JavaVersion.VERSION_1_8;
        type.sourceCompatibility = JavaVersion.VERSION_1_8;

        project.tasks.withType(JavaCompile.class)
                .every {it.options.encoding = 'UTF-8' };
    }
}
