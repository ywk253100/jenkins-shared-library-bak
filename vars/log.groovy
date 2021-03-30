import io.goharbor.harbor.jenkins.library.FreshInstallPipelineExecutor
import io.goharbor.harbor.jenkins.library.HarborInstance
import io.goharbor.harbor.jenkins.library.TestCaseRunner

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
        checkout scm
        HarborInstance instance
        stage('Pre-Install') {
            executor.preInstall()
        }
        stage('Install') {
            instance = executor.install()
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
            TestCaseRunner runner = new TestCaseRunner(this, instance)
            runner.run()
        }
        stage('Post-Test') {
             executor.postTest()
        }
    }
}