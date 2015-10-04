#  Installation Guide

## Install Java JDK 7(JDK 8 will not work correctly)
Required for Java development

1.	Download 64bits JDK from http://www.oracle.com/technetwork/java/javase/downloads/index.html
2.	Install in C:\Program Files\Java
3.	Create JAVA_HOME system environment variable and set it to: C:\Program Files\Java\jdk<version>
4.	Add to PATH system environment variable %JAVA_HOME%\bin;
5.	Open new command prompt window and test install: ‘javac –version’ should return installed java version.

## Install Android SDK
Required for Android development

1.	Download Standalone SDK tools from http://developer.android.com/sdk/installing/index.html
2.	Install SDK in D:\Android\Sdk
3.	Start SDK manager at end of setup (D:\Android\Sdk\ SDK Manager.exe) and required parts
4.	Create ANDROID_HOME environment variable and set it to: D:\Android\Sdk
5.	Create ANDROID_SDK_HOME environment variable and set it to: D:\Android\Sdk
6.	Add to PATH system environment variable:
    %ANDROID_SDK_HOME%
    %ANDROID_SDK_HOME%\tools
    %ANDROID_SDK_HOME%\platform-tools

## Install Android NDK
Required for Android native code development

1.	Download 64 bit NDK from https://developer.android.com/ndk/downloads/index.html
2.	UnzipNDK in D:\Android\Ndk
3.	Create ANDROID_NDK_HOME environment variable and set it to: D:\Android\Ndk
4.	Add to PATH system environment variable: a.	%ANDROID_NDK_HOME%
5.	Open new command prompt window and test install: ndk-build from the C:\Android\Ndk\samples\hello-jni should run and give no errors

## Install SWIG
Required for code generation of JNI wrappers for Android native code development

1.	Download swigwin-3.0.5.zip from http://www.swig.org/download.html
2.	Unpack into D:\Android\Swig
3.	Create SWIG_HOME environment variable and set it to: D:\Android\Swig
6.	Add to PATH system environment variable: %SWIG_HOME%
4.	Open new command prompt window and test install: ‘swig –version’ should return installed swig version.

## Install Samsung Drivers
Required in you testing with a Samsung device

1.	Download from http://developer.samsung.com/technical-doc/view.do?v=T000000117
2.	Install drivers
3.	Connect Samsung tablet via port and ensure additional drivers are found and correctly installed.


## Android Studio

### Plugins

1. Markdown
2. PlantUML
3. Cucumber (non standard)
4. SonarQube Communinity Plugin

## Install Jenkins
1.	Download Jenkins Windows Installer from https://jenkins-ci.org/
2.	Unzip in any directory and install in C:\Program Files (x86)\Jenkins\
3.	Check http://localhost:8080/

### Install Plugins

1. Gradle
2. Github Authentication plugin
3. GitHub API
4. GitHub

### Set Environment Variables

1. ANDROID_HOME d:\Android\Sdk

### Create Build task

## Install MySQL
Required for Continuous Inspection using SonarCube

1.	Download MySQL Installer 5.6 for Windows from http://dev.mysql.com/downloads/mysql/
2.	Install MySQL custom with options
    *	MySQL server x64
    *	MySQL workbench x64
    *	Connector/J X86
3.	Set MySQL password
4.	Open workbench and run create_database.sql found in this archive. Replace <user> and <password> as required.

## Install SonarQube
Required for Continuous Inspection

1.	Download SonarQube 5.1.1 from http://www.sonarqube.org/downloads/
2.	Unzip in c:\SonarQube
3.	Download SonarQubeRunner from http://www.sonarqube.org/downloads/
4.	Unzip in C:\SonarQubeRunner
5.	Create folder C:\Windows\System32\config\systemprofile\AppData\Local\Temp
6.	Run C:\SonarQube\bin\windows-x86-64\InstallNTService.bat to install the service.
7.	Edit C:\SonarQube\conf\sonar.properties file and uncomment lines:
    *	sonar.jdbc.username=sonar
    *	sonar.jdbc.password=sonar
    *	sonar.jdbc.url=jdbc:mysql://localhost:3306/sonar?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true&useConfigs=maxPerformance
8.	Run C:\SonarQube\bin\windows-x86-64\StartNTService.bat to start the service.
9.	Wait some time for the sonar server to have initialized the first time and go to http://localhost:9000/
10.	Install plugins:
    *	Android plugin
    *	Issue plugin
    *	SCM stats plugin
    *	XML plugin
    *	Widget lab plugin
    *	Build breaker plugin
    *	Build stability plugin
    *	Motion chart plugin
    *	Timeline plugin
    *	Tab metrics plugin
