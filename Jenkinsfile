pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
    }
    environment {
        DOCKER_HUB_TOKEN = credentials('docker-hub-token')
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
                sh 'echo Tests passed!'
            }
        }
        stage('Pre Push to Docker Registry') {
            steps {
                sh 'docker login --username=octopusthu --password=$DOCKER_HUB_TOKEN'
            }
        }
        stage('Push to Docker Registry') {
            failFast true
            parallel {
                stage('Push to Docker Registry - service') {
                    steps {
                        sh 'docker push octopusthu/infrastructure-automation-demo-service'
                    }
                }
                stage('Push to Docker Registry - static') {
                    steps {
                        sh 'docker push octopusthu/infrastructure-automation-demo-static'
                    }
                }
                stage('Push to Docker Registry - reverse-proxy') {
                    steps {
                        sh 'docker push octopusthu/infrastructure-automation-demo-reverse-proxy'
                    }
                }
            }
        }
        stage('Post Push to Docker Registry') {
            steps {
                sh 'docker logout'
            }
        }
        stage('Deliver') {
            steps {
                sh 'echo Delivered!'
            }
        }
    }
}
