
CREATE TABLE Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cedula VARCHAR(15) NOT NULL UNIQUE,
    nombre VARCHAR(50) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL,
    activo BOOLEAN NOT NULL,
    eliminado BOOLEAN NOT NULL,
    fechaCreacion DATETIME NOT NULL
);

CREATE TABLE TipoImpuesto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    porcentaje DOUBLE NOT NULL
);

CREATE TABLE Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    contrasenia VARCHAR(255) NOT NULL
);

CREATE TABLE Producto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(255) NOT NULL UNIQUE,
    nombre VARCHAR(200) NOT NULL,
    precio DOUBLE NOT NULL,
    stock INT NOT NULL,
    activo BOOLEAN NOT NULL,
    fechaCreacion DATETIME NOT NULL,
    eliminado BOOLEAN NOT NULL,
    tipo_impuesto_id BIGINT,
    FOREIGN KEY (tipo_impuesto_id) REFERENCES TipoImpuesto(id)
);

CREATE TABLE Venta (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       fecha DATETIME NOT NULL,
                       subTotal DOUBLE NOT NULL,
                       total DOUBLE NOT NULL,
                       descuento DOUBLE NOT NULL,
                       usuario_id INT,
                       cliente_id INT,
                       dineroRecibido DOUBLE NOT NULL,
                       cambio DOUBLE NOT NULL,
                       estado VARCHAR(255) NOT NULL,
                       FOREIGN KEY (usuario_id) REFERENCES Usuario(id),
                       FOREIGN KEY (cliente_id) REFERENCES Cliente(id)
);

CREATE TABLE DetalleVenta (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              cantidad INT NOT NULL,
                              valor DOUBLE NOT NULL,
                              producto_id BIGINT NOT NULL,
                              venta_id INT NOT NULL,
                              FOREIGN KEY (producto_id) REFERENCES Producto(id),
                              FOREIGN KEY (venta_id) REFERENCES Venta(id)
);

CREATE TABLE Factura (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         fecha DATETIME NOT NULL,
                         venta_id INT UNIQUE,
                         FOREIGN KEY (venta_id) REFERENCES Venta(id)
);

CREATE TABLE FacturaElectronica (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    fecha DATETIME NOT NULL,
                                    venta_id INT UNIQUE,
                                    FOREIGN KEY (venta_id) REFERENCES Venta(id)
);

CREATE TABLE UsuarioPermisos (
                                 usuarioId INT NOT NULL,
                                 permisos VARCHAR(255) NOT NULL,
                                 PRIMARY KEY (usuarioId, permisos),
                                 FOREIGN KEY (usuarioId) REFERENCES Usuario(id)
);

