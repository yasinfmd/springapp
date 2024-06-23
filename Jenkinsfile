pipeline {
    agent any

    tools {
        maven '3.9.8'
    }

    stages {

       stage('Git Pullk') {
                steps {
                     script {
                                    def branch = 'main'
                                    echo "Git branch ${branch}  pulling ..."
                                    checkout([$class: 'GitSCM', branches: [[name: "refs/heads/${branch}"]], userRemoteConfigs: [[url: 'https://github.com/yasinfmd/springapp.git']]])
                                    echo "Last commit msg: ${env.GIT_COMMIT}"
                                    echo "Commiter : ${env.GIT_AUTHOR}"
                                }

                }
        }
        stage('Java and Maven Info') {
            steps {
                echo "Java VERSION"
                sh 'java -version'
                echo "Maven VERSION"
                sh 'mvn -version'
                echo 'building project...'
            }
        }




        stage('Compile') {
                steps {
                    echo "Compiling"
                    sh "mvn compile"
                }
        }



               stage('Clean and Install') {
                          steps {
                                 echo "Clean and Install"
                                 sh "mvn clean install"
                          }
                  }
    }
        post {
            always {
                echo 'Pipeline done!'
            }
            success {
                echo 'Pipeline success!'
            }
            failure {
                echo 'Pipeline error!'
            }
        }
}
