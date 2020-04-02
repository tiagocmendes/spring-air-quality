pipeline {
    agent any
    tools {
        jdk 'jdk8'
        maven 'maven_3.6.0'
    }
    stages {
        stage('Install') {
            steps {
                sh "cd airQuality"
                sh "mvn clean install"
            }
            post {
                always {
                    junit '**/target/*-reports/TEST-*.xml'
                }
            }
        }
    }
}