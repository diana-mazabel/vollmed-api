-- Crear una nueva columna temporal de tipo boolean
ALTER TABLE medicos ADD COLUMN activo_temp boolean;

-- Copiar los valores de la columna smallint a la nueva columna boolean
UPDATE medicos SET activo_temp = (activo = 1);

-- Verificar que todos los valores se han copiado correctamente
SELECT * FROM medicos WHERE activo IS NOT NULL AND activo_temp IS NULL;

-- Eliminar la columna original de tipo smallint
ALTER TABLE medicos DROP COLUMN activo;

-- Renombrar la columna temporal a 'activo'
ALTER TABLE medicos RENAME COLUMN activo_temp TO activo;

