pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "beauty7718/demo_sonar_argo_k8s_project-0.0.1"
        KUBECONFIG = '/root/.kube/config-jenkins'
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

            // stage('Push Docker Image to Nexus') {

            // steps {

            //  sh '''
            //     docker tag demo_sonar_argo_k8s_project:0.0.1 \
            //     host.docker.internal:8081/demo_sonar_argo_k8s_project:0.0.1

            //      docker push \
            //      host.docker.internal:8081/demo_sonar_argo_k8s_project:0.0.1
            //      '''
            //      }
            // }

            stage('Push Docker Image To Nexus') {
            steps {
        withCredentials([
            usernamePassword(
                credentialsId: 'nexus',
                usernameVariable: 'NEXUS_USER',
                passwordVariable: 'NEXUS_PASS'
            )
        ]) {
            sh '''
            echo "$NEXUS_PASS" | docker login \
            host.docker.internal:8083 \
            -u "$NEXUS_USER" \
            --password-stdin

            docker tag demo_sonar_argo_k8s_project:0.0.1 \
            host.docker.internal:8083/demo_sonar_argo_k8s_project:0.0.1

            docker push \
            host.docker.internal:8083/demo_sonar_argo_k8s_project:0.0.1
            '''
        }
    }
}

stage('Deploy to Kubernetes via Helm') {
    steps {
        withCredentials([
            usernamePassword(
                credentialsId: 'nexus',
                usernameVariable: 'NEXUS_USER',
                passwordVariable: 'NEXUS_PASS'
            )
        ]) {
            sh '''
            # Create/Update Docker registry secret
            kubectl create secret docker-registry nexus-secret \
              --docker-server=host.docker.internal:8083 \
              --docker-username=$NEXUS_USER \
              --docker-password=$NEXUS_PASS \
              --dry-run=client -o yaml | kubectl apply -f -

            # Deploy Helm chart
            helm upgrade --install beauty-demo-chart ./beauty-demo-chart \
              --set image.repository=host.docker.internal:8083/demo_sonar_argo_k8s_project \
              --set image.tag=0.0.1
            '''
        }
    }
}
    }
}

   