# Files

> [!TIP]
> This is a reference to the operations on files

<details>
<summary>file </summary>

```sh
# ascertain file types
$ file *
```

</details>

<details>
<summary>ls </summary>

```sh
$ ls -l a_file

# -rw-rw-r-- 1 coop aproject 1601 Mar 9 15:04 a_file
```

<br> 

> [!TIP] how to read the listing result
> <br> file type character<br>
> <br> owner: the user who owns the file; also called user
> <br> group: the group of users who have access
> <br> world: the rest of the world; also called other.<br>
> <br> r: read access is allowed
> <br> w: write access is allowed
> <br> x: execute access is allowed.

<br>

## basic file types

| Character | Type                    |
|-----------|-------------------------|
| -         | Normal file             |
| d         | Directory               |
| l         | Symbolic link           |
| p         | Pipe (FIFO special file)|
| s         | Unix domain socket      |
| b         | Block device node       |
| c         | Character device node   |

</details>

<details>
<summary>chmod </summary>

```sh
$ ls -l somefile
# -rw-rw-r-- 1 coop coop 1601 Mar 9 15:04 somefile
# give the owner and world execute permission, and remove the group write permission
# where u stands for user (owner), o stands for other (world), and g stands for group.
$ chmod uo+x,g-w somefile
$ chmod 755 somefile
```

<br>

> [!TIP] using digits
> <br> 4 if read permission is desired
> <br> 2 if write permission is desired
> <br> 1 if execute permission is desired.

</details>

<details>
<summary>chgrp</summary>

```sh
$ chgrp newgroupowner somefile
```

</details>

<details>
<summary>chown</summary>

```sh
# -R for recursive
$ chown newowner a_file
# separate the owner and the group with a period
$ chown newowner.newgroupowner somefile
```

</details>

<details>
<summary>umask</summary>

> [!TIP] umask
> The umask command controls the default permissions (read, write, execute) for newly created files and directories by "masking" out permissions you do not want set.

```sh
$ umask 022    # Default for most systems; files: 644, directories: 755
$ umask 027    # More restrictive; files: 640, directories: 750
$ umask 077    # Most restrictive; files: 600, directories: 700
$ umask u=rwx,g=rx,o=

```

```sh
If umask is 022 (octal):
Default file permission: 666
Umask: 022
File permission: 
666−022=644
666−022=644 (rw-r--r--)

If umask is 027:
Default directory permission: 777
Umask: 027
Directory permission: 
777−027=750
777−027=750 (rwxr-x---)
```


| Digit | Meaning                                  |
|-------|------------------------------------------|
| 0     | Allow all permissions (read, write, execute)    |
| 1     | Remove execute                                  |
| 2     | Remove write                                    |
| 3     | Remove write and execute                        |
| 4     | Remove read                                     |
| 5     | Remove read and execute                         |
| 6     | Remove read and write                           |
| 7     | Remove all (read, write, execute)               |

</details>

<br>

| [Back](../README.md)|
|--------|
