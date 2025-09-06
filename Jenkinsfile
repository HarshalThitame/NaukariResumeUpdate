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
                git branch: 'master',
                    url: 'https://github.com/HarshalThitame/NaukariResumeUpdate.git'
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean test'
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
