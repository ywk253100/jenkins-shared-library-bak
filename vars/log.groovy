import io.goharbor.harbor.FreshInstallPipelineExecutor

def call(FreshInstallPipelineExecutor executor) {
  pipeline {
    agent any
    stages {
      stage("Pre-Install") {
        steps {
          executor.preInstall()
        }
      }
      stage("Install") {
        steps {
          executor.install()
        }
      }
      stage("Post-Install") {
        steps {
          executor.postInstall()
        }
      }
      stage("Health Check") {
        steps {
          echo "Health Check"
        }
      }
      stage("Pre-Test") {
        steps {
          executor.preTest()
        }
      }
      stage("Test") {
        steps {
          echo "Test"
        }
      }
      stage("Post-Test") {
        steps {
          executor.postTest()
        }
      }
    }
  }
}