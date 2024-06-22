pipeline {
    agent any

    tools {
        maven '3.9.8' // Maven aracı ismi ve sürümünü tanımlayın
    }

    stages {
        stage('deneme') {
            steps {
                echo "Java VERSION"
                sh 'java -version' // Java sürümünü kontrol et
                echo "Maven VERSION"
                sh 'mvn -version' // Maven sürümünü kontrol et
                echo 'building project...'
                sh "mvn compile" // Projeyi derle
                sh "mvn package" // Projeyi paketle
                // sh "mvn test" // Test adımı (isteğe bağlı)
                sh "mvn clean install" // Projeyi temizle ve yükle
            }
        }
    }
}
