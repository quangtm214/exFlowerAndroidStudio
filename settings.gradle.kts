pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        jcenter()
    }
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "com.google.gms.google-services" ->
                    useModule("com.google.gms:google-services:4.4.1")
            }
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}

rootProject.name = "exFlower"
include(":app")
 