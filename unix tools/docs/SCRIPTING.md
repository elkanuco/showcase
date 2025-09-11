# scripting

> [!TIP]
> These are useful bits when scripting

<details>
<summary>conditionals</summary>

```sh
​#!/bin/bash
​if [[ -f file.c ]] ; then ... ; fi
if [ -f file.c ] ; then ... ; fi #deprecated
if test -f file.c ; then ... ; fi #deprecated
​if [ "$VAR" == "" ] 
```

> [!TIP]
> The && (ANDs) in the first statement say "stop" as soon as one of the commands fails. It is often preferable to using the ; operator.
> On the other hand, the || ' (ORs) in the second statement say "stop" as soon as one of the commands succeeds.

## file conditional options

| Test     | Meaning                  |
|----------|--------------------------|
| -e file  | file exists?             |
| -d file  | file is a directory?     |
| -f file  | file is a regular file?  |
| -s file  | file has non-zero size?  |
| -g file  | file has sgid set?       |
| -u file  | file has suid set?       |
| -r file  | file is readable?        |
| -w file  | file is writeable?       |
| -x file  | file is executable?      |

## string conditional options

| Test         | Meaning            |
|--------------|--------------------|
| string       | string not empty?   |
| string1 == string2 | strings same?  |
| string1 != string2 | strings differ?|
| -n string    | string not null?    |
| -z string    | string null?        |

> [!TIP]
> Arithmetic comparisons take the form: exp1 -op exp2 where the operation (-op) can be: -eq, -ne, -gt, -ge, -lt, -le

<br>

> **Note:**
> Any condition can be negated by the use of !
</details>

<details>
<summary>case</summary>

```sh
#!/bin/sh

echo "Do you want to destroy your entire file system?"
read response

case "$response" in
"yes") echo "I hope you know what you are doing!" ;;
"no" ) echo "You have some comon sense!" ;;
"y" | "Y" | "YES" ) echo "I hope you know what you are doing!";;
"n" | "N" | "NO" ) echo "You have some comon sense!" ;;
* ) echo "You have to give an answer!" ;;
esac
exit 0
```

</details>

<details>
<summary>loops</summary>

```sh
#!/bin/sh
for i in 1 2 3 4 5
do
  echo "Number $i"
done
```

```sh
#!/bin/sh
while [ $count -le 5 ]
do
  echo "Count is $count"
  count=$((count + 1))
done

```

</details>

<details>
<summary>functions</summary>

```sh
#!/bin/sh

fun_foobar(){
#.. statements
}

function fun_foobar(){
#.. statements
}

function fun_foobar{
#.. statements
}

```

</details>

<br>

> **Note:**
> ```. file``` and ```source file``` can be used to include script inside another script

## special environment variables that can be used inside a script

| Variable | Description                                      |
|----------|------------------------------------------------|
| $0       | The command name                                |
| $1 $2 ...| The command arguments                           |
| $*       | Represents all arguments as a single word      |
| $@       | Represents all arguments, preserving quoted grouping |
| $#       | The number of arguments |
| $?       | The exit status of the previous command |

> **Note:**
> ```shift``` or ```shif N``` can be used to shift the arguments N (1 by default) positions to the left

## options that can be used for debugging purposes                      |

| Command  | Description                                                        |
|----------|--------------------------------------------------------------------|
| set -n   | Just checks the syntax without running the script (bash -n)       |
| set -x   | Echoes all commands after running them (bash -x)                  |
| set -v   | Echoes all commands before running them (bash -v)                 |
| set -u   | Treats use of unset variables as an error (bash -u)               |
| set -e   | Causes the script to exit immediately upon any non-zero exit status (bash -e) |

| [Back](../README.md)|
|--------|
