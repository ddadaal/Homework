param($file)
./compile.ps1

"Compilation completed."

if ($file) {
    "Analyzing $file"
    ""
    Get-Content $file | ./test
} else {
    "Type content now"
    ""
    ./test
}
