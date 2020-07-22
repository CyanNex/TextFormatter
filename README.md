# TextFormatter [![](https://jitpack.io/v/Koenn11/TextFormatter.svg)](https://jitpack.io/#Koenn11/TextFormatter)

TextFormatter is a simple Bukkit library for minecraft JSON text formatting based on the old Fanciful library

## Installation

### Maven

Add the [JitPack](https://jitpack.io/) maven repository

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

Add TextFormatter as a dependency

```xml
<dependency>
    <groupId>com.github.Koenn11</groupId>
    <artifactId>TextFormatter</artifactId>
    <version>VERSION</version>
</dependency>
```

### Gradle

Add the [JitPack](https://jitpack.io/) maven repository

```gradle
repositories {
    maven { url 'https://jitpack.io' }
}
```

Add TextFormatter as a dependency

```gradle
dependencies {
    implementation 'com.github.Koenn11:TextFormatter:Tag'
}
```

To use TextFormatter, [Spigot](https://www.spigotmc.org/wiki/spigot-maven/) or [Paper](https://github.com/PaperMC/Paper#how-to-plugin-developers) is required

Pre-compiled binaries can be found in the [Releases](https://github.com/Koenn11/TextFormatter/releases) tab

## Usage

```java
new TextFormatter("Hello World!")
                    .color(ChatColor.YELLOW)
                    .style(ChatColor.BOLD, ChatColor.UNDERLINE)
                    .onHover(MessageAction.SHOW_TEXT, "This is a tooltip!")
                    .send(player);
```
