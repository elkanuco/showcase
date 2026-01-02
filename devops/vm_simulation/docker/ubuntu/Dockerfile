FROM ubuntu:24.04

ENV DEBIAN_FRONTEND=noninteractive

# Install systemd and basic utilities
RUN apt-get update && \
    apt-get install -y \
    systemd \
    systemd-sysv \
    curl \
    wget \
    vim \
    && apt-get clean && \
    rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

# Remove unnecessary systemd services to speed up boot
RUN cd /lib/systemd/system/sysinit.target.wants/ && \
    ls | grep -v systemd-tmpfiles-setup | xargs rm -f && \
    rm -f /lib/systemd/system/multi-user.target.wants/* && \
    rm -f /etc/systemd/system/*.wants/* && \
    rm -f /lib/systemd/system/local-fs.target.wants/* && \
    rm -f /lib/systemd/system/sockets.target.wants/*udev* && \
    rm -f /lib/systemd/system/sockets.target.wants/*initctl* && \
    rm -f /lib/systemd/system/basic.target.wants/* && \
    rm -f /lib/systemd/system/anaconda.target.wants/*

# Prevent systemd from starting unwanted services
RUN systemctl mask \
    systemd-resolved.service \
    systemd-networkd.service \
    systemd-timesyncd.service \
    getty@.service \
    getty.target

VOLUME [ "/sys/fs/cgroup" ]

STOPSIGNAL SIGRTMIN+3

CMD ["/sbin/init"]