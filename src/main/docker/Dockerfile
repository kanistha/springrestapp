FROM openjdk
VOLUME /tmp
ADD springrestapp-1.0-SNAPSHOT.jar springrestapp.jar
RUN sh -c 'touch /springrestapp.jar'
EXPOSE 8080
USER "kanisthaa"
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/springrestapp.jar"]