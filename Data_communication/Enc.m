function Enc(N)

%
%   Get input by String
%

% Generator
G = [1 0 1 0 0;
     0 1 1 1 1];

% Processing input
Input = [];
for i=1:2:length(N)
    Input = [Input; str2num(N(i)) str2num(N(i+1))];
end

% make codeword
C = [];
for i=1:size(Input, 1)
    bit1 = [];
    for j=1:size(G, 2)
        bit1 = [bit1 Input(i,1) * G(1, j)];
    end
    bit2 = [];
    for k=1:size(G, 2)
        bit2 = [bit2 Input(i,2) * G(2, k)];
    end
    C = [C bitxor(bit1, bit2)];
end

disp(C)