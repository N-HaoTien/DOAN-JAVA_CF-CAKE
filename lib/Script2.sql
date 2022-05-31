/*
Created		10/12/2021
Modified		07/04/2022
Project		
Model			
Company		
Author		
Version		
Database		MS SQL 2005 
*/


Create table [NhanVien]
(
	[IDNV] Integer Identity NOT NULL,
	[TenNV] Nvarchar(20) NOT NULL, UNIQUE ([TenNV]),
	[Email] Nvarchar(20) NULL,
	[TaiKhoan] Nvarchar(20) NULL, UNIQUE ([TaiKhoan]),
	[MatKhau] Nvarchar(20) NULL,
	[Quyen] Bit NULL,
Primary Key ([IDNV])
) 
go

Create table [KhachHang]
(
	[IDKH] Integer Identity NOT NULL,
	[TenKH] Nvarchar(20) NOT NULL,
	[DiaChi] Nvarchar(20) NULL,
	[SDT] Integer NULL,
	[NgayCapThe] Datetime NULL,
	[Level] Nvarchar(30) NULL,
Primary Key ([IDKH])
) 
go

Create table [LoaiSP]
(
	[IDLoaiSP] Integer Identity NOT NULL,
	[TenLoaiSP] Nvarchar(20) NOT NULL,
	[Hinh] Nvarchar(30) NULL,
Primary Key ([IDLoaiSP])
) 
go

Create table [SanPham]
(
	[IDSP] Integer Identity NOT NULL,
	[IDLoaiSP] Integer NOT NULL,
	[TenSP] Nvarchar(20) NOT NULL, UNIQUE ([TenSP]),
	[GiaBan] Integer NOT NULL,
	[SLTon] Integer Default 0 NULL,
	[Hinh] Nvarchar(30) NULL,
Primary Key ([IDSP])
) 
go

Create table [HoaDon]
(
	[IDHD] Integer Identity NOT NULL,
	[IDNV] Integer NOT NULL,
	[IDKH] Integer NULL,
	[TongTien] Float NULL,
	[ThoiDiemLap] Datetime NULL,
	[GiamGia] Integer NULL,
Primary Key ([IDHD])
) 
go

Create table [ChiTietHoaDon]
(
	[IDSP] Integer NOT NULL,
	[IDHD] Integer NOT NULL,
	[SoLuong] Integer NOT NULL,
	[GhiChu] Nvarchar(30) NULL,
Primary Key ([IDSP],[IDHD])
) 
go


Alter table [HoaDon] add  foreign key([IDNV]) references [NhanVien] ([IDNV])  on update no action on delete no action 
go
Alter table [HoaDon] add  foreign key([IDKH]) references [KhachHang] ([IDKH])  on update no action on delete no action 
go
Alter table [SanPham] add  foreign key([IDLoaiSP]) references [LoaiSP] ([IDLoaiSP])  on update no action on delete no action 
go
Alter table [ChiTietHoaDon] add  foreign key([IDSP]) references [SanPham] ([IDSP])  on update no action on delete no action 
go
Alter table [ChiTietHoaDon] add  foreign key([IDHD]) references [HoaDon] ([IDHD])  on update no action on delete no action 
go


Set quoted_identifier on
go


Set quoted_identifier off
go


