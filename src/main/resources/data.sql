INSERT INTO congregacao (nome) VALUES
('Nova Olinda 1'),
('Nova Olinda 2'),
('Nova Olinda 3'),
('Esperança 1'),
('Esperança 2'),
('Aguazinha 2'),
('Aguazinha 3')
ON CONFLICT DO NOTHING;

INSERT INTO maestro (nome) VALUES
('Matheus'),
('Whashiton'),
('Alisson')
ON CONFLICT DO NOTHING;