function result=checkPrime(num)
result=1;
for i=2:num-1
    if mod(num, i)==0
        result=0;
    end
end