clear;

% Parameter Setting
Tsym = 0.125;
Nsym = 16;
Fs = 2000;
Fc = 100;

N0 = 0.00001;
alpha=0.5;

% OFDM parameters
Nfft = 8;
p_index = [1:Nfft];

% Simulation
t = [Tsym*Nfft/Fs : Tsym*Nfft/Fs : Tsym*Nsym];
Tmax = length(t);

% channel delay:tau
tau = 0.001;

% Symbol 생성
M=4;
symTable = zeros(1,4);
for i = 1:M
    i_m = 2*pi*(i-1)/M + pi/4;
    symTable(i) = cos(i_m) + j*sin(i_m);
end

% Basis Signal 생성
phi1 = cos(2*pi*Fc*t(1:Tsym*Fs));
Es = norm(phi1);
phi1 = phi1/Es;

phi2 = -sin(2*pi*Fc*t(1:Tsym*Fs));
Es = norm(phi2);
phi2 = phi2/Es;

%% TX

% 랜덤신호만들기
m = [1,2,3,4,1,2,2,3];
disp(m)

% 심볼신호 만들기
theta_m = 2*pi*(m-1)/M + pi/4;
bbSym = cos(theta_m) + j*sin(theta_m);
bbSym_orig = bbSym;

reconstFilter = rcosdesign(alpha, 5, Tsym*Fs/5);

%After upconversion
var = load('Ex3-1.mat', 'Ex1');
for i=1:length(var.Ex1)
    RFsignal(i) = var.Ex1(i);
end

%% RX
% Coherent Detection
Ich = RFsignal .* cos(2*pi*(Fc)*t)/Es;
Qch = RFsignal .* sin(2*pi*(Fc)*t)/Es;

% matched filter
Ich_m = conv(Ich,reconstFilter);
Qch_m = conv(Qch,reconstFilter);

% Baseband Signal Representation
for i = 1:Nsym
    n_index = i*Tsym*Fs;
    bbSym_rx(i) = Ich_m(n_index) - Qch_m(n_index)*j;
end

% Noise Insertion
noise = sqrt(N0)*randn(1,length(bbSym_rx)) + j*sqrt(N0)*randn(1,length(bbSym_rx));
bbSym_rx = bbSym_rx+noise;

% OFDM demodulation
if Nfft>0
    bbSymT_rx = bbSym_rx;
    bbSym_rx = [];
    for i = 1:Nsym/Nfft
        input = bbSymT_rx(Nfft*(i-1)+1:Nfft*i);
        bbSym_rx = [bbSym_rx fft(input,Nfft)];
    end
end

% one-tap equalization
for i = 1:Nsym/Nfft
    if i==1
        OFDMsym = bbSym_rx(1:8);
        h(p_index) = conj(bbSym_orig(p_index)).*OFDMsym(p_index);
        phase_diff = angle(h);
    end
    bbSym_rx(Nfft*(i-1)+1:Nfft*i) = bbSym_rx(Nfft*(i-1)+1:Nfft*i).*exp(-j*phase_diff);
end

% Optimal Receiver
Sym_orig = zeros(1,Nsym);
for i= 1:Nsym
    corr_result = bbSym_rx(i)*conj(symTable);
    [dammyVal hd_index] = max(real(corr_result));
    Sym_orig(i) = hd_index;
end
disp(Sym_orig)
