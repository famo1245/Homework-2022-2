function Dec(N)

%
%   Get input by String
%

% original symbol
sym_origin = [0 0; 0 1; 1 0; 1 1];

% generator matrix
G = [1 0 1 0 0;
     0 1 1 1 1];

% codeword
C = [];

for i=1:size(sym_origin, 1)
    bit1 = [];
    for j=1:size(G, 2)
        bit1 = [bit1 sym_origin(i,1) * G(1, j)];
    end
    bit2 = [];
    for k=1:size(G, 2)
        bit2 = [bit2 sym_origin(i,2) * G(2, k)];
    end
    C = [C; bitxor(bit1, bit2)];
end


% input
Input = [];
for i=1:5:length(N)
    Input = [Input; str2num(N(i)) str2num(N(i+1)) str2num(N(i+2)) str2num(N(i+3)) str2num(N(i+4))];
end

% hard-decision
decoded_sym = [];
for i=1:size(Input, 1)
    distance = [];
    
    for j=1:size(C, 1)
        dist = 0;
        
        for k=1:size(Input, 2)
            dist = dist + abs(Input(i,k) - C(j,k));
        end

        distance = [distance dist];
    end

    [dummyVal hd_index] = min(distance);
    decoded_sym = [decoded_sym sym_origin(hd_index, :)];
end

disp(decoded_sym)