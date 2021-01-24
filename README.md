### Sonar

- sudo docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:latest
- mvn sonar:sonar   -Dsonar.projectKey=discord-service   -Dsonar.host.url=http://localhost:9000   -Dsonar.login=6237f6212582f276200ad761236aef71b7978297

### Docker
- sudo usermod -aG docker $USER
- newgrp docker
