pipeline {
    agent any


    environment {
        BROWSER = "edge"      // can switch to chrome/firefox if needed
    }

    triggers {
        // Run daily at 9 AM
        cron('0 9 * * *')
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/your-repo/naukri-automation.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }
    }

    post {
        success {
            echo '✅ Naukri profile updated successfully!'
        }
        failure {
            echo '❌ Naukri update failed. Check logs in Jenkins.'
        }
    }
}
