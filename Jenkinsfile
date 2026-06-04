pipeline {
    agent any

    stages {

        stage('Clone Code') {
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

        stage('Sonar Scan') {
         steps {
             sh '''
             mvn sonar:sonar \
             -Dsonar.projectKey=demo_sonar_argo_k8s_project \
             -Dsonar.host.url=http://host.docker.internal:9000 \
             -Dsonar.login=sqa_7ad23c59141c64a879b1cbae7ce8139a80dd387d
             '''
         }
     }
	 
    }
}

   