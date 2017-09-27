BEGIN;

INSERT INTO `sys_role`
VALUES
('1', 'ROLE_ADMIN'),
('2', 'ROLE_USER');

INSERT INTO `sys_user`
VALUES
('1', '$2a$10$NYw3YvE.XvF.hgMHfAoLo.KtMT6KpWKxNtoUhFZbM2zNuUyiJWcp2', 'gwf'),
('2', '$2a$10$NYw3YvE.XvF.hgMHfAoLo.KtMT6KpWKxNtoUhFZbM2zNuUyiJWcp2', 'jd'),
('3', '$2a$10$NYw3YvE.XvF.hgMHfAoLo.KtMT6KpWKxNtoUhFZbM2zNuUyiJWcp2', 'gzf');

INSERT INTO `sys_user_roles`
VALUES
('1', '1', '1');

COMMIT;