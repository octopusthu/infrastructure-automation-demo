pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /var/lib/jenkins/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                dir('service') {
                    sh 'mvn clean package'
                }
            }
        }
    }
}
