@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  SCD2_2021_Exam startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and SC_D2_2021_EXAM_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\SCD2_2021_Exam.jar;%APP_HOME%\lib\maven-dependency-plugin-3.1.2.jar;%APP_HOME%\lib\javafx-media-12.0.2-mac.jar;%APP_HOME%\lib\javafx-controls-12.0.2-mac.jar;%APP_HOME%\lib\javafx-graphics-12.0.2-mac.jar;%APP_HOME%\lib\javafx-graphics-12.0.2.jar;%APP_HOME%\lib\javafx-base-12.0.2-mac.jar;%APP_HOME%\lib\javafx-base-12.0.2.jar;%APP_HOME%\lib\guava-28.2-jre.jar;%APP_HOME%\lib\json-simple-1.1.1.jar;%APP_HOME%\lib\sqlite-jdbc-3.34.0.jar;%APP_HOME%\lib\javase-3.4.1.jar;%APP_HOME%\lib\core-3.4.1.jar;%APP_HOME%\lib\maven-reporting-impl-3.0.0.jar;%APP_HOME%\lib\maven-artifact-transfer-0.11.0.jar;%APP_HOME%\lib\maven-common-artifact-filters-3.1.0.jar;%APP_HOME%\lib\file-management-3.0.0.jar;%APP_HOME%\lib\maven-shared-io-3.0.0.jar;%APP_HOME%\lib\maven-compat-3.0.jar;%APP_HOME%\lib\maven-core-3.0.jar;%APP_HOME%\lib\maven-plugin-api-3.0.jar;%APP_HOME%\lib\doxia-site-renderer-1.9.jar;%APP_HOME%\lib\maven-dependency-analyzer-1.11.1.jar;%APP_HOME%\lib\maven-artifact-3.0.jar;%APP_HOME%\lib\maven-aether-provider-3.0.jar;%APP_HOME%\lib\maven-model-builder-3.0.jar;%APP_HOME%\lib\maven-model-3.0.jar;%APP_HOME%\lib\maven-repository-metadata-3.0.jar;%APP_HOME%\lib\maven-reporting-api-3.0.jar;%APP_HOME%\lib\plexus-archiver-4.2.2.jar;%APP_HOME%\lib\plexus-io-3.2.0.jar;%APP_HOME%\lib\maven-shared-utils-3.2.1.jar;%APP_HOME%\lib\commons-io-2.6.jar;%APP_HOME%\lib\doxia-module-xhtml-1.9.jar;%APP_HOME%\lib\doxia-module-xhtml5-1.9.jar;%APP_HOME%\lib\doxia-core-1.9.jar;%APP_HOME%\lib\doxia-sink-api-1.9.jar;%APP_HOME%\lib\sisu-inject-plexus-1.4.2.jar;%APP_HOME%\lib\maven-settings-builder-3.0.jar;%APP_HOME%\lib\maven-settings-3.0.jar;%APP_HOME%\lib\plexus-sec-dispatcher-1.3.jar;%APP_HOME%\lib\doxia-logging-api-1.9.jar;%APP_HOME%\lib\plexus-velocity-1.2.jar;%APP_HOME%\lib\plexus-container-default-1.7.1.jar;%APP_HOME%\lib\doxia-decoration-model-1.9.jar;%APP_HOME%\lib\doxia-skin-model-1.9.jar;%APP_HOME%\lib\plexus-i18n-1.0-beta-10.jar;%APP_HOME%\lib\wagon-provider-api-2.10.jar;%APP_HOME%\lib\plexus-utils-3.3.0.jar;%APP_HOME%\lib\maven-dependency-tree-3.0.1.jar;%APP_HOME%\lib\velocity-tools-2.0.jar;%APP_HOME%\lib\velocity-1.7.jar;%APP_HOME%\lib\commons-lang-2.6.jar;%APP_HOME%\lib\commons-collections-3.2.2.jar;%APP_HOME%\lib\classworlds-1.1.jar;%APP_HOME%\lib\failureaccess-1.0.1.jar;%APP_HOME%\lib\listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\checker-qual-2.10.0.jar;%APP_HOME%\lib\error_prone_annotations-2.3.4.jar;%APP_HOME%\lib\j2objc-annotations-1.3.jar;%APP_HOME%\lib\junit-4.10.jar;%APP_HOME%\lib\jcommander-1.78.jar;%APP_HOME%\lib\jai-imageio-core-1.4.0.jar;%APP_HOME%\lib\aether-impl-1.7.jar;%APP_HOME%\lib\aether-util-1.7.jar;%APP_HOME%\lib\aether-spi-1.7.jar;%APP_HOME%\lib\aether-api-1.7.jar;%APP_HOME%\lib\plexus-interpolation-1.14.jar;%APP_HOME%\lib\plexus-classworlds-2.5.1.jar;%APP_HOME%\lib\plexus-component-annotations-1.7.1.jar;%APP_HOME%\lib\commons-lang3-3.8.1.jar;%APP_HOME%\lib\httpclient-4.5.8.jar;%APP_HOME%\lib\httpcore-4.4.11.jar;%APP_HOME%\lib\commons-compress-1.20.jar;%APP_HOME%\lib\snappy-0.4.jar;%APP_HOME%\lib\xz-1.8.jar;%APP_HOME%\lib\asm-7.0.jar;%APP_HOME%\lib\aether-util-0.9.0.M2.jar;%APP_HOME%\lib\commons-codec-1.11.jar;%APP_HOME%\lib\slf4j-api-1.7.5.jar;%APP_HOME%\lib\hamcrest-core-1.1.jar;%APP_HOME%\lib\sisu-inject-bean-1.4.2.jar;%APP_HOME%\lib\plexus-cipher-1.4.jar;%APP_HOME%\lib\xbean-reflect-3.7.jar;%APP_HOME%\lib\google-collections-1.0.jar;%APP_HOME%\lib\struts-taglib-1.3.8.jar;%APP_HOME%\lib\struts-tiles-1.3.8.jar;%APP_HOME%\lib\struts-core-1.3.8.jar;%APP_HOME%\lib\commons-chain-1.1.jar;%APP_HOME%\lib\commons-validator-1.3.1.jar;%APP_HOME%\lib\commons-digester-1.8.jar;%APP_HOME%\lib\commons-beanutils-1.7.0.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\dom4j-1.1.jar;%APP_HOME%\lib\oro-2.0.8.jar;%APP_HOME%\lib\sslext-1.2-0.jar;%APP_HOME%\lib\sisu-guice-2.1.7-noaop.jar;%APP_HOME%\lib\antlr-2.7.2.jar

@rem Execute SCD2_2021_Exam
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %SC_D2_2021_EXAM_OPTS%  -classpath "%CLASSPATH%" SCD2_2021_Exam.App %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable SC_D2_2021_EXAM_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%SC_D2_2021_EXAM_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
