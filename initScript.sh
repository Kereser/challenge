docker-compose down
docker rmi ensolvers-app_backend
docker rmi ensolvers-app_frontend
cd ./backend
./mvnw clean package
cd ..
docker-compose up