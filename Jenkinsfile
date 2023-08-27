def TeleUrl = 'https://api.telegram.org/bot1647436660:AAF8OrMu-FqlwWxj2_M4k_allIGLNwjS83E/sendMessage'
def TeleChatId = -1001249400214
def ServiceName = '[Admin_BE]'
def GIT_COMMIT_MSG
def GIT_AUTHOR
def staging_host = '103.75.185.204'
def repo = 'ladi-admin-service'
def image = 'admin_be_image.tar'
def container_name = 'BE_admin_service'
def ssh_port = '24700'

pipeline {
    agent any

    stages {
		stage ('Get git commit information') {
			steps {
				script {
					GIT_COMMIT_MSG = sh(script: 'git log -1 --pretty=%B', returnStdout: true).trim()
					GIT_AUTHOR = sh(script: 'git log -1 --pretty=format:"%Cgreen%an %Cblue%ae"', returnStdout: true).trim()
				}
			}
		}
		
		stage ('Message to telegram chat room when started to deploy') {
			steps {
				script {
					sh """
						curl -X GET \
						-H "Content-Type: application/json" \
						-d '{"chat_id": ${TeleChatId}, "text": "${ServiceName} Commit \\"${GIT_COMMIT_MSG}\\" performed by ${GIT_AUTHOR} started to deploy."}' \
						${TeleUrl}
					"""
				}
			}
		}
	
		stage('Build and deploy') {
				steps {
						sh 'mvn clean install package -Dmaven.test.skip=true'
	
						// Build docker image
						sh 'docker build -t ' + repo + ':latest -f Dockerfile .'

						// Stop current container
						//sh 'ssh -i ~/.ssh/id_rsa -p ' + ssh_port + ' root@' + staging_host + ' "docker stop ' + container_name + '"'
	
						// Remove current container
						//sh 'ssh -i ~/.ssh/id_rsa -p ' + ssh_port + ' root@' + staging_host + ' "docker rm ' + container_name + '"'
	
						// Run container with new image
						sh 'docker run -itdp 8080:8080 --name ' + container_name + ' ' + repo + ':latest''
					
						// Remove unused images on staging host
						sh 'sudo /root/remove_none_images.sh'
				}
			}
		}

    post {
		failure {
			script {
				echo 'The pipeline run error!'
				sh """
					curl -X GET \
					-H "Content-Type: application/json" \
					-d '{"chat_id": ${TeleChatId}, "text": "${ServiceName} Commit \\"${GIT_COMMIT_MSG}\\" performed by ${GIT_AUTHOR} deployed failed."}' \
					${TeleUrl}
				"""
			}
		}
  
		success {
			script {
				echo 'The pipeline run success!'
				sh """
					curl -X GET \
					-H "Content-Type: application/json" \
					-d '{"chat_id": ${TeleChatId}, "text": "${ServiceName} Commit \\"${GIT_COMMIT_MSG}\\" performed by ${GIT_AUTHOR} deployed successful."}' \
					${TeleUrl}
				"""
			}
		}
	}
}
