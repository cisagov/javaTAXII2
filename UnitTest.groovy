import hudson.model.*
import hudson.util.*

String LIB_NAME = "taxii2"
String POM_FILEPATH = "pom.xml"
String TARGET_DIRECTORY = "target"

String TEST_BUILD_FILEPATH = "FLAREcloud-tests/UnitLibTest.groovy"
String TEST_REPO_SSH_URL = "github.com/bcmc/FLAREcloud-tests.git"

def UnitTest

node('maven') {
    stage('github checkout') {
        checkout scm
        withCredentials([usernamePassword(credentialsId: 'github',
                passwordVariable: 'GIT_PASSWORD',
                usernameVariable: 'GIT_USERNAME')]) {
            sh "git clone https://${GIT_USERNAME}:" + java.net.URLEncoder.encode(GIT_PASSWORD, "UTF-8") + "@${TEST_REPO_SSH_URL}"
            UnitTest = load TEST_BUILD_FILEPATH
            UnitTest.runJob(LIB_NAME, POM_FILEPATH, TARGET_DIRECTORY, [])
        }
    }
}