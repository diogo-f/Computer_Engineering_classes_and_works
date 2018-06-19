
echo "Compressing data to be sent to the client side node."
rm -rf SantaLife_socketTCP.zip
zip -rq SantaLife_socketTCP.zip ./**/
echo "--------------------------------"
echo "Transfering data to computers..."
echo "--------------------------------"

for i in 1 2 3 4 5 7 8 9; do
echo "computer "
sshpass -f password ssh cd0108@l040101-ws0${i}.ua.pt 'mkdir -p santaLife_64231_67828/'
sshpass -f password ssh cd0108@l040101-ws0${i}.ua.pt 'rm -rf santaLife_64231_67828/*'
sshpass -f password scp SantaLife_socketTCP.zip cd0108@l040101-ws0${i}.ua.pt:santaLife_64231_67828/
echo "Decompressing data sent to the computer."
sshpass -f password ssh cd0108@l040101-ws0${i}.ua.pt 'cd santaLife_64231_67828 ; unzip -q SantaLife_socketTCP.zip'
echo "Compiling program files at the client side node."
sshpass -f password ssh cd0108@l040101-ws0${i}.ua.pt 'cd santaLife_64231_67828 ; javac */*.java'
done

