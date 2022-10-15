res = calSum(10);
res1 = checkPrime(10);
res2 = checkPrime(13);

% prob3
% Tmax=2;
% fs=100;
% t=1/fs:1/fs:Tmax;
% y=cos(2*pi*t);
% z=sin(2*pi*t);
% plot(t,y,'k.');
% hold on;
% plot(t,z,'kpentagram')
% title('cos(2pit)')
% xlabel('t')
% ylabel('value')

% prob 4
sym=3+3j;
Ts=1;
fc=1/Ts;
fs=100;
t=1/fs:1/fs:Ts;
y=real(sym)*cos(2*pi*fc*t)-imag(sym)*sin(2*pi*fc*t);
plot(t,y,'bo')