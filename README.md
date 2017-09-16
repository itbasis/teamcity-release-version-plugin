# Teamcity Release Version Generator Plugin

version: 0.2

The plug-in is intended for generation of versions of artifacts. In order that in assembly the version of an artifact was generated, it is necessary to add necessary "Build Feature" ([step 1](docs/images/screen_00000.png), [step 2](docs/images/screen_00001.png)).

## The generated properties

|name|
|---|
|`release.version`|
|`env.RELEASE_VERSION`|
|`system.release.version`|

### Date/Time Build Version Generator

default template : `YYYYMMddHHmmss`

Parameter for customization of a format ([pattern letters](https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html)): `release.version.format`

At the moment only one version of the generator - date in format `YYYYMMdd.HHmmss`: [screen 1](docs/images/screen_00002.png), [screen 2](docs/images/screen_00003.png), [screen 3](docs/images/screen_00004.png), [screen 4](docs/images/screen_00005.png).

    

## TODO

- Check the correctness of the plug-in when running several "Build Runner" 
- To add date-time custom format as dialog box in Feature
- To add still versions of generators, in addition to Date/time
- To add separate generation for Release and Snapshot of versions
- To add support of the generator from Maven
- To add support of the generator from Gradle
- To add support of generators not only for Java
- To add tests