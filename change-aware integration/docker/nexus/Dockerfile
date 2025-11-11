FROM sonatype/nexus3
USER root
COPY ./setup.sh .
RUN chmod +x setup.sh
USER nexus 
ENTRYPOINT ["/bin/bash", "-c", "\
    /opt/sonatype/nexus/bin/nexus run & \
    ./setup.sh && \
    wait $! \
"]