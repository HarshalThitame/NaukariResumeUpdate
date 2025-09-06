pipeline {
    agent any

    tools {
        // Use Jenkins-installed JDK & Maven
        jdk 'JAVA_HOME'           // configure JDK 17 in Jenkins
        maven 'MAVEN_HOME'        // configure Maven in Jenkins
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/HarshalThitame/NaukariResumeUpdate.git'
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
