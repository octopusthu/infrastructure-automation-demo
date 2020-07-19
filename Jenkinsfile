pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build') {
            steps {
                sh 'docker-compose build'
            }
        }
        stage('Test') {
            steps {
                // dummy step
            }
        }
        stage('Push to Docker Registry') {
            environment {
                DOCKER_HUB_TOKEN = credentials('docker-hub-token')
            }
            steps {
                sh 'docker login --username=octopusthu --password=$DOCKER_HUB_TOKEN'
                sh 'docker push octopusthu/infrastructure-automation-demo-service'
                sh 'docker push octopusthu/infrastructure-automation-demo-static'
                sh 'docker push octopusthu/infrastructure-automation-demo-reverse-proxy'
            }
        }
        stage('Deliver') {
        }
    }
}
