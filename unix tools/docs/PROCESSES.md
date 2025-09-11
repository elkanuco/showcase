# processes and system monitoring

> [!TIP]
> This is what could be already considered as a solid base in UNIX tools

<br>

## Process and Load Monitoring Utilities

| Utility   | Purpose                                       | Package        |
|-----------|-----------------------------------------------|----------------|
| top       | Process activity, dynamically updated         | procps         |
| uptime    | How long the system is running and the average load | procps    |
| ps        | Detailed information about processes           | procps         |
| pstree    | A tree of processes and their connections      | psmisc (or pstree) |
| mpstat    | Multiple processor usage                        | sysstat        |
| iostat    | CPU utilization and I/O statistics              | sysstat        |
| sar       | Display and collect information about system activity | sysstat   |
| numastat  | Information about NUMA (Non-Uniform Memory Architecture) | numactl  |
| strace    | Information about all system calls a process makes | strace     |

<details>
<summary>ps</summary>

```sh
$ ps auxf
$ ps aux
$ ps -elf
$ ps -eL
$ ps -C "bash"
```

</details>

<details>
<summary>pstree</summary>

```sh
# a visual description of the process ancestry and multi-threaded applications, showing the user the relationships between parent-child processes
$ pstree -aAp PID
$ pstree -aAps PID
$ ls -l /proc/PID/task
```

</details>

<details>
<summary>jobs</summary>

```sh
# The jobs command shows the background processes in the current terminal: it shows the job ID, the state and the command name. The job ID can be used with bg and fg.
$ jobs
$ jobs -l
```

</details>

<details>
<summary>at</summary>

```sh
# The at command executes any non-interactive command at a specified time. For example, to start executing a command after a delay
# send the contents of /var/log/messages via email to admin@example.com after 2 days
# <EOT> means you send an "End of Transmission" signal
$ at now + 2 days
at> mail < /var/log/messages admin@example.com
at> <EOT>
```

> **Note:**
> You need a working mail transfer agent (MTA) like Postfix, Sendmail, or Exim installed and configured on your Linux system to send emails.
> <br>The atd daemon must be installed, enabled, and running for the at command to function.
> <br>The mail command must also be available for sending mail (usually part of the mailutils or bsd-mailx package).
> <br>```sudo systemctl enable --now atd```

```sh
Create the file testat.sh containing:

#!/bin/bash
date > /tmp/datestamp

And then make it executable and queue it up with at:

$ chmod +x testat.sh
$ at now + 1 minute -f testat.sh

You can see if the job is queued up to run with atq:
$ atq
17        Wed Apr 22 08:55:00 2015 a student

Make sure the job actually ran:
$ cat /tmp/datestamp
Wed Apr 22 08:55:00 CDT 2015

What happens if you take the >/tmp/datestamp out of the command? (Hint: type mail if not prompted to do so!)

2. Interactively it is basically the same procedure. Just queue up the job with:

$ at now + 1 minute
at> date > /tmp/datestamp
CTRL-D
$ atq
```

</details>

<details>
<summary>cron</summary>

```sh
# crontab lets users specify jobs
# cron is a very useful and flexible tool. It is used for any job that needs to run on a regular schedule
$ ls -l /etc/cron.d # can be extended with formatted job files
$ ls -l /etc/cron.d.daily # /etc/cron.{hourly,daily,weekly,monthly} can obtain any system script
```

</details>

<details>
<summary>anacron</summary>

<br>

> **Note:**
> On older Linux systems, crontab contained information about when to run the jobs in the /etc/cron.* subdirectories.
> <br> However, it was implicitly assumed the machine was always running.
> <br> If the machine was powered off, scheduled jobs would not run.
> <br> anacron has replaced cron on modern systems.
> <br> anacron will run the necessary jobs in a controlled and staggered manner when the system is running. 
> <br> The key configuration file is /etc/anacrontab.

```sh
Create a file named mycrontab with the following content:

0 10 * * * /tmp/myjob.sh

Then create /tmp/myjob.sh containing:

#!/bin/bash
echo Hello I am running $0 at $(date)

Make it executable:

$ chmod +x /tmp/myjob.sh

Put it in the crontab system with:

$ crontab mycrontab

Verify it was loaded with:

$ crontab -l
0 10 * * * /tmp/myjob.sh

$ sudo ls -l /var/spool/cron/student
-rw------- 1 student student 25 Apr 22 09:59 /var/spool/cron/student

$ sudo cat /var/spool/cron/student
0 10 * * * /tmp/myjob.sh

Note: If you don't really want this running every day, printing out messages like:

Hello I am running /tmp/myjob.sh at Wed Apr 22 10:03:48 CDT 2015

and mailing them to you, you can remove it with:

$ crontab -r

If the machine is not up at 10 AM on a given day, anacron will run the job at a suitable time.
```

</details>

<details>
<summary>sleep </summary>

```sh
# delay 60 * 10 seconds the execution of a script in the background
$ (sleep 600; somescript.sh) &
```

</details>

<details>
<summary>vmstat</summary>

```sh
$ vmstat [options] [delay] [count]
# displays information about memory, paging, I/O, processor activity and processes every 2 seconds 4 times
$ vmstat 2 4
```

</details>

</details>

<details>
<summary>OOM Killer</summary>

### /proc/sys/vm/overcommit_memory

> **Note:**
> You can modify, and even turn off this overcommission by setting the value of ```/proc/sys/vm/overcommit_memory```
> <br> 0: (default) Permit overcommission, but refuse obvious overcommits, and give root users somewhat more memory allocation than normal users.
> <br> 1: All memory requests are allowed to overcommit.
> <br> 2: Turn off overcommission. Memory requests will fail when the total memory commit reaches the size of the swap space plus a configurable percentage (50 by default) of RAM. This factor is modified changing ```/proc/sys/vm/overcommit_ratio.```

</details>

<br>

> **Note:**
> Processes run in the foreground by default. Adding an ampersand (&) after a command will run the command in the background.
> Using Ctrl-Z, you can suspend a foreground process. The bg command makes it run in the background. The fg command puts it back in the foreground. Ctrl-C terminates a foreground process.

<br>

<details>
<summary>kill</summary>

```sh
$ jobs                 # list background jobs
$ ps aux | grep name   # find PID of the process
$ kill PID             # terminate process gracefully
$ kill -9 PID          # force kill if needed
$ kill %jobID          # kill background job by job number
```

</details>

## Memory Monitoring Utilities

| Utility   | Purpose                                        | Package |
|-----------|------------------------------------------------|---------|
| free      | Brief summary of memory usage                   | procps  |
| vmstat    | Detailed virtual memory statistics and block I/O, dynamically updated | procps |
| pmap      | Process memory map                              | procps  |

## I/O Monitoring Utilities

| Utility   | Purpose                                        | Package |
|-----------|------------------------------------------------|---------|
| iostat    | CPU utilization and I/O statistics            | sysstat |
| sar       | Display and collect information about system activity | sysstat |
| vmstat    | Detailed virtual memory statistics and block I/O, dynamically updated | procps |

## Network Monitoring Utilities

| Utility   | Purpose                                        | Package  |
|-----------|------------------------------------------------|----------|
| netstat   | Detailed networking statistics                  | netstat  |
| iptraf    | Gather information on network interfaces        | iptraf   |
| tcpdump   | Detailed analysis of network packets and traffic | tcpdump  |
| wireshark | Detailed network traffic analysis                | wireshark|

## /proc/meminfo Entries

> **Note:**
> The pseudofile /proc/meminfo contains a wealth of information about how memory is being used.
> <br> ```$ cat /proc/meminfo```

| Entry            | Meaning                                                                                   |
|------------------|-------------------------------------------------------------------------------------------|
| MemTotal         | Total usable RAM (physical minus some kernel reserved memory)                             |
| MemFree          | Free memory in both low and high zones                                                    |
| Buffers          | Memory used for temporary block I/O storage                                               |
| Cached           | Page cache memory, mostly for file I/O                                                    |
| SwapCached       | Memory that was swapped back in but is still in the swap file                             |
| Active           | Recently used memory, not to be claimed first                                             |
| Inactive         | Memory not recently used, more eligible for reclamation                                   |
| Active(anon)     | Active memory for anonymous pages                                                         |
| Inactive(anon)   | Inactive memory for anonymous pages                                                       |
| Active(file)     | Active memory for file-backed pages                                                       |
| Inactive(file)   | Inactive memory for file-backed pages                                                     |
| Unevictable      | Pages which can not be swapped out of memory or released                                  |
| Mlocked          | Pages which are locked in memory                                                          |
| SwapTotal        | Total swap space available                                                                |
| SwapFree         | Swap space not being used                                                                 |
| Dirty            | Memory which needs to be written back to disk                                             |
| Writeback        | Memory actively being written back to disk                                                |
| AnonPages        | Non-file backed pages in cache                                                            |
| Mapped           | Memory mapped pages, such as libraries                                                    |
| Shmem            | Pages used for shared memory                                                              |
| Slab             | Memory used in slabs                                                                      |
| SReclaimable     | Cached memory in slabs that can be reclaimed                                              |
| SUnreclaim       | Memory in slabs that can't be reclaimed                                                   |
| KernelStack      | Memory used in kernel stack                                                               |
| PageTables       | Memory being used by page table structures                                                |
| Bounce           | Memory used for block device bounce buffers                                               |
| WritebackTmp     | Memory used by FUSE filesystems for writeback buffers                                     |
| CommitLimit      | Total memory available to be used, including overcommission                               |
| Committed_AS     | Total memory presently allocated, whether or not it is used                               |
| VmallocTotal     | Total memory available in kernel for vmalloc allocations                                  |
| VmallocUsed      | Memory actually used by vmalloc allocations                                               |
| VmallocChunk     | Largest possible contiguous vmalloc area                                                  |
| HugePages_Total  | Total size of the huge page pool                                                          |
| HugePages_Free   | Huge pages that are not yet allocated                                                     |
| HugePages_Rsvd   | Huge pages that have been reserved, but not yet used                                      |
| HugePages_Surp   | Huge pages that are surplus, used for overcommission                                      |
| Hugepagesize     | Size of a huge page                                                                       |

### /proc/sys/vm

> **Note:**
> The /proc/sys/vm directory contains many tunable knobs to control the Virtual Memory system.
> <br> Exactly what appears in this directory will depend somewhat on the kernel version.
> <br> Almost all of the entries are writable (by root).

<br>

| [Back](../README.md)|
|--------|
