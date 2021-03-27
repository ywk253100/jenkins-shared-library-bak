import io.goharbor.harbor.FreshInstallPipelineExecutor

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
        def hostname = ""
        stage('Pre-Install') {
            executor.preInstall()
        }
        stage('Install') {
            hostname = executor.install()
        }
        stage('Example') {
            echo hostname
        }
    }
}