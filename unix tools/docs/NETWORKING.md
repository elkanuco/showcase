# networking and networking interfaces

> [!TIP]
> These are some tools and hints to take into account when working on networking

<br>

## base commands

| Command   | Description                                                                                       |
|-----------|-------------------------------------------------------------------------------------------------|
| `ip`      | Show and manipulate routing, devices, policy routing, and tunnels (replaces ifconfig and route) |
| `ifconfig`| Display or configure network interfaces (deprecated on many systems, replaced by `ip`)          |
| `ping`    | Send ICMP echo requests to test network connectivity                                             |
| `netstat` | Display network connections, routing tables, interface statistics, masquerade connections, etc.  |
| `ss`      | Show socket statistics, lists TCP, UDP, and other network connections (replacement for netstat) |
| `traceroute`| Trace the path packets take to a network host                                                 |
| `dig`     | DNS lookup utility to query DNS name servers                                                    |
| `nslookup`| Query Internet domain name servers for DNS information                                          |
| `arp`     | View and manipulate the system's ARP cache (IP-to-MAC address resolution)                       |
| `hostname`| Show or set the system's hostname                                                               |
| `curl`    | Transfer data from or to a server using supported protocols                                     |
| `wget`    | Non-interactive network downloader                                                              |
| `tcpdump` | Command-line packet analyzer for network troubleshooting                                        |
| `mtr`     | Network diagnostic tool combining ping and traceroute                                          |
| `nmap`    | Network exploration and security auditing tool                                                  |
| `iwconfig`| Configure wireless network interfaces                                                          |
| `iw`      | More modern tool for configuring wireless network devices|

<br>

<details>
<summary>ip, ifconfig</summary>

```sh
$ ip [ OPTIONS ] OBJECT { COMMAND | help }
# Show information for all network interfaces
$ ip link
# Show information for the eth0 network interface
$ ip -s link show eth0
# Set the IP address for eth0
$ sudo ip addr add 192.168.1.7 dev eth0
$ sudo ifconfig eth0 up 192.168.1.7
# Bring eth0 down
$ sudo ip link set eth0 down
$ sudo ifconfig eth0 down
$ sudo ip link set eth0 up
$ sudo ifconfig eth0 up
# Set the MTU to 1480 bytes for eth0
$ sudo ip link set eth0 mtu 1480
# Set the networking route
$ sudo ip route add 172.16.1.0/24 via 192.168.1.5
# requests an IP address and other network configuration parameters (such as subnet mask, gateway, and DNS servers) from a DHCP server on the network.It configures the interface eth0 with the obtained dynamic IP address and settings, enabling network connectivity.
$ sudo dhclient eth0
```

</details>

<br>

> **Note:**
> Historically, the wired Ethernet network devices have been known by a name such as eth0, eth1, etc., while wireless devices have had names like wlan0, wlan1, etc.

| [Back](../README.md)|
|--------|
