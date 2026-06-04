pipeline {
    agent any

    stages {

        stage('Clone Code') {
            steps {
                git 'git branch: 'dev', credentialsId: 'git_credentials', url: 'https://github.com/beauty-singh-code/k8s-practice-project.git''
            }
        }

        stage('Build Application') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Sonar Scan') {
         steps {
             sh '''
             mvn sonar:sonar \
             -Dsonar.projectKey= demo_sonar_argo_k8s_project \
             -Dsonar.host.url= http://sonarqube:9000 \
             -Dsonar.login= sonar-token-id
             '''
         }
     }
	 
    }
}

   