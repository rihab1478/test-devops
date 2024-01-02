pipeline {
    agent any
    tools{
        maven 'M2_HOME'
    }
    stages{
        stage("Cleanup Workspace"){
            steps {
                cleanWs()
            }

        }
        stage('Build Maven'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/rihab1478/test-devops']]])

                sh 'mvn clean install'
            }
        }
        stage("SonarQube") {
            steps {

                    sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=rihab"

            }
        }
stage("Test Application"){
            steps {
                sh "mvn test"
                sh "ls -R target/surefire-reports"
            }
            post{
                always{
                   junit '**/target/surefire-reports/TEST-*.xml'

                }
            }

        }
          stage("Mockito")
          {

              steps {

                    sh "mvn test"

            }
          }


          stage("nexus") {
                 steps {
          sh 'mvn deploy -DskipTests'
        }}
        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build  -t rihab23/test-devops . '
                }
            }
        }
       stage('Push image to Hub'){
                   steps{
                       script{
       withCredentials([string(credentialsId: 'dockerpwd', variable: 'dockerpwd')]) {
                          sh 'docker login -u rihab23 -p ${dockerpwd}'
       }
                          sh 'docker push rihab23/test-devops'
                       }
                   }
               }

    }
}