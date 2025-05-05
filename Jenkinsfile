pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'jdk21'
    }

    environment {
        ALLURE_RESULTS = 'target/allure-results'
        ALLURE_REPORT = 'target/allure-report'
        GITHUB_USERNAME = 'BilalImam94'  // Replace with your GitHub username
        GITHUB_TOKEN = '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'  // Replace with your GitHub token
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    // Use the GitHub username and token for authentication in the URL
                    def gitUrl = "https://${GITHUB_USERNAME}:${GITHUB_TOKEN}@github.com/BilalImam94/JodaynDemo1.git"
                    git url: gitUrl, branch: 'main'  // Replace 'main' with your branch if needed
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
