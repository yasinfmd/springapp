pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                script {
                    def mvnHome = tool name: 'M3', type: 'maven'
                    sh "${mvnHome}/bin/mvn clean install"
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    def mvnHome = tool name: 'M3', type: 'maven'
                    sh "${mvnHome}/bin/mvn test"
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    echo 'Deploying...'
                }
            }
        }
    }
}
