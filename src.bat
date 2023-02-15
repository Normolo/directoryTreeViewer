@echo off
setlocal EnableExtensions EnableDelayedExpansion

set target_file=dir_tree.txt

echo Checking if file already exists...
if exist "%target_file%" (
    set /p overwrite=The file already exists. Overwrite it? (Y/N)
    if /i "!overwrite!" EQU "N" (
        echo Exiting script...
        goto :EOF
    )
)

echo Generating directory tree...
tree /a /f > "%target_file%"
echo Done! Opening %target_file%...
start "" notepad "%target_file%"

pause >nul
