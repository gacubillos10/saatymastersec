-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 14, 2020 at 08:14 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `privacidad`
--

-- --------------------------------------------------------

--
-- Table structure for table `archivo`
--

CREATE TABLE `archivo` (
  `idArchivo` int(50) NOT NULL,
  `nombre` text NOT NULL,
  `tamano` int(255) NOT NULL,
  `hash` varchar(255) NOT NULL,
  `creacion` datetime NOT NULL,
  `base` varchar(100) NOT NULL,
  `acceso` varchar(10) NOT NULL,
  `idusuario` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `fragmento`
--

CREATE TABLE `fragmento` (
  `idArchivo` int(50) NOT NULL,
  `idfragmento` int(100) NOT NULL,
  `fecha` datetime NOT NULL,
  `direccion` text NOT NULL,
  `hashAnterior` varchar(255) NOT NULL,
  `hashSiguiente` varchar(255) NOT NULL,
  `hashActual` varchar(255) NOT NULL,
  `tamano` bigint(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `perfil`
--

CREATE TABLE `perfil` (
  `idperfil` int(100) NOT NULL,
  `idUsuario` int(100) NOT NULL,
  `fecha_creacion` datetime NOT NULL DEFAULT current_timestamp(),
  `descripcion` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `perfil`
--

INSERT INTO `perfil` (`idperfil`, `idUsuario`, `fecha_creacion`, `descripcion`) VALUES
(1, 1, '2020-06-14 13:11:12', 'owner'),
(2, 2, '2020-06-14 13:13:34', 'reader');

-- --------------------------------------------------------

--
-- Table structure for table `permisos`
--

CREATE TABLE `permisos` (
  `idpermisos` int(100) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `permiso` varchar(20) NOT NULL,
  `fecha` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `permisos`
--

INSERT INTO `permisos` (`idpermisos`, `nombre`, `permiso`, `fecha`) VALUES
(1, 'escritura', 'W', '2020-06-14 13:04:24'),
(2, 'lectura', 'R', '2020-06-14 13:04:24');

-- --------------------------------------------------------

--
-- Table structure for table `permisos_perfil`
--

CREATE TABLE `permisos_perfil` (
  `idpermisos_perfil` int(50) NOT NULL,
  `idarchivo` int(50) NOT NULL,
  `idperfil` int(50) NOT NULL,
  `creacion` datetime NOT NULL,
  `expiracion` datetime NOT NULL,
  `chmod` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `idusuario` int(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `passsword` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`idusuario`, `email`, `passsword`) VALUES
(1, 'ga.cubillos10@uniandes.edu.co', '40bd001563085fc35165329ea1ff5c5ecbdbbeef'),
(2, 'wolfang@uniandes.edu.co', '40bd001563085fc35165329ea1ff5c5ecbdbbeef');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `archivo`
--
ALTER TABLE `archivo`
  ADD PRIMARY KEY (`idArchivo`),
  ADD KEY `fk_usuario` (`idusuario`);

--
-- Indexes for table `fragmento`
--
ALTER TABLE `fragmento`
  ADD PRIMARY KEY (`idfragmento`),
  ADD KEY `fk_archivo` (`idArchivo`);

--
-- Indexes for table `perfil`
--
ALTER TABLE `perfil`
  ADD PRIMARY KEY (`idperfil`);

--
-- Indexes for table `permisos`
--
ALTER TABLE `permisos`
  ADD PRIMARY KEY (`idpermisos`);

--
-- Indexes for table `permisos_perfil`
--
ALTER TABLE `permisos_perfil`
  ADD PRIMARY KEY (`idpermisos_perfil`),
  ADD KEY `fk_usuario_p` (`idperfil`),
  ADD KEY `fk_archivo_p` (`idarchivo`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idusuario`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `archivo`
--
ALTER TABLE `archivo`
  MODIFY `idArchivo` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `fragmento`
--
ALTER TABLE `fragmento`
  MODIFY `idfragmento` int(100) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `perfil`
--
ALTER TABLE `perfil`
  MODIFY `idperfil` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `permisos`
--
ALTER TABLE `permisos`
  MODIFY `idpermisos` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `permisos_perfil`
--
ALTER TABLE `permisos_perfil`
  MODIFY `idpermisos_perfil` int(50) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idusuario` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `archivo`
--
ALTER TABLE `archivo`
  ADD CONSTRAINT `fk_usuario` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`);

--
-- Constraints for table `fragmento`
--
ALTER TABLE `fragmento`
  ADD CONSTRAINT `fk_archivo` FOREIGN KEY (`idArchivo`) REFERENCES `archivo` (`idArchivo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
