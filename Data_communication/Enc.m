function result = Enc(N)

G = [1 0 1 0 0;
     0 1 1 1 1];
Input = [];
for i=1:2:length(N)
    Input = [Input; str2num(N(i)) str2num(N(i+1))];
end

C = [];
for i=1:length(Input(:,1))
    bit1 = [];
    for j=1:length(G(1,:))
        bit1 = [bit1 Input(i,1) * G(1, j)];
    end
    bit2 = [];
    for k=1:length(G(2,:))
        bit2 = [bit2 Input(i,2) * G(2, k)];
    end
    C = [C bitxor(bit1, bit2)];
end

result = C;