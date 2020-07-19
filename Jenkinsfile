pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Static Analysis') {
            steps {
                // do static program analysis
                sh 'echo Static Analysis passed!'
            }
        }
        stage('Build') {
            steps {
                sh 'docker-compose -f docker-compose.local.yml build'
            }
        }
        stage('Pre Push to Docker Registry') {
            environment {
                DOCKER_HUB_TOKEN = credentials('docker-hub-token')
            }
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
            failFast false
            parallel {
                stage('Deliver - test') {
                    environment {
                        SSH_CREDS_TEST = credentials('ssh-creds-test')
                        SSH_HOST_TEST = credentials('ssh-host-test')
                        SSH_PORT_TEST = credentials('ssh-port-test')
                        SSH_PATH_TEST = credentials('ssh-path-test')
                    }
                    steps {
                        script{
                            if (fileExists('deliver-to-test')) {
                                sh 'scp -i $SSH_CREDS_TEST -P $SSH_PORT_TEST docker-compose.server.yml $SSH_CREDS_TEST_USR@$SSH_HOST_TEST:$SSH_PATH_TEST'
                                sh 'ssh $SSH_CREDS_TEST_USR@$SSH_HOST_TEST -p $SSH_PORT_TEST -i $SSH_CREDS_TEST'
                                sh 'cd $SSH_PATH_TEST'
                                sh 'docker-compose -f docker-compose.server.yml down'
                                sh 'docker-compose -f docker-compose.server.yml up -d'
                                sh 'echo Delivered to test environment!'
                            } else {
                                echo 'Not meant for test environment!'
                            }
                        }
                    }
                }
                stage('Deliver - staging') {
                    steps {
                        script{
                            if (fileExists('deliver-to-staging')) {

                                // Business logic is basically the same as 'Deliver - test' so is omitted here

                                sh 'echo Delivered to staging environment!'
                            } else {
                                echo 'Not meant for staging environment!'
                            }
                        }
                    }
                }
                stage('Deliver - prod') {
                    steps {
                        script{
                            if (fileExists('deliver-to-prod')) {

                                // Business logic is basically the same as 'Deliver - test' so is omitted here

                                sh 'echo Delivered to prod environment!'
                            } else {
                                echo 'Not meant for prod environment!'
                            }
                        }
                    }
                }
            }
        }
    }
}
