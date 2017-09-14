# Teamcity Release Version Generator Plugin

version: 0.1

For operation add "Release Version Generator" to "Build Features"

## Variables

|type|name|
|---|---|
|Environment|`RELEASE_VERSION`|
|Parameter|`release.version`|

### DateTime generator

format 1: `YYYYMMdd.HHmmss`
    

## TODO

- Check the correctness of the plug-in when running several "Build Runner" 
- Add date-time custom format
- Add another Release version generators
- Add separate Release and Snapshot version generator
- Add support Maven release version generator
- Add support Gradle release version generator