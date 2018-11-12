param($file)
./compile.ps1

"Compilation completed."

if ($file) {
    "Analyzing $file"
    ""
    Get-Content $file | ./subc.exe
} else {
    "Type content now"
    ""
    ./subc.exe
}
