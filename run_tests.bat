@echo off
call build asdf
if %errorlevel% == 0 (
  @echo on
  java -cp bin io.github.paulszefer.TestFramework
)