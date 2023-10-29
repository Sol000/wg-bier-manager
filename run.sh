sudo backend/gradlew -p backend/ bootJar
sudo docker compose build
sudo docker compose up