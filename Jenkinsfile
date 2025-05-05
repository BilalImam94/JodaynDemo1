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
                git branch: 'main', url: 'https://github.com/BilalImam94/JodaynDemo1.git'
                 credentialsId: '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',
                            url: 'https://github.com/BilalImam94/JodaynDemo1.git'
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
