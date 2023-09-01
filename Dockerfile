# Use an image with the SDK for compilation
FROM openjdk:8-jdk-alpine AS builder

LABEL author="Sylvain K."
LABEL "dev.skamdem.techjobs"="Online TechJobs App."
LABEL version="1.0"
LABEL description="Tech Jobs for everyone, \
at all levels of the learning curve."

WORKDIR /out

# Get the source code inside the image
COPY /src/main .

# Compile source code
RUN javac java/dev/skamdem/techjobs/TechjobsApplication.java

# Create a lightweight image
FROM openjdk:8-jre-alpine

# Copy compiled artifacts from previous image
COPY --from=builder /out/*.class .
EXPOSE 8080
CMD ["java", "TechjobsApplication"]
