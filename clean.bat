@echo off
:: Delegate execution to packaging-builder/clean.bat
call "%~dp0\packaging-builder\clean.bat" %*
