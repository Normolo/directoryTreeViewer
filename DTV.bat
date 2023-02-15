::[Bat To Exe Converter]
::
::YAwzoRdxOk+EWAnk
::fBw5plQjdCyDJHiW90o5Ph5HcDeBLmKqErwS79TV6v27hkQPUeErd53C7L2LN+UB1kHtZ5MuwnVIgf8eCR5udxe8ax0IrXpUn2eKOYmVsACB
::YAwzuBVtJxjWCl3EqQJgSA==
::ZR4luwNxJguZRRnk
::Yhs/ulQjdF+5
::cxAkpRVqdFKZSDk=
::cBs/ulQjdF+5
::ZR41oxFsdFKZSDk=
::eBoioBt6dFKZSTk=
::cRo6pxp7LAbNWATEpCI=
::egkzugNsPRvcWATEpCI=
::dAsiuh18IRvcCxnZtBJQ
::cRYluBh/LU+EWAnk
::YxY4rhs+aU+JeA==
::cxY6rQJ7JhzQF1fEqQJjZks0
::ZQ05rAF9IBncCkqN+0xwdVsFAlXi
::ZQ05rAF9IAHYFVzEqQJQ
::eg0/rx1wNQPfEVWB+kM9LVsJDGQ=
::fBEirQZwNQPfEVWB+kM9LVsJDGQ=
::cRolqwZ3JBvQF1fEqQJQ
::dhA7uBVwLU+EWDk=
::YQ03rBFzNR3SWATElA==
::dhAmsQZ3MwfNWATElA==
::ZQ0/vhVqMQ3MEVWAtB9wSA==
::Zg8zqx1/OA3MEVWAtB9wSA==
::dhA7pRFwIByZRRnk
::Zh4grVQjdCyDJGyX8VAjFBlRQgaNAE+1EbsQ5+n//NajlntTUfo6GA==
::YB416Ek+ZG8=
::
::
::978f952a14a936cc963da21a135fa983
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