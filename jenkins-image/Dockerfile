FROM jenkins/jenkins:lts

USER root

RUN apt-get update && \
    apt-get install -y docker.io maven
    

RUN jenkins-plugin-cli --plugins \
    sonar \
    git \
    workflow-aggregator \
    docker-workflow

RUN curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl" \
    && chmod +x kubectl \
    && mv kubectl /usr/local/bin/

USER jenkins