# MyLOGGER

![Collage](src/docs/resources/images/collage.png)

A simple _logging_ tool to make **console outputs** look prettier...

| Wanna have a look around?                              | Try visiting these links                                                                                        |
|--------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|
| Get an intro to "writing formatting codes" at          | [![](https://img.shields.io/badge/Logger-Instructions-yellow)](/src/docs/md/WRITING_A_FORMAT_FOR_DECORATION.md) |
| Other important stuff regarding the usage of this tool | [![](https://img.shields.io/badge/Logger-Stuff-cyan)](/src/docs/md/OTHER_IMPORTANT_THINGS.md)                   |
| Get simple and complicated examples for better insight | [![](https://img.shields.io/badge/Logger-Examples-orange)](/src/docs/md/LOGGER_EXAMPLES.md)                     |
| You know, you can also create Tables with this tool!   | [![](https://img.shields.io/badge/Tables-Examples-green)](/src/docs/md/TABLE_EXAMPLES.md)                       |
| And well, we have simple plotting tools too            | [![](https://img.shields.io/badge/Plotting-Examples-red)](/src/docs/md/PLOTTING.md)                             |
| For console specific compatibility                     | [![](https://img.shields.io/badge/Console-Compatibility-purple)](/src/docs/md/SUPPORT.md)                       |

<br />

[![](https://jitpack.io/v/Minecraftian14/MyLOGGER.svg)](https://jitpack.io/#Minecraftian14/MyLOGGER)
[![](https://img.shields.io/discord/872811194170347520?color=%237289da&logoColor=%23424549)](https://discord.gg/Ar6Zuj2m82)

## Setup

#### Download

```groovy
allprojects {
    repositories {
        // ... other sources
        maven { url 'https://jitpack.io/#in.mcxiv' }
    }
}

dependencies {
    // ... other dependencies

    // Include the library as a dependency. 
    // Replace TAG_NAME with the number aside jitpack badge.
    implementation 'com.github.Minecraftian14:MyLOGGER:TAG_NAME'

    // Optional: To use the annotation processor version instead.
    annotationProcessor 'com.github.Minecraftian14:MyLOGGER:TAG_NAME'

}
```

#### Initialise

```groovy
// Default initialisation, to print to Console.
FLog logger = FLog.getNew();

// The annotation processor version.
FLog logger = ALog.getNew();

// To write logs to to a file "Hello.txt".
FLog logger = FileLog.getNew("new.txt");

// to use a custom Decoration
log.setDecorationType(Decorations.TAG); // enable tag decos.
log.setDecorationType(Decorations.RAW); // enable raw decos, ie, no strange characters.

// creating a logger to both, print to console and write to file.
FLog log = ULog.forNew()
        .add(FLog.getNew())
        .add(FileLog.getNew("new.txt"))
        .create();
```

#### Usage

```groovy
// Print formatted text 
logger.prt(arg1, arg2, arg3 ... argn);

// Print unformatted text
log.raw(arg);
```
