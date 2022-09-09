package com.lavkesh.plugin.springBootCommon

import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessPlugin
import com.diffplug.spotless.LineEnding
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.GroovyCompile
import org.gradle.api.tasks.compile.JavaCompile

class CodeStylePlugin implements Plugin<Project> {

    void apply(Project project) {
        project.logger.info("Applying Spring Boot Common Code Style Plugin.");
        project.pluginManager.apply(SpotlessPlugin.class);

        def type = project.extensions.getByType(SpotlessExtension.class);
        type.encoding = 'UTF-8';
        type.lineEndings = LineEnding.UNIX;

        setJavaConfiguration(project, type);

        setGroovyConfiguration(project, type);
    }

    private void setJavaConfiguration(Project project, SpotlessExtension type) {
        type.java({
            it.target("**/*.java");
            it.googleJavaFormat();
        });

        project.tasks.withType(JavaCompile.class).every({
            it.dependsOn('spotlessCheck');
        });
    }

    private void setGroovyConfiguration(Project project, SpotlessExtension type) {
        def filePath = project.projectDir.getAbsolutePath() + '/spotless-eclipse.xml';
        if (project.file(filePath).exists()) {
            project.logger.info("Found spotless-eclipse.xml in project directory.");
            type.groovy({
                it.target("**/*.groovy");
                it.greclipse().configFile(filePath);
            });
            type.groovyGradle({
                it.greclipse().configFile(filePath)
            });
        } else {
            project.logger.info("Not found spotless-eclipse.xml in project directory.");
            type.groovy({
                it.target("**/*.groovy");
                it.greclipse();
            });

            type.groovyGradle({
                it.greclipse()
            });
        }

        project.tasks.withType(GroovyCompile.class).every({
            it.dependsOn('spotlessCheck');
        });
    }
}
