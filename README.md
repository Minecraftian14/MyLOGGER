# MyLOGGER

![Collage](images/collage.png)

A simple _logging_ tool to make **console outputs** look prettier...

For instructions on writing a "Formatting Code",
[![](https://img.shields.io/badge/Logger-Instructions-yellow)](WRITING_A_FORMAT_FOR_DECORATION.md)

For examples of `@Format` annotation,
[![](https://img.shields.io/badge/Logger-Examples-orange)](LOGGER_EXAMPLES.md)

For instructions on using Tables and examples, 
[![](https://img.shields.io/badge/Tables-Examples-green)](TABLE_EXAMPLES.md)

For examples of plotint data, 
[![](https://img.shields.io/badge/Plotting-Examples-red)](PLOTTING.md)

For console specific compatibility,
[![](https://img.shields.io/badge/Console-Compatibility-purple)](SUPPORT.md) 


[![](https://jitpack.io/v/Minecraftian14/MyLOGGER.svg)](https://jitpack.io/#Minecraftian14/MyLOGGER)
[![](https://img.shields.io/discord/740954840259362826?color=7289da&label=Discord)](https://discord.gg/UgMH9c98mg)


## Setup

#### Download

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {

    // Include the library as a dependency. 
    // Replace TAG_NAME with the number aside jitpack badge.
    implementation 'com.github.Minecraftian14:MyLOGGER:TAG_NAME'

    // Optional: To use the annotation processor version instead.
    annotationProcessor 'com.github.Minecraftian14:MyLOGGER:TAG_NAME'

}
```

#### Initialise

```
// default initialisation.
FLog logger = FLog.getNew();

// if using the annotation processor version.
FLog logger = ALog.getNew();

// to use a custom Decoration
Decoration.setDecoration(TagDecoration::new);

// to change the output Decoration when using RawFileDecoration
RawFileDecoration.setPartnerDecorationDecoration(TagDecoration::new);
Decoration.setDecoration(RawFileDecoration::new);
```

#### Usage

```
// Print formatted text 
logger.prt(arg1, arg2, arg3 ... argn);

// Print unformatted text
log.raw(arg);
```