# search

> [!TIP]
> These are useful commands when searching

<details>
<summary>find</summary>

```sh
$ find [location] [criteria] [actions]
$ find /folder -name "*.EXTENSION"
# find and do an ls on whats found
$ find /folder -name "*.EXTENSION" -ls
# with the given EXTENSION or newer than specified file
$ find /folder1 /folder2 -name "*.EXTENSION" -or -newer /folder1/file -ls
# in the current folder find all files with a given SUFFIX and execute the command 'rm'. {} is the placeholder for whats found
$ find . -name "*SUFFIX" -exec rm {} ';'
# same result using pipe '|' that takes the output and adds to the command executed by xargs 'rm'
$ find . -name "*SUFFIX" | xargs rm
# same result using inline scripting for/do/done
$ for names in $(find . -name "*SUFFIX" ) ; do rm $names ; done
# same result but avoiding potential issues with blank spaces in file names
$ find . -name "*SUFFIX" -print0 | xargs -0 rm
$ find . -name "*SUFFIX" -print0 -exec rm {} ';'
```

</details>

<details>
<summary>locate</summary>

```sh
# go through entire file system
$ locate .extension
```

</details>

</details>

<details>
<summary>grep</summary>

```sh
# search KEYWORD in the specified file
$ $ grep KEYWORD file
# ignoring case find multiple KEYWORDs recursively in the current directory
$ grep -i -e KEYWORD1 -e KEYWORD2 -r .
# filter lines that start with given PREFIX
$ grep "^PREFIX" file
# filter lines that end with given SUFFIX
$ grep "SUFFIX$" file
# filter lines with a 'd' followed by another character within the alphabetical range 'a' and 'z'
$ grep d[a-z] file
# prints all lines that match given PATTERN
$ grep [PATTERN] file
# prints all lines that do NOT match given PATTERN
$ grep -v [PATTERN] file
# prints lines that contain numbers from 0 to 9
$ grep [0-9] file
# prints matching lines with context: 3 lines before and after
$ grep -C 3 [pattern] file
```

## options

| Option | Meaning                                                                   |
|--------|---------------------------------------------------------------------------|
| -i     | Ignore case                                                              |
| -v     | Invert match                                                             |
| -n     | Print line number                                                        |
| -H     | Print filename                                                          |
| -a     | Treat binary files as text                                              |
| -I     | Ignore binary files                                                     |
| -r     | Recurse through subdirectories                                        |
| -l     | Print out names of all files that contain matches              |
| -L     | Print out names of all files that do not contain matches    |
| -c     | Print out number of matching lines only                      |
| -e     | Use the following pattern; useful for multiple strings and special characters |

</details>

## patterns

| Search Pattern | Meaning                 |
|----------------|-------------------------|
| . (dot)        | Match any single character |
| a\|z            | Match 'a' or 'z'          |
| $              | Match end of a line       |
| ^              | Match beginning of a line |
| *              | Match preceding item 0 or more times |

e.g.: "the quick brown fox jumped over the lazy dog"

| Command   | Usage                |
|-----------|----------------------|
| a..       | matches azy          |
| b\|j.     | matches both br and ju |
| ..$       | matches og           |
| l.*       | matches lazy dog     |
| l.*y      | matches lazy         |
| the.*     | matches the whole sentence |

| [Back](../README.md)|
|--------|
