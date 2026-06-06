FROM jenkins/jenkins:lts

USER root

RUN apt-get update && \
    apt-get install -y docker.io maven
    

RUN jenkins-plugin-cli --plugins \
    sonar \
    git \
    workflow-aggregator \
    docker-workflow
    
# kubectl
RUN curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl" \
    && chmod +x kubectl \
    && mv kubectl /usr/local/bin/

    # helm
RUN curl https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3 | bash

USER jenkins