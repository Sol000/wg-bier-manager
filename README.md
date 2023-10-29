
___
### TODO:
- mby use login-system?
- use a logger
- Add QR-Code generation for banking after invoce
___
### Quick Guide:

Must be installed:
- docker + compose plugin
- Docker Desktop is nice to have.
- Intellij to build the .jar file
  - openjdk-21 installed
  - gradle > 6.0

    
First of all you have to build the .jar file. In Intellij look on the right -> click on gradle -> tasks -> build -> build.
The file you are looking for is in `build/libs/..` !! leave it there !!

Next open a terminal in the project root and type

    docker compose build

after that 

     docker compose up 



