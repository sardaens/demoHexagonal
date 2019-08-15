#!groovy

pipeline {
  agent none
  stages {
    stage('Maven Install and Test') {
      agent {
        docker {
          image 'maven:3.5.0'
          args '-v /root/.m2:/root/.m2'
        }
      }
      steps {
        sh 'mvn clean install'
      }
      post {
          always {
              junit 'target/surefire-reports/*.xml'
          }
      }

    }
    stage('Docker Build') {
      agent any
      steps {
        sh 'docker build -t sardaens/demoHexagonale:latest .'
      }
    }
    stage('Docker Push') {
      agent any
      steps {
        withCredentials([usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
          sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
          sh 'docker push sardaens/demoHexagonale:latest'
        }
      }
    }
  }
}