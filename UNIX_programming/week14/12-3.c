#include <netdb.h>
#include <stdio.h>

int main() {
	struct servent *port;
	int n;

	setservent(0);

	for (n = 0; n < 5; n++) {
		port = getservent();
		printf("Name = %s, Port = %d\n", port->s_name, ntohs(port->s_port));
	}

	endservent();
}
// NBO로 저장된 port number를 HBO(Host Byte Order)로 변환
// ntohs()함수 사용 16바이트
