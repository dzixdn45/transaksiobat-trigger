-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 01 Des 2017 pada 17.41
-- Versi Server: 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tokoobat`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `customer`
--

CREATE TABLE `customer` (
  `id_customer` varchar(20) NOT NULL,
  `nama_customer` varchar(50) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `no_telepon` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `customer`
--

INSERT INTO `customer` (`id_customer`, `nama_customer`, `alamat`, `no_telepon`) VALUES
('C00001', 'didin', 'jalan bintaro', '082882'),
('C00002', 'outu', 'jjj', '082992');

-- --------------------------------------------------------

--
-- Struktur dari tabel `obat`
--

CREATE TABLE `obat` (
  `id_obt` varchar(30) NOT NULL,
  `jenis_obt` varchar(50) NOT NULL,
  `nama_obt` varchar(50) NOT NULL,
  `harga` int(10) NOT NULL,
  `jumlah` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `obat`
--

INSERT INTO `obat` (`id_obt`, `jenis_obt`, `nama_obt`, `harga`, `jumlah`) VALUES
('B00001', 'ghg', 'ggu', 6000, 10),
('B00002', 'tablet', 'panadol ekstra', 1000, 8);

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` varchar(20) NOT NULL,
  `id_customer` varchar(20) NOT NULL,
  `nama_customer` varchar(50) NOT NULL,
  `id_barang` varchar(30) NOT NULL,
  `jumlah` int(10) NOT NULL,
  `harga` int(10) NOT NULL,
  `total` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_customer`, `nama_customer`, `id_barang`, `jumlah`, `harga`, `total`) VALUES
('T00006', 'C00002', 'outu', 'B00002', 1, 1000, 1000),
('T00007', 'C00002', 'outu', 'B00002', 3, 1000, 3000),
('T00008', 'C00002', 'outu', 'B00002', 2, 1000, 2000),
('T00009', 'C00002', 'outu', 'B00002', 3, 1000, 3000),
('T00010', 'C00002', 'outu', 'B00002', 3, 1000, 3000),
('T00011', 'C00002', 'outu', 'B00002', 2, 1000, 2000);

--
-- Trigger `transaksi`
--
DELIMITER $$
CREATE TRIGGER `transaksi` AFTER INSERT ON `transaksi` FOR EACH ROW UPDATE obat SET jumlah=obat.jumlah-New.jumlah WHERE id_obt=New.id_barang
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id_customer`);

--
-- Indexes for table `obat`
--
ALTER TABLE `obat`
  ADD PRIMARY KEY (`id_obt`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
