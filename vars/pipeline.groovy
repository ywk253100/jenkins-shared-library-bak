import io.goharbor.harbor.Executor

def call(Executor executor) {
  if (buildNumber % 2 == 0) {
    pipeline {
      agent any
      stages {
        stage('Even Stage') {
          steps {
            echo "The build number is even"
            echo executor.Name
          }
        }
      }
    }
  } else {
    pipeline {
      agent any
      stages {
        stage('Odd Stage') {
          steps {
            echo "The build number is odd"
            echo executor.Name
          }
        }
      }
    }
  }
}