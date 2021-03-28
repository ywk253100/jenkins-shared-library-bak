import io.goharbor.harbor.FreshInstallPipelineExecutor
import io.goharbor.harbor.HarborInstance

/*
def call(FreshInstallPipelineExecutor executor) {
  pipeline {
    agent {
      label 'helm-client'
    }
    stages {
      stage("Pre-Install") {
        steps {
          script {
            executor.preInstall()
          }
        }
      }
      stage("Install") {
        steps {
          script {
            executor.install()
          }
        }
      }
      stage("Post-Install") {
        steps {
          script {
            executor.postInstall()
          }
        }
      }
      stage("Health Check") {
        steps {
          echo "Health Check"
        }
      }
      stage("Pre-Test") {
        steps {
          script {
            executor.preTest()
          }
        }
      }
      stage("Test") {
        steps {
          echo "Test"
        }
      }
      stage("Post-Test") {
        steps {
          script {
            executor.postTest()
          }
        }
      }
    }
  }
}
*/

def call(FreshInstallPipelineExecutor executor) {
    node("helm-client") {
        HarborInstance instance
        stage('Pre-Install') {
            executor.preInstall()
        }
        stage('Install') {
            instance = executor.install()
            echo instance.getCoreServiceURL
        }
        stage('Post-Install') {
            executor.postInstall()
        }
        stage('Health Check') {
            echo "health checking"
        }
        stage('Pre-Test') {
             executor.preTest()
        }
        stage('Test') {
             echo "testing"
        }
        stage('Post-Test') {
             executor.postTest()
        }
    }
}