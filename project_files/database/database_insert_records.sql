-- senha:"admin"
INSERT INTO libshare.usuario (codusu, email, senha) VALUES ('1', 'admin@admin.com', '9fe76e18dd29100230680fa6c812e26c4da4b3cf48a85850910e6f4400a6b3ff9ec05190c175f592');

-- senha:"user"
INSERT INTO libshare.usuario (codusu, email, senha) VALUES ('2', 'user@user.com', '4a6e2c62e383f9c944f112388e5917caea81cecfef06cd2aa3af12d82f2dbe97ad56ea6934366ad3');

INSERT INTO libshare.permissao(CODPERMISSAO, PAPEL) VALUES (1, 'ROLE_ADMIN');
INSERT INTO libshare.permissao(CODPERMISSAO, PAPEL) VALUES (2, 'ROLE_USER');

INSERT INTO libshare.usuario_permissao (codpermissao, codusu) VALUES (1, 1);
INSERT INTO libshare.usuario_permissao (codpermissao, codusu) VALUES (2, 1);
INSERT INTO libshare.usuario_permissao (codpermissao, codusu) VALUES (2, 2);

insert into perfil (codusu, ativo, nome, SOBRENOME, ENDERECO, BAIRRO, cidade, ESTADO, PAIS, DTNASCIMENTO, NRO, COMPLEMENTO, TELEFONE, CEP)
values (1, 'S', 'Josiemerson', 'Lacerda', 'Alameda José de Oliveira Guimarães', 'Jardim Holanda', 'Uberlândia', 'MG', 'Brasil', now(), '707', 'Apt 102 bloco 9', '(34) 99147-0363', '38412234' )

insert into perfil (codusu, ativo, nome, SOBRENOME, ENDERECO, BAIRRO, cidade, ESTADO, PAIS, DTNASCIMENTO, NRO, COMPLEMENTO, TELEFONE, CEP)
values (2, 'S', 'Gabriela', 'Lacerda', 'Alameda José de Oliveira Guimarães', 'Jardim Holanda', 'Uberlândia', 'MG', 'Brasil', now(), '1138', 'Apt 102 bloco 9', '(34) 99894-2383', '38412234' )
