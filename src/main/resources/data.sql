USE `bancosimple`;

-- Insertar en beneficios
INSERT INTO beneficios (descripcion, descuento, activo) VALUES
                                                            ('Descuento en supermercados', 10.00, 1),
                                                            ('Cashback en compras online', 5.00, 1),
                                                            ('Descuento en gasolina', 7.50, 1),
                                                            ('Promoción en restaurantes', 15.00, 1),
                                                            ('Beneficio exclusivo para clientes premium', 20.00, 1);

-- Insertar en usuarios
INSERT INTO usuarios (nombre, email, password, telefono, direccion) VALUES
                                                                        ('Juan Pérez', 'juan.perez@example.com', '$2a$10$3.J0/m/xlzeAz9n5HEIeceG.rVzuLPVUvv7xm0CN4AmAxW.pmY44e', '123456789', 'Calle 123, Ciudad A'),
                                                                        ('María Gómez', 'maria.gomez@example.com', '$2a$10$3.J0/m/xlzeAz9n5HEIeceG.rVzuLPVUvv7xm0CN4AmAxW.pmY44e', '987654321', 'Avenida 456, Ciudad B'),
                                                                        ('Carlos Ruiz', 'carlos.ruiz@example.com', '$2a$10$3.J0/m/xlzeAz9n5HEIeceG.rVzuLPVUvv7xm0CN4AmAxW.pmY44e', '456123789', 'Calle 789, Ciudad C'),
                                                                        ('Ana López', 'ana.lopez@example.com', '$2a$10$3.J0/m/xlzeAz9n5HEIeceG.rVzuLPVUvv7xm0CN4AmAxW.pmY44e', '789321456', 'Plaza 159, Ciudad D'),
                                                                        ('Luis Fernández', 'luis.fernandez@example.com', '$2a$10$3.J0/m/xlzeAz9n5HEIeceG.rVzuLPVUvv7xm0CN4AmAxW.pmY44e', '321987654', 'Boulevard 753, Ciudad E');

-- Insertar en cuentas
INSERT INTO cuentas (usuario_id, numero_cuenta, tipo, saldo) VALUES
                                                                 (1, '100000000001', 'corriente', 5000.00),
                                                                 (2, '100000000002', 'ahorro', 12000.50),
                                                                 (3, '100000000003', 'corriente', 2500.75),
                                                                 (4, '100000000004', 'ahorro', 8000.00),
                                                                 (5, '100000000005', 'corriente', 9500.25);

-- Insertar en recargas
INSERT INTO recargas (cuenta_id, monto, estado) VALUES
                                                    (1, 100.00, 'completado'),
                                                    (2, 250.50, 'pendiente'),
                                                    (3, 75.25, 'completado'),
                                                    (4, 500.00, 'fallido'),
                                                    (5, 300.00, 'completado');

-- Insertar en tarjetas_virtuales
INSERT INTO tarjetas_virtuales (cuenta_id, cvv, estado, fecha_expiracion) VALUES
                                                                                                     (1,  '123', 'activa', '2027-12-31'),
                                                                                                     (2,  '456',  'activa', '2026-10-30'),
                                                                                                     (3,  '789',  'bloqueada', '2028-09-29'),
                                                                                                     (4,  '012',  'activa', '2029-08-28'),
                                                                                                     (5,  '345',  'inactiva', '2025-07-27');

-- Insertar en transacciones
INSERT INTO transacciones (cuenta_origen_id, cuenta_destino_id, monto,descripcion, estado) VALUES
                                                                                         (1, 2, 200.00,'descripcion dummie', 'completado'),
                                                                                         (2, 3, 100.50, 'descripcion dummie', 'pendiente'),
                                                                                         (3, 4, 300.75, 'descripcion dummie', 'completado'),
                                                                                         (4, 5, 50.00, 'descripcion dummie', 'fallido'),
                                                                                         (5, 1, 150.25, 'descripcion dummie', 'completado');

-- Insertar en descuentos_aplicados
INSERT INTO descuentos_aplicados (transaccion_id, beneficio_id, monto_descuento) VALUES
                                                                                     (1, 1, 20.00),
                                                                                     (2, 2, 10.50),
                                                                                     (3, 3, 15.75),
                                                                                     (4, 4, 5.00),
                                                                                     (5, 5, 30.25);