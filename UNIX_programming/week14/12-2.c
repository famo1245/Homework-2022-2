#include <netdb.h>
#include <stdio.h>

int main() {
	struct servent *port;
	int n;

	setservent(0);

	for (n = 0; n < 5; n++) {
		port = getservent();
		printf("Name = %s, Port = %d\n", port->s_name, port->s_port);
	}

	endservent();
}

// linux에서는 포트 번호를 빅엔디언, NBO(Network Byte Order)로 저장하므로
// 포트 번호가 이상하게 출력됨
