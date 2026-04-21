-- Seeding inicial da tabela contatos
-- ON CONFLICT DO NOTHING garante que re-execucoes nao quebram

INSERT INTO contatos (nome, telefone, email) VALUES
    ('Ana Souza',       '(11) 91234-5678', 'ana.souza@email.com')
    ON CONFLICT (nome) DO NOTHING;

INSERT INTO contatos (nome, telefone, email) VALUES
    ('Bruno Lima',      '(21) 98765-4321', 'bruno.lima@email.com')
    ON CONFLICT (nome) DO NOTHING;

INSERT INTO contatos (nome, telefone, email) VALUES
    ('Carla Mendes',    '(31) 99876-5432', 'carla.mendes@email.com')
    ON CONFLICT (nome) DO NOTHING;

INSERT INTO contatos (nome, telefone, email) VALUES
    ('Diego Ferreira',  '(41) 97654-3210', '')
    ON CONFLICT (nome) DO NOTHING;

INSERT INTO contatos (nome, telefone, email) VALUES
    ('Eduarda Costa',   '(51) 96543-2109', 'eduarda.costa@email.com')
    ON CONFLICT (nome) DO NOTHING;
