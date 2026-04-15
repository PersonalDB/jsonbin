@echo off
set DIR=%~dp0
set JAVA_CMD=java

"%JAVA_CMD%" -jar "%DIR%\gradle\wrapper\gradle-wrapper.jar" %*
