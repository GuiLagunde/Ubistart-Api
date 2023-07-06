INSERT INTO usuario (id_usuario, senha, email, usuario_role, data_criacao, data_atualizacao)
SELECT 1, '$2a$12$q6MWUU39H7.w/j/XDR5z.O9LNUF7F.O5Fwa6ibZ8Cnxapbgkqx6rq', 'admin@admin.com', 'ADMIN', current_timestamp, current_timestamp
        WHERE NOT EXISTS (SELECT 1 FROM usuario WHERE email = 'admin@admin.com');