@echo off
setlocal

set "MAVEN_HOME=c:\data\maven"
set "PATH=%MAVEN_HOME%\bin;%PATH%"

cd /d "%~dp0"
mvn test site

endlocal
