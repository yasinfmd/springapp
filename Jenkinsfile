pipeline {
    agent any

    tools {
        maven:'3.9.8'
    }

    stages {
        stage('deneme') {
            steps {
                 echo "Java VERSION"
                sh 'java -version'
                echo "Maven VERSION"
                sh 'mvn -version'
                echo 'building project...'
                sh "mvn compile"
                sh "mvn package"
                //sh "mvn test"
                sh "mvn clean install"
            }
        }
    
    }
}
