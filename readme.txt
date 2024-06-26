= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 
= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

8:27 AM 6/26/2024   experiments with redirecting javax.net.debug logging output

  static Logger log = LoggerFactory.getLogger(AhcappApplication.class);
  private static void createClientAndMakeGetRequest() {
    String[] protocolArray2 = { "TLSv1.2" };
    String[] protocolArray3 = { "TLSv1.3" };
    String[] protocolArray32 = { "TLSv1.3", "TLSv1.2" };
    String[] cipherSuiteArray1 = { "TLS_AES_256_GCM_SHA384" };
    DefaultAsyncHttpClientConfig.Builder clientBuilder = Dsl.config()
        .setEnabledProtocols(protocolArray3)
        .setEnabledCipherSuites(cipherSuiteArray1)
        .setConnectTimeout(500);
    AsyncHttpClient client = Dsl.asyncHttpClient(clientBuilder);

git remote add origin git@github.com:ashburndev/ahcapp.git
git branch -M main
git push -u origin main

.\gradlew clean
.\gradlew bootJar
java -jar build\libs\ahcapp-0.0.1-SNAPSHOT.jar
java -Djavax.net.debug=ssl:handshake -jar build\libs\ahcapp-0.0.1-SNAPSHOT.jar

java -jar build\libs\ahcapp-0.0.1-SNAPSHOT.jar > ahcapp-test-20240626a.txt
java -Djavax.net.debug=ssl:handshake -jar build\libs\ahcapp-0.0.1-SNAPSHOT.jar > ahcapp-test-20240626b.txt
java -Djavax.net.debug=ssl:handshake -jar build\libs\ahcapp-0.0.1-SNAPSHOT.jar 2> ahcapp-test-20240626c.txt
java -Djavax.net.debug=ssl:handshake -jar build\libs\ahcapp-0.0.1-SNAPSHOT.jar 2>&1 ahcapp-test-20240626d.txt
java -Djavax.net.debug=ssl:handshake -jar build\libs\ahcapp-0.0.1-SNAPSHOT.jar > ahcapp-test-20240626e.txt 2>&1

C:\Users\DavidHolberton\sbprojs\ahcapp>
C:\Users\DavidHolberton\sbprojs\ahcapp>dir /o:gd
 Volume in drive C is OS
 Volume Serial Number is 38E4-68D8

 Directory of C:\Users\DavidHolberton\sbprojs\ahcapp

04/18/2024  06:56 AM    <DIR>          gradle
04/18/2024  06:56 AM    <DIR>          src
04/25/2024  07:25 AM    <DIR>          .gradle
06/06/2024  11:57 AM    <DIR>          ..
06/25/2024  06:48 AM    <DIR>          build
06/26/2024  08:27 AM    <DIR>          .
04/18/2024  06:56 AM                28 settings.gradle
04/18/2024  06:56 AM             8,692 gradlew
04/18/2024  06:56 AM             2,918 gradlew.bat
04/18/2024  06:56 AM             1,343 HELP.md
04/18/2024  06:56 AM               444 .gitignore
04/25/2024  07:23 AM            40,602 ahcapp-dependencies-20240424.txt
04/25/2024  07:26 AM            61,397 ahcapp-dependencies-20240424b.txt
06/20/2024  08:55 AM             1,047 build.gradle
06/26/2024  08:09 AM             3,470 ahcapp-test-20240626a.txt
06/26/2024  08:12 AM             3,450 ahcapp-test-20240626b.txt
06/26/2024  08:16 AM            21,950 ahcapp-test-20240626c.txt
06/26/2024  08:25 AM            25,420 ahcapp-test-20240626e.txt
06/26/2024  08:28 AM             1,018 readme.txt
              13 File(s)        171,779 bytes
               6 Dir(s)  490,377,805,824 bytes free

C:\Users\DavidHolberton\sbprojs\ahcapp>

