rootProject.name="donate-pix"

registerModules()

fun registerModules() = rootDir.walkTopDown()
        .filter { it.isDirectory }
        .filter { dir -> dir.listFiles()?.any { it.name == "build.gradle.kts" } == true }
        .map { it.toRelativeString(rootDir).replace(File.separatorChar, ':') }
        .filter { !it.startsWith("build-logic") }
        .forEach { include(it) }