Web:
### To run: ./gradlew build

### To run En komplett version av projektet finns nu i mappen "/build/distributions/team05-race-clock-vXX.zip"

### To run Unzippa filen till lämplig plats.

### To run Stega in i den unzippade mappen.

### To run Kör ./start.sh on Linux , ./start.bat on Windows


Java:
### To run: ./gradlew run

### To run / build the register subproject : ./gradlew :register:run

### To run / build result project you have to specify arguments: ./gradlew :result:run --args='hh:mm:ss nameFilePath startTimesPath endTimesPath resultPath shouldSort'

### Example: ./gradlew :result:run --args='00:00:00 input/namnfil.txt input/starttider.txt input/maltider.txt output/resultatFil.txt true'

### Currently no docs file in the repo like before, also some slight changes in the gradle files / structure from the other zero feature release