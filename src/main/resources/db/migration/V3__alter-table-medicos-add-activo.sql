-- 1. Agregar la columna 'activo' con tipo smallint
ALTER TABLE medicos
ADD COLUMN activo smallint;

-- 2. Actualizar los valores existentes en la columna
UPDATE medicos
SET activo = 1;
