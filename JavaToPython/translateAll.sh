echo "" > output.txt
for filename in Translate/*.java; do
    [ -e "$filename" ] || continue
    Output=$(txl $filename umpleJavaToPython.txl > ${filename%%.*}".py" -q 2>> output.txt)
done
echo "Finished translating files!"
read
