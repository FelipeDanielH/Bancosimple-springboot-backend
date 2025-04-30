CREATE DATABASE IF NOT EXISTS `BancoSimple_Completo` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `BancoSimple_Completo`;

-- Eliminar todas las tablas en orden de dependencias
DROP TABLE IF EXISTS `descuentos_aplicados`;
DROP TABLE IF EXISTS `auditoria_transacciones`;
DROP TABLE IF EXISTS `transacciones`;
DROP TABLE IF EXISTS `tarjetas_virtuales`;
DROP TABLE IF EXISTS `recargas`;
DROP TABLE IF EXISTS `cuentas`;
DROP TABLE IF EXISTS `usuarios`;
DROP TABLE IF EXISTS `beneficios`;

-- bancocompleto.beneficios definition

CREATE TABLE `beneficios` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `descripcion` text NOT NULL,
                              `descuento` decimal(5,2) DEFAULT NULL,
                              `activo` tinyint(1) DEFAULT '1',
                              PRIMARY KEY (`id`),
                              CONSTRAINT `beneficios_chk_1` CHECK ((`descuento` between 0 and 100))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- bancocompleto.usuarios definition

CREATE TABLE `usuarios` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `nombre` varchar(100) NOT NULL,
                            `email` varchar(150) NOT NULL,
                            `password` varchar(255) NOT NULL,
                            `telefono` varchar(20) DEFAULT NULL,
                            `direccion` text,
                            `fecha_registro` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- bancocompleto.cuentas definition

CREATE TABLE `cuentas` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `usuario_id` int NOT NULL,
                           `numero_cuenta` varchar(20) NOT NULL,
                           `tipo` enum('corriente','ahorro') NOT NULL DEFAULT 'corriente',
                           `saldo` decimal(15,2) NOT NULL,
                           `fecha_creacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `numero_cuenta` (`numero_cuenta`),
                           KEY `usuario_id` (`usuario_id`),
                           CONSTRAINT `cuentas_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE,
                           CONSTRAINT `cuentas_chk_1` CHECK ((`saldo` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- bancocompleto.recargas definition

CREATE TABLE `recargas` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `cuenta_id` int NOT NULL,
                            `monto` decimal(15,2) NOT NULL,
                            `estado` enum('pendiente','completado','fallido') DEFAULT 'pendiente',
                            `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                            PRIMARY KEY (`id`),
                            KEY `cuenta_id` (`cuenta_id`),
                            CONSTRAINT `recargas_ibfk_1` FOREIGN KEY (`cuenta_id`) REFERENCES `cuentas` (`id`) ON DELETE CASCADE,
                            CONSTRAINT `recargas_chk_1` CHECK ((`monto` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- bancocompleto.tarjetas_virtuales definition

CREATE TABLE `tarjetas_virtuales` (
                                      `id` int NOT NULL AUTO_INCREMENT,
                                      `cuenta_id` int NOT NULL,
                                      `cvv` varchar(4) NOT NULL,
                                      `estado` enum('activa','inactiva','bloqueada') DEFAULT 'activa',
                                      `fecha_creacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                      `fecha_expiracion` date NOT NULL,
                                      PRIMARY KEY (`id`),
                                      KEY `cuenta_id` (`cuenta_id`),
                                      CONSTRAINT `tarjetas_virtuales_ibfk_1` FOREIGN KEY (`cuenta_id`) REFERENCES `cuentas` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- bancocompleto.transacciones definition

CREATE TABLE `transacciones` (
                                 `id` int NOT NULL AUTO_INCREMENT,
                                 `cuenta_origen_id` int NOT NULL,
                                 `cuenta_destino_id` int NOT NULL,
                                 `monto` decimal(15,2) NOT NULL,
                                 `descripcion` varchar(100) NOT NULL,
                                 `tipo` enum('in','out') NOT NULL,
                                 `estado` enum('pendiente','completado','fallido') DEFAULT 'pendiente',
                                 `fecha_transaccion` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                 PRIMARY KEY (`id`),
                                 KEY `cuenta_origen_id` (`cuenta_origen_id`),
                                 KEY `cuenta_destino_id` (`cuenta_destino_id`),
                                 CONSTRAINT `transacciones_ibfk_1` FOREIGN KEY (`cuenta_origen_id`) REFERENCES `cuentas` (`id`) ON DELETE CASCADE,
                                 CONSTRAINT `transacciones_ibfk_2` FOREIGN KEY (`cuenta_destino_id`) REFERENCES `cuentas` (`id`) ON DELETE CASCADE,
                                 CONSTRAINT `transacciones_chk_1` CHECK ((`monto` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- bancocompleto.auditoria_transacciones definition

CREATE TABLE `auditoria_transacciones` (
                                           `id` int NOT NULL AUTO_INCREMENT,
                                           `transaccion_id` int NOT NULL,
                                           `estado_anterior` enum('pendiente','completado','fallido') NOT NULL,
                                           `estado_nuevo` enum('pendiente','completado','fallido') NOT NULL,
                                           `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                           PRIMARY KEY (`id`),
                                           KEY `transaccion_id` (`transaccion_id`),
                                           CONSTRAINT `auditoria_transacciones_ibfk_1` FOREIGN KEY (`transaccion_id`) REFERENCES `transacciones` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- bancocompleto.descuentos_aplicados definition

CREATE TABLE `descuentos_aplicados` (
                                        `id` int NOT NULL AUTO_INCREMENT,
                                        `transaccion_id` int NOT NULL,
                                        `beneficio_id` int NOT NULL,
                                        `monto_descuento` decimal(10,2) NOT NULL,
                                        PRIMARY KEY (`id`),
                                        KEY `transaccion_id` (`transaccion_id`),
                                        KEY `beneficio_id` (`beneficio_id`),
                                        CONSTRAINT `descuentos_aplicados_ibfk_1` FOREIGN KEY (`transaccion_id`) REFERENCES `transacciones` (`id`) ON DELETE CASCADE,
                                        CONSTRAINT `descuentos_aplicados_ibfk_2` FOREIGN KEY (`beneficio_id`) REFERENCES `beneficios` (`id`) ON DELETE CASCADE,
                                        CONSTRAINT `descuentos_aplicados_chk_1` CHECK ((`monto_descuento` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;