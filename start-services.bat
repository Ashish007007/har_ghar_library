@echo off
title HarGharLibrary Microservices Manager
:menu
cls
echo =================================================================
echo             HAR GHAR LIBRARY - SERVICE CONTROLLER
echo =================================================================
echo  [1] Start Eureka Registry (8761)
echo  [2] Start API Gateway (8080)
echo  [3] Start User Service (8081)
echo  [4] Start Book Catalog Service (8082)
echo  [5] Start Geo Search Service (8083)
echo  [6] Start Rental Order Service (8084)
echo  [7] Start Rare Book Service (8085)
echo  [8] Start Chat Service (8086)
echo -----------------------------------------------------------------
echo  [A] START ALL SERVICES
echo  [S] STOP ALL SERVICES (Auto-Kill all without prompt)
echo  [E] EXIT
echo =================================================================
set /p choice="Enter your choice: "

if /I "%choice%"=="1" goto s1
if /I "%choice%"=="2" goto s2
if /I "%choice%"=="3" goto s3
if /I "%choice%"=="4" goto s4
if /I "%choice%"=="5" goto s5
if /I "%choice%"=="6" goto s6
if /I "%choice%"=="7" goto s7
if /I "%choice%"=="8" goto s8
if /I "%choice%"=="A" goto run_all
if /I "%choice%"=="S" goto stop_all
if /I "%choice%"=="E" exit
goto menu

:s1
start "HGL_EUREKA" cmd /c "cd /d D:\HarGharLibrary\service-registry && mvn spring-boot:run"
goto menu

:s2
start "HGL_GATEWAY" cmd /c "cd /d D:\HarGharLibrary\api-gateway && mvn spring-boot:run"
goto menu

:s3
start "HGL_USER" cmd /c "cd /d D:\HarGharLibrary\user-service && mvn spring-boot:run"
goto menu

:s4
start "HGL_BOOK" cmd /c "cd /d D:\HarGharLibrary\book-catalog-service && mvn spring-boot:run"
goto menu

:s5
start "HGL_GEO" cmd /c "cd /d D:\HarGharLibrary\geo-search-service && mvn spring-boot:run"
goto menu

:s6
start "HGL_RENTAL" cmd /c "cd /d D:\HarGharLibrary\rental-order-service && mvn spring-boot:run"
goto menu

:s7
start "HGL_RARE" cmd /c "cd /d D:\HarGharLibrary\rare-book-service && mvn spring-boot:run"
goto menu

:s8
start "HGL_CHAT" cmd /c "cd /d D:\HarGharLibrary\chat-service && mvn spring-boot:run"
goto menu

:run_all
echo Starting Eureka Registry first...
start "HGL_EUREKA" cmd /c "cd /d D:\HarGharLibrary\service-registry && mvn spring-boot:run"
timeout /t 15 /nobreak >nul

echo Starting API Gateway...
start "HGL_GATEWAY" cmd /c "cd /d D:\HarGharLibrary\api-gateway && mvn spring-boot:run"
timeout /t 5 /nobreak >nul

echo Starting all Microservices...
start "HGL_USER" cmd /c "cd /d D:\HarGharLibrary\user-service && mvn spring-boot:run"
start "HGL_BOOK" cmd /c "cd /d D:\HarGharLibrary\book-catalog-service && mvn spring-boot:run"
start "HGL_GEO" cmd /c "cd /d D:\HarGharLibrary\geo-search-service && mvn spring-boot:run"
start "HGL_RENTAL" cmd /c "cd /d D:\HarGharLibrary\rental-order-service && mvn spring-boot:run"
start "HGL_RARE" cmd /c "cd /d D:\HarGharLibrary\rare-book-service && mvn spring-boot:run"
start "HGL_CHAT" cmd /c "cd /d D:\HarGharLibrary\chat-service && mvn spring-boot:run"
echo All services launched!
pause
goto menu

:stop_all
echo.
echo Stopping all running microservices and closing terminal windows...

:: 1. Force kill processes on all ports without prompt
for %%P in (8761 8080 8081 8082 8083 8084 8085 8086) do (
    for /f "tokens=5" %%a in ('netstat -aon ^| findstr :%%P ^| findstr LISTENING') do (
        echo Y | taskkill /F /T /PID %%a >nul 2>&1
    )
)

:: 2. Close all spawned cmd windows by their Window Titles
echo Y | taskkill /F /T /FI "WINDOWTITLE eq HGL_*" /IM cmd.exe >nul 2>&1

echo.
echo All microservices stopped and terminals closed instantly!
timeout /t 2 /nobreak >nul
goto menu