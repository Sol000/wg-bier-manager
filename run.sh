gradle -p backend/ bootJar
sudo docker compose build
sudo docker compose up