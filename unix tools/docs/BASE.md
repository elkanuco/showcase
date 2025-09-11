# essentials

> [!TIP]
> This is what could be already considered as a solid base in UNIX tools

## base commands

| Name   | Purpose                                                                       |
|--------|-------------------------------------------------------------------------------|
| ls     | List files                                                                    |
| cat    | Type out (concatenate) the files                                              |
| rm     | Remove files                                                                  |
| mv     | Rename (move) files                                                           |
| mkdir  | Create directories                                                            |
| rmdir  | Remove directories                                                            |
| file   | Show file types                                                               |
| ln     | Create (soft) symbolic and hard links                                         |
| tail   | Look at the tail end of the file                                              |
| head   | Look at the beginning of the file                                             |
| less   | Look at the file, one screenful at a time                                     |
| more   | Look at the file, one screenful at a time                                     |
| touch  | Either create an empty file, or update the file modification time             |
| wc     | Count lines, words, and bytes in a file                                       |
| xargs  | Build and execute command lines from standard input                           |
| echo   | Display a line of text                                                        |
| diff   | Compare files line by line                                                    |
| tar   | Archives multiple files and directories into a single file (tarball); can also extract from archives. Often used with compression. |
| gzip   | Compresses files using the gzip compression algorithm, often producing files with .gz extension. |
| pushd   | Saves the current directory on a stack and changes to a new directory. Useful for quick directory navigation. |
| popd   | Returns to the directory at the top of the directory stack (from a previous pushd). |
| cp   | Copies files and directories from one location to another. |
| ps   | Shows a snapshot list of active processes |
| top   | Monitor processes and system performance in real-time |
| dmesg   | Show kernel log messages collected since system boot |

## base distributions

| Distribution | Overview                                                                                                           |
|--------------|-------------------------------------------------------------------------------------------------------------------|
| Debian       | One of the oldest and most stable Linux distributions, Debian is community-driven, focuses on free software, and uses the APT package management system. It serves as a base for many other distributions, including Ubuntu. Known for stability and large software repositories. |
| Ubuntu       | A popular Debian-based distribution designed for ease of use on desktops and servers. Ubuntu focuses on user-friendliness, regular releases, and extensive community support. It supports a wide range of hardware and offers commercial backing from Canonical.              |
| Red Hat      | A commercial Linux distribution aimed at enterprises, famous for stability, security, and long-term support. Red Hat Enterprise Linux (RHEL) is widely used in corporate environments. It uses RPM packages and provides professional support and certifications.       |
| CentOS       | Originally a free rebuild of Red Hat Enterprise Linux source code, CentOS offers a free, stable enterprise-grade OS compatible with RHEL. It’s popular for servers, with long-term support. It is now transitioning to CentOS Stream, a continuously delivered distro upstream of RHEL. |
| Fedora       | A cutting-edge community-driven distribution sponsored by Red Hat. Fedora includes the latest features, technologies, and software versions. It serves as a testing ground for innovations before they are incorporated into RHEL. Focused on rapid release cycles and innovation. |

> **Note:**
> Debian / Ubuntu Family uses APT with .deb packages
> Red Hat / CentOS / Fedora Family uses DNF/YUM with .rpm packages

## special directories

| Directory     | Description                                                            |
|---------------|------------------------------------------------------------------------|
| /             | Root directory, the top-level directory of the filesystem              |
| /bin          | Essential user binaries (commands)                                     |
| /sbin         | System binaries for administration                                     |
| /etc          | System configuration files                                             |
| /home         | User home directories                                                  |
| /root         | Home directory for the root user                                      |
| /tmp          | Temporary files, usually cleared on reboot                            |
| /var          | Variable data such as logs, spool files                               |
| /usr          | User programs and data                                                |
| /lib          | Shared libraries and kernel modules                                   |
| /dev          | Device files                                                          |
| /proc         | Virtual filesystem with process and system information                |
| /sys          | Virtual filesystem with kernel and device information                 |
| /media        | Mount points for removable media                                      |
| /mnt          | Temporary mount points                                                |
| /opt          | Optional or third-party software                                    |
| /srv          | Data for services provided by the system                             |
| /lost+found   | Recovery directory for corrupted files                                |

> **Note:**
> Pseudo-filesystems such as /proc, /sys, and /dev exist entirely in memory and provide dynamic interfaces to the kernel and hardware. They do not correspond to real files stored on disk.
> Other special directories like /bin, /etc, /home, /usr, /var, /opt, /tmp, /root, etc., are real directories on disk containing actual files and data.
> Some directories like /tmp and /run may use tmpfs, a temporary in-memory filesystem, depending on system configuration, so they may reside in memory but can also be backed by disk in some cases.
> The /proc filesystem is an interface to the kernel data structures. /proc contains a subdirectory for each active process, named by the process id (PID). /proc/self is the currently executing process. Some tunable parameters are in the /proc directories.

### common system log files

| Log File                    | Description                                               |
|----------------------------|-----------------------------------------------------------|
| /var/log/syslog             | *General system activity logs (Debian/Ubuntu)*            |
| /var/log/messages           | *General system activity logs (Red Hat/CentOS)*             |
| /var/log/auth.log           | *Authentication logs (Ubuntu/Debian)*                       |
| /var/log/secure             | *Authentication logs (Red Hat/CentOS)*                      |
| /var/log/kern.log           | Kernel event logs                                         |
| /var/log/boot.log           | *Boot process logs*                                        |
| /var/log/cron.log           | Cron daemon logs (scheduled tasks)                        |
| /var/log/mail.log           | Mail server logs                                         |
| /var/log/apache2/           | Apache web server logs (access and error)                 |
| /var/log/nginx/             | Nginx web server logs (access and error)                  |
| /var/log/mysql/             | MySQL database server logs                               |
| /var/log/yum.log            | Yum package manager logs (Red Hat-based)                   |
| /var/log/dnf.log            | Dnf package manager logs (Fedora/Red Hat)                  |
| /var/log/apt/               | Apt package manager logs (Debian/Ubuntu)                   |
| /var/log/faillog            | Failed login attempts log                                 |
| /var/log/lastlog            | Records of last logins                                   |
| /var/log/wtmp               | Records login/logout events                               |
| /var/log/btmp               | Records failed login attempts                             |

<br>

| [Back](../README.md)|
|--------|
