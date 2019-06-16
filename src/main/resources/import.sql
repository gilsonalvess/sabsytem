INSERT INTO public.usuario (id, desativado, email, password, role, username) VALUES (99, false, 'useradmin@email.com', '$2a$10$XO17AsZE6pi4CgTVYhdPHeUCKqyxXoGrDrhPh6UjorPwLizO.gxGa', 'ROLE_ADMIN', 'admin');
INSERT INTO public.cliente (id, fone, nome, email, sexo) VALUES (99, '(99) 99999-9999', 'Cliente Teste', 'email@email.com', 'Masculino');
INSERT INTO public.agendamento (id, data_agendamento, hora_agendamento, status, cliente_id) VALUES (99, current_date, now() - interval '0.25 hour', 'Confirmado', 99);
INSERT INTO public.agendamento (id, data_agendamento, hora_agendamento, status, cliente_id) VALUES (98, current_date, now() + interval '0.02 hour', 'A confirmar', 99);
INSERT INTO public.agendamento (id, data_agendamento, hora_agendamento, status, cliente_id) VALUES (97, current_date, now() + interval '0.01 hour', 'Confirmado', 99);
INSERT INTO public.agendamento (id, data_agendamento, hora_agendamento, status, cliente_id) VALUES (96, current_date, now() - interval '1 hour', 'Cancelado', 99);


