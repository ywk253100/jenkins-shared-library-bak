import io.goharbor.harbor.Executor
def call(Executor executor) {
  if (2 % 2 == 0) {
    pipeline {
      agent any
      stages {
        stage('Even Stage') {
          steps {
            echo "The build number is even"
            echo executor.name
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
            echo executor.name
          }
        }
      }
    }
  }
}