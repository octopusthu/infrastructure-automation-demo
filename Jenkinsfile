pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build') {
            steps {
                dir('service') {
                    sh 'mvn clean package'
                }
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
}
