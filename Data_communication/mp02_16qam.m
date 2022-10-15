clear;

Tsym = 1;
Nsym = 1000;
Fs = 100;
Fc = 10;
N0 = 0.15;

t = [Tsym/Fs:Tsym/Fs:Tsym*Nsym];
Tmax = length(t);

% symbol table 생성 16QAM
M = 16;
for i=1:M
    realSym = -5+2*(mod(i-1,4)+1);
    imagSym = -5+2*(floor((i-1)/4)+1);
    symTable(i) = realSym + j*imagSym;
end

% basis representation
phi1 = cos(2*pi*Fc*t(1:Tsym*Fs)); % 한 주기동안 carrier
Es = norm(phi1); % find orthonoraml basis
phi1 = phi1/Es;

phi2 = -sin(2*pi*Fc*t(1:Tsym*Fs)); % 한 주기동안 carrier
Es = norm(phi2); % find orthonoraml basis
phi2 = phi2/Es;

%%%%%% Tx
m = randi(M, 1, Nsym);

% Symbol signal generation
realSymR = -5+2*(mod(m-1,4)+1);
imagSymR = -5+2*(floor((m-1)/4)+1);
bbSym = realSymR + j*imagSymR; % Si(t)+jSq(t)

% up conversion
RFsignal = zeros(1, Tmax);
for iterT = 1:Tmax
    iterSym = floor((iterT-1)/Fs)+1; % #of symbol
    RFsignal(iterT) = real(bbSym(iterSym)) * cos(2*pi*Fc*t(iterT)) / Es - imag(bbSym(iterSym)) * sin(2*pi*Fc*t(iterT)) / Es;
end

%%%%% Rx
% Down conversion (Coherent detection)
Ich = RFsignal .* cos(2*pi*Fc*t)/Es;
Qch = RFsignal .* sin(2*pi*Fc*t)/Es; % Es로 나누는건 normalization

for i = 1:Nsym
    n_start = (i-1)*Tsym*Fs;
    bbSym_rx(i) = sum(Ich(n_start+1:n_start+Tsym*Fs) - j*Qch(n_start+1:n_start+Tsym*Fs)); %optimal receiver
end
sigPower = mean(abs(bbSym_rx).^2);

%%%%%%% noise 추가
noise = sqrt(N0)*randn(1,length(bbSym_rx)) + j*sqrt(N0)*randn(1,length(bbSym_rx));
bbSymN_rx = bbSym_rx + noise;
noisePower=mean(abs(noise).^2);
SNR=10*log10(sigPower/noisePower)
figure(3)
scatter(real(bbSymN_rx), imag(bbSymN_rx));
grid on;
axis([-4 4 -4 4]);

% hard decision
hd_bbSym = zeros(1, Nsym);
for i = 1:Nsym
    mod_dis_metric = bbSymN_rx(i)*conj(symTable) - 1/2*abs(symTable).^2;
    [dammyVal, hd_index] = max(real(mod_dis_metric));
    hd_bbSym(i) = symTable(hd_index);
end

SER = sum(abs(hd_bbSym - bbSym) > 0.01) / Nsym