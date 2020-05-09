# TextFormatter [![Build Status](https://dev.azure.com/s1115867/s1115867/_apis/build/status/Koenn11.TextFormatter?branchName=master)](https://dev.azure.com/s1115867/s1115867/_build/latest?definitionId=1&branchName=master)

TextFormatter is a simple Bukkit library for minecraft JSON text formatting based on the old Fanciful library

## Installation

Add the JitPack maven repository

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

Add TextFormatter as a maven dependency

```xml
<dependency>
    <groupId>com.github.Koenn11</groupId>
    <artifactId>TextFormatter</artifactId>
    <version>VERSION</version>
</dependency>
```

To use TextFormatter, [Spigot](https://www.spigotmc.org/wiki/spigot-maven/) or [Paper](https://github.com/PaperMC/Paper#how-to-plugin-developers) is required

## Usage

```java
new TextFormatter("Hello World!")
                    .color(ChatColor.YELLOW)
                    .style(ChatColor.BOLD, ChatColor.UNDERLINE)
                    .onHover(MessageAction.SHOW_TEXT, "This is a tooltip!")
                    .send(player);
```
