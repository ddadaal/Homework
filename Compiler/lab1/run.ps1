param($file)
./compile.ps1

"Compilation completed."

if ($file) {
    Get-Content $file | ./test
} else {
    "Type content now"
    ./test
}
