@echo off
:: Delegate execution to packaging-builder/build.bat
call "%~dp0\packaging-builder\build.bat" %*
