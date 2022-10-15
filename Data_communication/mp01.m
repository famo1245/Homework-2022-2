% mp01
% 1번점
sym=3+3j;
Ts=1;
fc=1/Ts;
fs=20;
t=1/fs:1/fs:Ts;
y1=real(sym)*cos(2*pi*fc*t)-imag(sym)*sin(2*pi*fc*t);

% 2번점
sym2=1+1j;
y2=real(sym2)*cos(2*pi*fc*t)-imag(sym2)*sin(2*pi*fc*t);

% 3번점
sym3=-3-3j;
y3=real(sym3)*cos(2*pi*fc*t)-imag(sym3)*sin(2*pi*fc*t);
% plot(t,y1,'bo',t,y2,'ro',t,y3,'go')
figure(3);
plot(t,y1,'ro');
hold on;
plot(t,y2, 'bo');
hold on;
plot(t,y3, 'go');