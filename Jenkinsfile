pipeline {

   agent any
    tools {
        jdk 'jdk'
        maven "Maven3"
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
