[![Build Status](https://travis-ci.com/Carlos-Lopes1985/cartaocreditousuario.svg?token=rWDx79qT2YUj9trohyEC&branch=master)](https://travis-ci.com/github/Carlos-Lopes1985/cartaocreditousuario)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=br.com%3Acartaocredito&metric=alert_status)](https://sonarcloud.io/dashboard?id=br.com%3Acartaocredito) 

Instruções 

1- Efetuar login - 

ex:

{
    "serial": "222fffrrr60",
    "senha": "123"
}

Ao efeutuar o login, copiar o token que esta no header e colar e colar no campo authorization do hearder no postman

2-Lojas

2.1 - http://localhost:8081/lojas - GET - Busca todas as lojas cadastradas, com perfil e compras das mesmas, esse método só será permitido para quem tem o 
perfil de administrador, porém, eu comentei essa linha para melhor testar.

2.2 - http://localhost:8081/lojas/1 - GET - Buscar por ID - Retorna loja, perfil e compra.

(Preciso tratar a excessão para quando não existe a loja pesquisada) - a fazer

2.3 - http://localhost:8081/lojas - POST - salvar loja - Por favor inserir um cnpj válido...

	Validação para cnpj inválido. - OK
	(precisa tratar a excessão de quando o mesmo já existe na base)

	 {
		"nome":"Ponto Frio",
		"cnpj":"17414148000175"
	}

2.4 - http://localhost:8081/lojas/importar - POST -Importar arquivo excel
	
	Importante: Precisa mandar o caminho decodificado(https://www.4devs.com.br/url_encode_url_decode)
	
	*tratar excessão quando o usuário não digitar um caminho
	
	 {
		"caminho":"c:\\lojaimport.xls"
	}

	* O sistema vai validar caso haja algum cnpj que já esteja cadastrado e vai importar somente os válidos que não estejam cadastrados.
	
3- Usuários

	3.1- http://localhost:8081/usuarios - Buscar Todos - Usuário + cartões 

	*Retirar senha do usuário da resposta
	
	3.2 - http://localhost:8081/usuarios/17 - Buscar por ID
	
	*tratar excessão quando o usuário da busca não existir
	
	3.3 - http://localhost:8081/usuarios - POST- cadastrar Usuário 
	
	obs. Usuário só existe com cartão de crédito
	
	 {
        "idUsuario": null,
        "nome": "Carlos",
        "dataNascimento": "2020-05-05",
        "cpf": "94893694030",
        "senha": "123",
        "email": "caka1996_rj@hotmail.com",
		"bandeira": "Visa",
        "numeroCartao": "4235879000023244",
        "codSeguranca": "238",
        "limiteDisponivelTotal": 1500.0,
        "limiteDisponivelAtual": 200.0,
        "limiteDisponivelParaSaque": 400.0,
        "dataValidade": "2020-05-05",
        "vencimentoFatura": "2020-10-10"
 }
 
 4-Maquinas de Cartão de crédito
	
	4.1 - http://localhost:8081/maquinas - GET - buscar todas as maquinas(maquina - loja - compras)
	
	Obs: retirar senha da resposta
	
	4.2 - http://localhost:8081/maquinas/6 -GET - buscar maquina por id 
	
		obs: A busca por id tem uma validação onde só da para a buscar a maquina do usuário que esta logado
		
5- Compras 
	
	5.1 - http://localhost:8081/compras - GET - Buscar Todas ( compras - cartão )
	
	5.2 - http://localhost:8081/compras/24 - GET - Buscar uma compra 
	
	5.3 - http://localhost:8081/compras - POST - efetuar compra
	
	* manda email no caso de a compra ter sido efetuada com sucesso e no caso de o cartão ter saldo insuficiente

    {
    	"dataCompra": "2019-05-05",
    	"valor": "500.",
    	"serial": "222fffrrr60",
    	"numeroCartao":"4235879000023244",
    	"codseguranca":"238",
    	"senha": "123"
    }
	
6- Cartão de crédito

	6.1 - http://localhost:8081/cartaocredito - GET - buscar todos ( cartão)
	
