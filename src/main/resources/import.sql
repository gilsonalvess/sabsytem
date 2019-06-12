INSERT INTO public.usuario (id, desativado, email, password, role, username) VALUES (99, false, 'useradmin@email.com', '$2a$10$XO17AsZE6pi4CgTVYhdPHeUCKqyxXoGrDrhPh6UjorPwLizO.gxGa', 'ROLE_ADMIN', 'admin');
INSERT INTO public.cliente (id, fone, nome, email, sexo) VALUES (99, '32154', 'Cliente Teste', 'email@email.com', 'Masculino');
INSERT INTO public.agendamento (id, data_agendamento, hora_agendamento, status, cliente_id) VALUES (99, '2019-06-12', '2019-06-12 00:11:14.982000', 'Confirmado', 99);


