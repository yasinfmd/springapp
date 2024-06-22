pipeline {

    agent {
        node {
            label:'maven'
        }
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
