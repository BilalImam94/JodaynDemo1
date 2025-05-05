pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'jdk21'
    }

    environment {
        ALLURE_RESULTS = 'target/allure-results'
        ALLURE_REPORT = 'target/allure-report'
    }

    stages {
        stage('Checkout') {
            steps {
                withCredentials([string(credentialsId: 'GitHub_PAT_Bilal', variable: 'GITHUB_TOKEN')]) {
                    git branch: 'main', url: 'https://github.com/BilalImam94/JodaynDemo1.git'
                }
            }
        }

        stage('Build, Test, and Generate Report') {
            steps {
                sh 'mvn clean verify' // this includes test + report generation
            }
        }

        stage('Publish Allure Report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: "${env.ALLURE_RESULTS}"]]
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: "${env.ALLURE_REPORT}/**", allowEmptyArchive: true
        }
    }
}
