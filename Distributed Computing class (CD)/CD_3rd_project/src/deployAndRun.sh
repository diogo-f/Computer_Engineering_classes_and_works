for i in 1 2 3 4 5 7 8 9
do
	sshpass -f password scp config cd0108@l040101-ws0${i}.ua.pt:santaLife_64231_67828 ;
	xterm  -T "computer $i" -hold -e "sshpass -f password ssh cd0108@l040101-ws0${i}.ua.pt 'cd santaLife_64231_67828 ; ./Run.sh < config'" &
done
