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

% 신호만들기
m = [1,2,3,4,1,2,2,3];
disp(m)

% 심볼신호 만들기
theta_m = 2*pi*(m-1)/M + pi/4;
bbSym = cos(theta_m) + j*sin(theta_m);
bbSym_orig = bbSym;


%% RX
var = load('Ex3-2.mat', 'Ex2');
for i=1:length(var.Ex2)
    bbSym_rx(i) = var.Ex2(i);
end


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