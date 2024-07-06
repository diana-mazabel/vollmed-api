-- 1. Agregar la columna sin la restricción NOT NULL
ALTER TABLE medicos
ADD COLUMN telefono VARCHAR(20);

-- 2. Actualizar los valores existentes en la columna
UPDATE medicos
SET telefono = 'N/A';  -- O cualquier otro valor predeterminado

-- 3. Agregar la restricción NOT NULL a la columna
ALTER TABLE medicos
ALTER COLUMN telefono SET NOT NULL;

