pipeline {
  agent any
  environment {
    NODE_HOME = '/usr/bin/node'
    JAVA_HOME = '/usr/lib/jvm/java-11-openjdk-amd64'
  }
  stages {
    stage('Checkout') {
      steps { git url: 'https://github.com/username/repo.git', credentialsId: 'github-creds' }
    }
    stage('Build Frontend') {
      steps {
        dir('frontend') {
          sh 'npm install'               // 安装依赖
          sh 'npm run build'             // 打包产出到 build/ 目录
        }
      }
    }
    stage('Build Backend') {
      steps {
        dir('backend') {
          sh 'mvn clean package -DskipTests'  // 生成 target/*.jar
        }
      }
    }
    stage('Archive') {
      steps {
        archiveArtifacts artifacts: 'frontend/build/**', fingerprint: true
        archiveArtifacts artifacts: 'backend/target/*.jar', fingerprint: true
      }
    }
    stage('Deploy') {
      steps {
        sh './deploy.sh'    // 在虚拟机上执行部署脚本
      }
    }
  }
  post {
    always { junit 'backend/target/surefire-reports/*.xml' }
    success { echo 'Deploy succeeded.' }
    failure { mail to: 'you@example.com', subject: 'Build Failed', body: 'Check Jenkins.' }
  }
}