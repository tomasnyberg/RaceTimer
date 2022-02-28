Web:
### To Run: 

### Type ./gradlew build


### A complete version of project exist in the map: "/build/distributions/team05-race-clock-vXX.zip"

### Unzip the file.

### Step inside the unzipped file.

### Type ./start.sh on Linux , ./start.bat on Windows


Java:
### To Run:

### Type ./gradlew run

### Build the register subproject : ./gradlew :register:run

### Build result project you have to specify arguments: ./gradlew :result:run --args='hh:mm:ss nameFilePath startTimesPath endTimesPath resultPath shouldSort'

### Example: ./gradlew :result:run --args='00:00:00 input/namnfil.txt input/starttider.txt input/maltider.txt output/resultatFil.txt true'

### Currently no docs file in the repo like before, also some slight changes in the gradle files / structure from the other zero feature release