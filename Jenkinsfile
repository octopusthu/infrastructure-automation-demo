pipeline {
    agent any
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
