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
                // Checkout the repository (Jenkins handles this automatically)
                checkout scm
            }
        }

        stage('Build, Test, and Generate Report') {
            steps {
                bat 'mvn clean verify'
            }
        }
    }

    post {
        always {
            // Always archive the report folder
             archiveArtifacts allowEmptyArchive: true, artifacts: 'allure-report/**', followSymlinks: false

            // Always publish the Allure report
            allure includeProperties: false, jdk: '', commandline: 'allure', results: [[path: "${env.ALLURE_RESULTS}"]], reportBuildPolicy: 'ALWAYS'
        }
    }
}
