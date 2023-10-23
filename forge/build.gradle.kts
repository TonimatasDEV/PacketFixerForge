plugins {
    id("net.minecraftforge.gradle") version "6.+"
    id("idea")
    id("eclipse")
}

val modVersion: String by extra
val minecraftVersion: String by extra
val forgeVersion: String by extra
val forgeVersionRange: String by extra

group = "net.tonimatasdev"
version = "$modVersion-$minecraftVersion"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(8))
    withSourcesJar()
}

minecraft {
    mappings("snapshot", "20171003-1.12")

    copyIdeResources.set(true)

    runs {
        configureEach {
            workingDirectory(project.file("run"))
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")

            mods {
                create("packetfixer") {
                    source(sourceSets.main.get())
                }
            }
        }

        create("client") {
            property("forge.enabledGameTestNamespaces", "packetfixer")
        }

        create("server") {
            property("forge.enabledGameTestNamespaces", "packetfixer")
            args("--nogui")
        }

        create("gameTestServer") {
            property("forge.enabledGameTestNamespaces", "packetfixer")
        }

        create("data") {
            workingDirectory(project.file("run-data"))
            args("--mod", "packetfixer", "--all", "--output", file("src/generated/resources/"), "--existing", file("src/main/resources/"))
        }
    }
}

sourceSets.main.get().resources { srcDir("src/generated/resources") }

repositories {

}

dependencies {
    minecraft("net.minecraftforge:forge:1.12.2-14.23.5.2860")
}



tasks.withType<ProcessResources> {
    val replaceProperties = mapOf("modVersion" to modVersion, "minecraftVersion" to minecraftVersion)

    inputs.properties(replaceProperties)

    filesMatching(listOf("META-INF/mods.toml", "pack.mcmeta")) {
        expand(replaceProperties)
    }
}


tasks.jar {
    manifest {
        attributes(
            "Manifest-Version" to "1.0",
            "FMLCorePlugin" to "net.tonimatasdev.packetfixer.PacketFixerCore"
        )
    }

    finalizedBy("reobfJar")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
