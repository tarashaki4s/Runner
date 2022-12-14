create database [Runner]
go
USE [Runner]
GO
/****** Object:  Table [dbo].[Accounts]    Script Date: 8/7/2022 11:08:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Accounts](
	[Username] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](50) NOT NULL,
	[Fullname] [nvarchar](50) NOT NULL,
	[Email] [nvarchar](60) NOT NULL,
	[Gender] [bit] NOT NULL,
	[Active] [bit] NOT NULL,
 CONSTRAINT [PK_Accounts] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Authorities]    Script Date: 8/7/2022 11:08:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Authorities](
	[Id] [int] NOT NULL identity(1,1),
	[Username] [nvarchar](50) NOT NULL,
	[RoleId] [nchar](4) NOT NULL,
 CONSTRAINT [PK_Authorities] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Categories]    Script Date: 8/7/2022 11:08:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Categories](
	[Id] [nchar](4) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Categories] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetails]    Script Date: 8/7/2022 11:08:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetails](
	[Id] [int] NOT NULL identity(1,1),
	[Price] [float] NOT NULL,
	[Quantity] [int] NOT NULL,
	[ProductId] [int] NOT NULL,
	[OrderId] [int] NOT NULL,
 CONSTRAINT [PK_OrderDetails] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 8/7/2022 11:08:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[Id] [int] NOT NULL identity(1,1),
	[Username] [nvarchar](50) NOT NULL,
	[Createdate] [date] NOT NULL,
	[Address] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_Orders] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Products]    Script Date: 8/7/2022 11:08:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[Id] [int] NOT NULL identity(1,1),
	[CategoryId] [nchar](4) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Price] [float] NOT NULL,
	[Size] [int] NOT NULL,
	[Image1] [nvarchar](50) NOT NULL,
	[Image2] [nvarchar](50) NOT NULL,
	[Amount] [int] NOT NULL,
	[Color] [nvarchar](50) NOT NULL,
	[Active] [bit] NOT NULL,
	[Quantitysold] [int] NOT NULL,
	[Describe] [nvarchar](300) NOT NULL,
 CONSTRAINT [PK_Products] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Roles]    Script Date: 8/7/2022 11:08:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Roles](
	[Id] [nchar](4) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Roles] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Authorities]  WITH CHECK ADD  CONSTRAINT [fk_htk_id_account] FOREIGN KEY([Username])
REFERENCES [dbo].[Accounts] ([Username])
GO
ALTER TABLE [dbo].[Authorities] CHECK CONSTRAINT [fk_htk_id_account]
GO
ALTER TABLE [dbo].[Authorities]  WITH CHECK ADD  CONSTRAINT [fk_htk_id_roles] FOREIGN KEY([RoleId])
REFERENCES [dbo].[Roles] ([Id])
GO
ALTER TABLE [dbo].[Authorities] CHECK CONSTRAINT [fk_htk_id_roles]
GO
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [fk_htk_id_chitietsp] FOREIGN KEY([ProductId])
REFERENCES [dbo].[Products] ([Id])
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [fk_htk_id_chitietsp]
GO
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [fk_htk_id_hoadon] FOREIGN KEY([OrderId])
REFERENCES [dbo].[Orders] ([Id])
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [fk_htk_id_hoadon]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [fk_htk_id_ND] FOREIGN KEY([Username])
REFERENCES [dbo].[Accounts] ([Username])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [fk_htk_id_ND]
GO
ALTER TABLE [dbo].[Products]  WITH CHECK ADD  CONSTRAINT [fk_htk_id_sanpham] FOREIGN KEY([CategoryId])
REFERENCES [dbo].[Categories] ([Id])
GO
ALTER TABLE [dbo].[Products] CHECK CONSTRAINT [fk_htk_id_sanpham]
GO

INSERT [dbo].[Accounts] ([Username], [Password], [Fullname], [Email],[Gender],[Active]) VALUES (N'customer', N'123', N'Nguyễn Văn Tèo', N'teonv@gmail.com',1,1)
INSERT [dbo].[Accounts] ([Username], [Password], [Fullname], [Email], [Gender],[Active]) VALUES (N'director', N'123', N'Nguyễn Chí Phèo', N'pheonc@fpt.edu.vn',1,1)
INSERT [dbo].[Accounts] ([Username], [Password], [Fullname], [Email], [Gender],[Active]) VALUES (N'nguyenlv', N'dracd', N'Sven Ottlieb', N'dracd@gmail.com',1,1)
INSERT [dbo].[Accounts] ([Username], [Password], [Fullname], [Email], [Gender],[Active]) VALUES (N'nhuttruong', N'123', N'Janine Labrune', N'dumon@gmail.com',1,1)
INSERT [dbo].[Accounts] ([Username], [Password], [Fullname], [Email], [Gender],[Active]) VALUES (N'vinhsang123', N'123', N'Ann Devon', N'eastc@gmail.com',1,1)
INSERT [dbo].[Accounts] ([Username], [Password], [Fullname], [Email], [Gender],[Active]) VALUES (N'trungnguyen2k2', N'123', N'Roland Mendel', N'ernsh@gmail.com',1,1)
INSERT [dbo].[Accounts] ([Username], [Password], [Fullname], [Email], [Gender],[Active]) VALUES (N'hoainam', N'123', N'Aria Cruz', N'famia@gmail.com',1,1)
INSERT [dbo].[Accounts] ([Username], [Password], [Fullname], [Email], [Gender],[Active]) VALUES (N'staff', N'123', N'Aria Cruz', N'famia@gmail.com',1,1)
SET IDENTITY_INSERT [dbo].[Authorities] ON 

go
INSERT [dbo].[Roles] ([Id], [Name]) VALUES (N'CUST', N'Customers')
INSERT [dbo].[Roles] ([Id], [Name]) VALUES (N'DIRE', N'Directors')
INSERT [dbo].[Roles] ([Id], [Name]) VALUES (N'STAF', N'Staffs')
go
INSERT [dbo].[Authorities] ([Id], [Username], [RoleId]) VALUES (1, N'nguyenlv', N'CUST')
INSERT [dbo].[Authorities] ([Id], [Username], [RoleId]) VALUES (2,N'nhuttruong', N'CUST')
INSERT [dbo].[Authorities] ([Id], [Username], [RoleId]) VALUES (3, N'vinhsang123', N'CUST')
INSERT [dbo].[Authorities] ([Id], [Username], [RoleId]) VALUES (4, N'trungnguyen2k2', N'CUST')
INSERT [dbo].[Authorities] ([Id], [Username], [RoleId]) VALUES (5, N'hoainam', N'CUST')
INSERT [dbo].[Authorities] ([Id], [Username], [RoleId]) VALUES (6, N'director', N'DIRE')
INSERT [dbo].[Authorities] ([Id],[Username], [RoleId]) VALUES (7, N'staff', N'STAF')
INSERT [dbo].[Authorities] ([Id], [Username], [RoleId]) VALUES (8, N'director', N'STAF')
INSERT [dbo].[Authorities] ([Id], [Username], [RoleId]) VALUES (9, N'staff', N'CUST')
SET IDENTITY_INSERT [dbo].[Authorities] OFF
go
INSERT [dbo].[Categories] ([Id], [Name]) VALUES (N'1', N'Nike')
INSERT [dbo].[Categories] ([Id], [Name]) VALUES (N'2', N'Adidas')
SET IDENTITY_INSERT [dbo].[OrderDetails] ON 


GO

INSERT [dbo].[OrderDetails] ( [OrderId], [ProductId], [Price], [Quantity]) VALUES ( 1, 1, 12.5, 2)
INSERT [dbo].[OrderDetails] ( [OrderId], [ProductId], [Price], [Quantity]) VALUES ( 1, 2, 17.45, 1)
INSERT [dbo].[OrderDetails] ( [OrderId], [ProductId], [Price], [Quantity]) VALUES ( 1, 3, 21.35, 1)
INSERT [dbo].[OrderDetails] ( [OrderId], [ProductId], [Price], [Quantity]) VALUES ( 2, 1, 9.2, 1)
INSERT [dbo].[OrderDetails] ( [OrderId], [ProductId], [Price], [Quantity]) VALUES ( 2, 3, 10, 1)
INSERT [dbo].[OrderDetails] ( [OrderId], [ProductId], [Price], [Quantity]) VALUES ( 3, 6, 19, 1)
INSERT [dbo].[OrderDetails] ( [OrderId], [ProductId], [Price], [Quantity]) VALUES ( 4, 7, 190, 1)
INSERT [dbo].[OrderDetails] ( [OrderId], [ProductId], [Price], [Quantity]) VALUES ( 5, 1, 9, 1)
INSERT [dbo].[OrderDetails] ( [OrderId], [ProductId], [Price], [Quantity]) VALUES ( 5, 2, 21, 3)
INSERT [dbo].[OrderDetails] ( [OrderId], [ProductId], [Price], [Quantity]) VALUES ( 6, 6, 14, 1)
INSERT [dbo].[OrderDetails] ( [OrderId], [ProductId], [Price], [Quantity]) VALUES ( 6, 5, 39, 1)


go
INSERT [dbo].[Orders] ( [Username], [CreateDate], [Address]) VALUES ( N'customer', CAST(0x0000ACF300000000 AS DateTime), N'Quận 12,TP Hồ Chí Minh')
INSERT [dbo].[Orders] ( [Username], [CreateDate], [Address]) VALUES ( N'nguyenlv', CAST(0x0000ACF300000000 AS DateTime), N'Biên Hòa ,Đồng Nai')
INSERT [dbo].[Orders] ( [Username], [CreateDate], [Address]) VALUES ( N'nhuttruong', CAST(0x0000ACF400000000 AS DateTime), N'Chư Sê,Gia Lai')
INSERT [dbo].[Orders] ( [Username], [CreateDate], [Address]) VALUES ( N'vinhsang123', CAST(0x0000ACF400000000 AS DateTime), N'Quận Cầu giấy,Hà Nội')
INSERT [dbo].[Orders] ( [Username], [CreateDate], [Address]) VALUES ( N'staff', CAST(0x0000AD0100000000 AS DateTime), N'Quận 3 ,TP Hồ Chí Minh')
INSERT [dbo].[Orders] ( [Username], [CreateDate], [Address]) VALUES ( N'staff', CAST(0x0000AD0100000000 AS DateTime), N'Xã Dun,Huyện Chư Sê,Gia Lai')

go
INSERT [dbo].[Products] ([CategoryId], [Name],[Price],[Size],[Image1],[Image2],[Amount], [Color],[Active],[Quantitysold],[Describe] ) VALUES ('1',N'Nike Red White',2300000,39,'1.jpg','1-1.jpg',13,N'White,Red',1,23,N'Hiện đại và thời trang khi diện item mới của Nike. Màu sắc lạ mắt, chất liệu thoáng mát, nhẹ nhàng, phù hợp với những chàng trai yêu phong cách sports.')
INSERT [dbo].[Products] ([CategoryId], [Name],[Price],[Size],[Image1],[Image2],[Amount], [Color],[Active],[Quantitysold],[Describe] ) VALUES ('1',N'Nike Just for fun',2400000,39,'2.jpg','2-2.jpg',13,N'White,Orage',1,23,N'Hiện đại và thời trang khi diện item mới của Nike. Màu sắc lạ mắt, chất liệu thoáng mát, nhẹ nhàng, phù hợp với những chàng trai yêu phong cách sports.')
INSERT [dbo].[Products] ([CategoryId], [Name],[Price],[Size],[Image1],[Image2],[Amount], [Color],[Active],[Quantitysold],[Describe] ) VALUES ('2',N'Adidas Yeezy 350',3300000,39,'14.jpg','14-14.jpg',13,N'White',1,23,N'Hiện đại và thời trang khi diện item mới của Nike. Màu sắc lạ mắt, chất liệu thoáng mát, nhẹ nhàng, phù hợp với những chàng trai yêu phong cách sports.')
INSERT [dbo].[Products] ([CategoryId], [Name],[Price],[Size],[Image1],[Image2],[Amount], [Color],[Active],[Quantitysold],[Describe] ) VALUES ('2',N'Adidas Yeezy 700',2300000,39,'17.jpg','17-17.jpg',13,N'Gray',1,23,N'Hiện đại và thời trang khi diện item mới của Nike. Màu sắc lạ mắt, chất liệu thoáng mát, nhẹ nhàng, phù hợp với những chàng trai yêu phong cách sports.')
INSERT [dbo].[Products] ([CategoryId], [Name],[Price],[Size],[Image1],[Image2],[Amount], [Color],[Active],[Quantitysold],[Describe] ) VALUES ('1',N'Nike White',2300000,39,'6.jpg','6-6.jpg',13,N'White',1,23,N'Hiện đại và thời trang khi diện item mới của Nike. Màu sắc lạ mắt, chất liệu thoáng mát, nhẹ nhàng, phù hợp với những chàng trai yêu phong cách sports.')
INSERT [dbo].[Products] ([CategoryId], [Name],[Price],[Size],[Image1],[Image2],[Amount], [Color],[Active],[Quantitysold],[Describe] ) VALUES ('2',N'Adidas Sport',2300000,39,'9.jpg','9-9.jpg',13,N'Orange',1,23,N'Hiện đại và thời trang khi diện item mới của Nike. Màu sắc lạ mắt, chất liệu thoáng mát, nhẹ nhàng, phù hợp với những chàng trai yêu phong cách sports.')
INSERT [dbo].[Products] ([CategoryId], [Name],[Price],[Size],[Image1],[Image2],[Amount], [Color],[Active],[Quantitysold],[Describe] ) VALUES ('1',N'Nike Blue',2300000,39,'5.jpg','5-5.jpg',13,N'Blue',1,23,N'Hiện đại và thời trang khi diện item mới của Nike. Màu sắc lạ mắt, chất liệu thoáng mát, nhẹ nhàng, phù hợp với những chàng trai yêu phong cách sports.')
INSERT [dbo].[Products] ([CategoryId], [Name],[Price],[Size],[Image1],[Image2],[Amount], [Color],[Active],[Quantitysold],[Describe] ) VALUES ('2',N'Adidas Black',2300000,39,'10.jpg','10-10.jpg',13,N'Black',1,23,N'Hiện đại và thời trang khi diện item mới của Nike. Màu sắc lạ mắt, chất liệu thoáng mát, nhẹ nhàng, phù hợp với những chàng trai yêu phong cách sports.')
INSERT [dbo].[Products] ([CategoryId], [Name],[Price],[Size],[Image1],[Image2],[Amount], [Color],[Active],[Quantitysold],[Describe] ) VALUES ('2',N'Adidas Sport 1',2300000,39,'19.jpg','19-19.jpg',13,N'White,Pink',1,23,N'Hiện đại và thời trang khi diện item mới của Nike. Màu sắc lạ mắt, chất liệu thoáng mát, nhẹ nhàng, phù hợp với những chàng trai yêu phong cách sports.')
INSERT [dbo].[Products] ([CategoryId], [Name],[Price],[Size],[Image1],[Image2],[Amount], [Color],[Active],[Quantitysold],[Describe] ) VALUES ('2',N'Adidas Sport 2',2300000,39,'18.jpg','18-18.jpg',13,N'White',1,23,N'Hiện đại và thời trang khi diện item mới của Nike. Màu sắc lạ mắt, chất liệu thoáng mát, nhẹ nhàng, phù hợp với những chàng trai yêu phong cách sports.')


