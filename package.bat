del src\test\resources\MSAppCenter.zip || exit /b
7z a src\test\resources\MSAppCenter.zip MSAppCenter\ || exit /b
mvn clean -DskipTests -P prepare-for-upload package
