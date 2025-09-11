# stress testing

> [!TIP]
> This is a reference to the stress tool

<details>
<summary>stress-ng</summary>

```sh
$ stress-ng --help
#Fork off 8 CPU-intensive processes, each spinning on a sqrt() calculation.
#Fork off 4 I/O-intensive processes, each spinning on sync().
#Fork off 6 memory-intensive processes, each spinning on malloc(), allocating 256 MB by default. The size can be changed as in --vm-bytes 128M.
#Run the stress test for 20 seconds.
$ stress-ng -c 8 -i 4 -m 6 -t 20s
```

</details>

<br>

| [Back](../README.md)|
|--------|
