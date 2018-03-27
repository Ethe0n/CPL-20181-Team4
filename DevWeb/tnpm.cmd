@ECHO OFF

IF %1 == + (
  npm i %2
  npm i @types/%2 -D
  EXIT
)
IF %1 == - (
  npm r %2
  npm r @types/%2 -D
  EXIT
)
ECHO Availables: +, -