# file and text manipulations

> [!TIP]
> These are useful commands when doing file or text manipulations

<details>
<summary>sed</summary>

```sh
# substitute first instances of a word ORIGINAL with the word REPLACEMENT in the specified file and output to a new file
$ sed s/ORIGINAL/REPLACEMENT/ file > newfile
$ sed s/ORIGINAL/REPLACEMENT/ < file > newfile
# same thing from the output stream of cat or echo
$ cat file | sed s/ORIGINAL/REPLACEMENT/ > newfile
$ echo $ENVVARIABLE | sed s/"ORIGINAL"/"REPLACEMENT"/g
$ echo ORIGINAL | sed s/"ORIGINAL"/"REPLACEMENT"/g
# substitute globally, all instead of first instance
$ sed s/ORIGINAL/REPLACEMENT/g file > newfile
# same thing using another delimiter
$ sed s:ORIGINAL:REPLACEMENT:g file > newfile
# escaping to replace '\' with '/'
$ sed s/'\\'/'\/'/g file > newfile
# multiple replacements 
$ sed -e s/"ORIGINAL1"/"REPLACEMENT1"/g -e s/"ORIGINAL2"/"REPLACEMENT2"/g < file > newfile
# using a scriptfile containing multiple patterns per line e.g.:
# s/"ORIGINAL1"/"REPLACEMENT1"/g
# s/"ORIGINAL2"/"REPLACEMENT2"/g
$ sed -f scriptfile < file > newfile
# search in a given range or lines 1-3
$ sed 1,3s/ORIGINAL/REPLACEMENT/g file
# apply changes in the same file
$ sed -i s/ORIGINAL/REPLACEMENT/g file
```

</details>

<details>
<summary>awk</summary>

```sh
$ awk ‘command’  file
$ awk -f scriptfile file
# print entire file
$ awk '{ print $0 }' /etc/passwd
# print first field (column) of every line, separated by a space
$ awk -F: '{ print $1 }' /etc/passwd
# print first and seventh field of every line
$ awk -F: '{ print $1 $7 }' /etc/passwd
```

</details>

<details>
<summary>cat</summary>

```sh
$ cat <filename>
# same but outputs lines in reverse order
$ tac <filename>
# concatenates given files and outputs into a new file
$ cat file1 file2 > file3
# concatenates given files and appends to the end of an existing file
$ cat file1 file2 >> existingfile
# anything that gets typed next will be outputted into a new file, until CTRL-D
$ cat > file
# appends to the end of an existing file
$ cat >> existingfile
# same thing but expects 'EOF'/'STOP' to be typed in the begining of a new line to exit
$ cat > file << EOF
$ cat << STOP > file
$ cat > file << STOP
$ cat << STOP > file
```

</details>

<details>
<summary>echo</summary>

```sh
# using \n for new line and \t for a tab
$ echo string
$ echo string > newfile
$ echo string >> existingfile
$ echo $variable
```

</details>

## dealing with large files

<details>
<summary>less</summary>

```sh
# j or Down arrow: Move down one line
# k or Up arrow: Move up one line
# Space or CTRL+F: Move forward one full screen
# b or CTRL+B: Move backward one full screen
# CTRL+D: Move forward half a screen
# CTRL+U: Move backward half a screen
# g: Go to the beginning of the file
# G: Go to the end of the file
# 10g: Go to the 10th line (replace 10 with desired line number)
# /pattern: Search forward for "pattern"
# ?pattern: Search backward for "pattern"
# n: Go to next search match
# N: Go to previous search match
# q: Quit less
# m<char>: Mark the current position with a character
# ' <char>: Return to marked position

$ less somefile
$ cat somefile | less
```

</details>

<details>
<summary>head</summary>

```sh
# print first 5 lines of a given file
$ head –n 5 /PATH/TO/FILE
$ head 5 /PATH/TO/FILE
```

</details>

<details>
<summary>tail</summary>

```sh
# print last 15 lines of a given file
$ tail -n 15 /PATH/TO/FILE
# monitor file changes on a given file that is expected to grow
$ tail -f /PATH/TO/FILE
```

</details>

### dealing with compressed files

<details>
<summary>z</summary>

```sh
$ zcat compressed-file.txt.gz
# page through content of a compressed file
$ zless somefile.gz
$ zmore somefile.gz
# search inside
$ zgrep -i less somefile.gz
# compare
$ zdiff file1.txt.gz file2.txt.gz
```

</details>

## utilities

<details>
<summary>sort</summary>

```sh
$ sort file
# sort in reverse order
$ sort -r file
# use the third character in each line to sort instead of starting
$ sort -k 3 file
$ cat file1 file2 | sort
# sorts and removed duplicate consecutive lines
$ sort -u file
```

</details>

<details>
<summary>uniq</summary>

```sh
# removes duplicate consecutive lines
$ sort file1 file2 | uniq > file3
$ sort -u file1 file2 > file3
# count duplicates
$ uniq -c filename
```

</details>

<details>
<summary>paste</summary>

```sh
# paste line of file 1 and line of file 2 into a new line
$ paste file1 file2
# same but defining a delimeter
$ paste -d, file1 file2
$ paste -d ':' file1 file2
```

</details>

<details>
<summary>join</summary>

```sh
# join file1 and file2 on a common first field
# file1 = A XX GG
# file2 = A KK PP
# result = A XX GG KK PP
$ join file1 file2
```

</details>

<details>
<summary>split</summary>

```sh
# splits the given file in 100 equal sized segments named after splitFileName[suffix]
$ split file splitFileName
# the suffix of each segment shall take N charaters
$ split -a N file splitFileName
# the suffix of each segment shall be numeric
$ split -d file splitFileName
```

</details>

<details>
<summary>strings</summary>

```sh
# prints all printable character strings within a *binary* file 
$ strings book1.xls | grep TEXT
```

</details>

<details>
<summary>tr</summary>

```sh
$ tr [options] set1 [set2]
# translates lowercase into uppercase 
$ tr abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ
# translates braces into parenthesis 
$ tr '{}' '()' < inputfile > outputfile
# translate white-space to tabs
$ tr [:space:] '\t' < inputfile > outputfile
# squeeze repetition of characters which replaces each sequence of a repeated character in the specified set with a single occurrence of that character.
$ tr -s [:space:] < inputfile > outputfile
# delete specified characters 
$ tr -d 't' < inputfile > outputfile
# c complements/selects d deletes
# deletes all characters NON digits from the input stream.
$ tr -cd [:digit:] < inputfile > outputfile
# deletes all digits from the input stream.
$ tr -d [:digit:] < inputfile > outputfile
# remove all non-printable character from a file
$ tr -cd [:print:] < file.txt
# join all the lines in a file into a single line
$ tr -s '\n' ' ' < file.txt
```

</details>

<details>
<summary>tee</summary>

```sh
# prints out the result from the 'ls' command and also writes it to a newfile
$ ls -l | tee newfile
```

</details>

<details>
<summary>wc</summary>

```sh
$ wc -l filename
$ wc -c filename
$ wc -w filename
```

</details>

<details>
<summary>cut</summary>

```sh
# print out display the third column on a column-based file delimited by a blank space, index starts at 1
$ ls -l | cut -d " " -f3
```

</details>

| [Back](../README.md)|
|--------|
