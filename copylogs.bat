REM Copy Logcat
del logcat.txt
adb logcat -d -v time > logcat.txt