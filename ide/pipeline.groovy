pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/kozakpawel/jgsu-spring-petclinic.git', branch: 'main'
            }
        }
        stage('Build') {
            steps {
                // Get some code from a GitHub repository b
                git branch: 'main', url: 'https://github.com/kozakpawel/jgsu-spring-petclinic.git'
                sh './mvnw spring-javaformat:apply'
                sh './mvnw clean package'
            }
            
            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }


        }
    }
}
