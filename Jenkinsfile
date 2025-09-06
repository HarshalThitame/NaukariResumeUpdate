pipeline {
  agent any

  triggers {
    // Run every hour, hashed minute
    cron('H * * * *')
  }

  environment {
    // Pass any global env if you want
    MAVEN_OPTS = '-Dmaven.repo.local=.m2'
  }

  options {
    // Keep logs reasonable
    timestamps()
    ansiColor('xterm')
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build Docker Image') {
      steps {
        script {
          // Build image from Dockerfile in repo
          dockerImage = docker.build("naukri-automation-edge:${env.BUILD_NUMBER}")
        }
      }
    }

    stage('Run Tests (Edge headless in Docker)') {
      steps {
        withCredentials([usernamePassword(credentialsId: 'NAUKRI_LOGIN',
                                          usernameVariable: 'NAUKRI_USER',
                                          passwordVariable: 'NAUKRI_PASS')]) {
          script {
            // Mount resume dir and run tests with needed system properties
            dockerImage.inside("-v /var/lib/jenkins/resume:/data/resume") {
              sh """
                mvn -q test \
                  -Dbrowser=edge \
                  -Dheadless=true \
                  -Dnaukri.username=${NAUKRI_USER} \
                  -Dnaukri.password=${NAUKRI_PASS} \
                  -DresumePath=/data/resume/HarshalThitameResume.pdf
              """
            }
          }
        }
      }
    }
  }

  post {
    always {
      // Collect test reports & any debug artifacts your tests generate
      junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
      archiveArtifacts allowEmptyArchive: true, artifacts: 'headless-debug*.png,headless-debug*.html'
    }
    success {
      echo '✅ Hourly Naukri update ran successfully.'
    }
    failure {
      echo '❌ Build failed — check console log and artifacts.'
    }
  }
}
