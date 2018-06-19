echo "trying to execute program at the computers."

lm = $(hostname)

while read runFiles hostName port

if [[ $hostName = $lm ]]
then
	java $runFiles &
fi
done

echo "all done, server shutdown"
