package com.lavkesh.plugin.springBootCommon

import io.spring.gradle.dependencymanagement.DependencyManagementPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.PluginManager

class CommonPlugin implements Plugin<Project> {

    private def plugins = [
            JavaPlugin.class,
            CodeStylePlugin.class,
            DependencyManagementPlugin.class
    ];

    void apply(Project project) {
        project.logger.info("Applying Spring Boot Common Plugin.");
        applyPlugin(project.pluginManager);
    }

    private void applyPlugin(PluginManager pluginManager) {
        plugins.each { pluginManager.apply(it) };
    }

}
