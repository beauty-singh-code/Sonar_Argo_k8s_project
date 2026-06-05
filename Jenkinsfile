pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "beauty7718/demo_sonar_argo_k8s_project-0.0.1"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'dev',
                    credentialsId: 'git_credential',
                    url: 'https://github.com/beauty-singh-code/Sonar_Argo_k8s_project.git'
            }
        }

        stage('Build Application') {
            steps { 
                sh 'mvn clean package'
            }
        }

//      stage('Sonar Scan') {
//     steps {
//         withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
//             sh '''
//             mvn sonar:sonar \
//             -Dsonar.projectKey=demo_sonar_argo_k8s_project \
//             -Dsonar.host.url=http://host.docker.internal:9000 \
//             -Dsonar.login=$SONAR_TOKEN \
//             -Dsonar.ws.timeout=300
//             '''
//         }
//     }
// }
	    stage('Upload To Nexus') {
             steps {

                sh 'mvn deploy'

                    }
            }

            stage('Build Docker Image') {
            steps {
                sh 'docker build -t $DOCKER_IMAGE .'
            }
        }

            stage('Push Docker Image') {

            steps {

             sh '''
                  docker tag demo_sonar_argo_k8s_project:0.0.1 \
                  nexus:8083/demo_sonar_argo_k8s_project:0.0.1

                 docker push \
                 nexus:8081/demo_sonar_argo_k8s_project:0.0.1
                 '''
                 }
            }
    }
}

   