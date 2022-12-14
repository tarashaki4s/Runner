CREATE RUNNER
GO

USE RUNNER
/****** Object:  Table [dbo].[Account_Role]    Script Date: 12/8/2022 10:49:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account_Role](
	[Role_Id] [nchar](4) NULL,
	[Account_Id] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Accounts]    Script Date: 12/8/2022 10:49:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Accounts](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Username] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](255) NULL,
	[Fullname] [nvarchar](50) NOT NULL,
	[Email] [nvarchar](60) NOT NULL,
	[Gender] [bit] NOT NULL,
	[Active] [bit] NULL,
	[Verification_code] [varchar](64) NULL,
	[Reset_password_token] [varchar](30) NULL,
	[Image] [varchar](255) NULL,
 CONSTRAINT [PK_Accounts] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Categories]    Script Date: 12/8/2022 10:49:06 PM ******/
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
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CommentImages]    Script Date: 12/8/2022 10:49:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CommentImages](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Content] [nvarchar](max) NULL,
	[CommentID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetails]    Script Date: 12/8/2022 10:49:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetails](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Quantity] [int] NOT NULL,
	[ProductId] [int] NOT NULL,
	[OrderId] [int] NOT NULL,
 CONSTRAINT [PK_OrderDetails] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 12/8/2022 10:49:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Createdate] [date] NOT NULL,
	[Address] [nvarchar](100) NOT NULL,
	[Account_Id] [int] NULL,
	[Status] [bit] NULL,
 CONSTRAINT [PK_Orders] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Products]    Script Date: 12/8/2022 10:49:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[Id] [int] IDENTITY(1,1) NOT NULL,
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
	[Create_date] [date] NULL,
 CONSTRAINT [PK_Products] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Rates]    Script Date: 12/8/2022 10:49:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Rates](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Stars] [int] NULL,
	[Comment] [nvarchar](250) NULL,
	[Account] [int] NULL,
	[Product] [int] NULL,
	[RatedDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Roles]    Script Date: 12/8/2022 10:49:06 PM ******/
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
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'3   ', 5)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 6)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 8)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 9)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 10)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 11)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 12)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 13)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 14)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'3   ', 15)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 16)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 17)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 19)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 20)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 21)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 23)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 24)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 25)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 27)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 28)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 39)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 44)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 45)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 46)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'3   ', 47)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 48)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 18)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 22)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 41)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 42)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 26)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 36)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 43)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 51)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 37)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 38)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 40)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'1   ', 49)
INSERT [dbo].[Account_Role] ([Role_Id], [Account_Id]) VALUES (N'3   ', 50)
GO
SET IDENTITY_INSERT [dbo].[Accounts] ON 

INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (5, N'nhutkca', N'$2a$10$Vm53D71dIqbbFBqoNkcwD.eL/Ue2Oxr4aUZwIIOqIChZm0.AFqrzq', N'Truong Dinh Nhut', N'nhutkca@gmail.com', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (6, N'nhut2k', N'$2a$10$EFSeAfWM1QMeO6Kwj5/sE.0j.J0uH/ky4/JM2fRllwirgtMDeYmbW', N'Truong Dinh Nhut', N'nhut2k@gmail.com', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (8, N'youngtown', N'$2a$10$Qw0xc5qLAUA8r0S6EN7DcecUkcBBXpYerg0lWh.bsrIIJWkLDNUcu', N'Truong Dinh Nhut', N'nhuttd@itsj-group.com.vn', 1, 1, NULL, N'x8NXJocyABuw2sZVCa5sl6ZxHyhZ0L', NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (9, N'nguyen123', N'$2a$10$ooiW73/wDU5VeUWH4kNDsOC6upvp5HGQBc41F3xziwb7JU0SEaxcK', N'Le Van Nguyen', N'nhut23112000@gmail.com', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (10, N'user1', N'$2a$10$hDAmifjG4QcIV8KThtPyx.jFtFJRmcDqICMHnurj2j5FcY/QFPAnO', N'Truong Dinh Nhut', N'long@gmail.com', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (11, N'sang123', N'$2a$10$lqAl/bW.vIUy5hcMkCWdc.S16h6hEEm.lP9foNz9JFJd03BTkKqKi', N'Pham Vinh Sang', N'sang123@gmail.com', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (12, N'trung123', N'$2a$10$ws6t1pU8Q2EeDbF7aqFatOBjUBBTqpn3oWjGID3K4Smn/Fw7Ju6H6', N'Nguyen Thanh Trung', N'trung@gmail.com', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (13, N'', N'$2a$10$lc5NevFqRbg.e/bzzz4uUuf6q2Y66g.hB88iRbSq0mdfcQMvPxMbe', N'Nhut Truong ', N'', 0, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (14, N'nhut123', N'$2a$10$OhN21j1mA2dEwO28HneogevSpZKKuaOf3a9TGBmDfpv.UgUWhtxP6', N'Nhut Truong Dinh', N'nhuttruong@gmail.com', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (15, N'nhut2klalala', N'$2a$10$ldtRTyUyOS2EHydh.bYMOeEVr2nkJ/TAXbhVA.cs0nvI.z05Q1JpG', N'aloaloalo', N'nhut@gmail.com', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (16, N'nhut22', N'$2a$10$hB.ZdmdqB63MdV4DYgtrueRQbact97...2gD7G7PlJeTt.Zae6xw6', N'Truong Dinh Nhut', N'nhut22@gmail.com', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (17, N'sang12345', N'$2a$10$Cb6nfD.IqjMCUov2X1jHIOcBuAwSKJvUvQT36IEv5vS6H7FJDNDjO', N'Pham Vinh Sang', N'sang12345@gmail.com', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (18, N'alwea123456', N'$2a$10$YPh38IH/xZZR20sEotwYd.7nupqjjQzS7Ap3XScOWP24lqcc6K1XW', N'Vinh Sang', N'phamvinhsang2002@gmail.com', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (19, N'hai0322', N'$2a$10$CaWTrUjPqooI5edDH93RaOtolv4HM/OG2B3E1nlKtDXTZci8/dd2C', N'Lam Thanh Hai', N'reyal50547@teknowa.com', 1, 1, N'tucxOzv5rE6MmCfeK2fVHWv6o5JRhl6V7zRl1izyj3xgRp4nl5FJly0xilxkUWab', NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (20, N'youngtown1', N'$2a$10$WLY6fjwtb2KHDbQyAjSi4OcHGFS4rdaMMC5KJx3KIJDgl5wcJ3ini', N'Truong Dinh Nhut', N'yoyec97120@sunetoa.com', 1, 1, N'fPFb8zSaAElMCvBl7Z2F88daZbSdBTVwVfDyDkcCJgucYp66vApS4dvp4rVNnGIA', NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (21, N'youngtown123', N'$2a$10$nLyHNbtzyv41blGohBvqxe9m7s0pt1Zmz0QWh9nM3CKiyu/TOCTKS', N'Truong Dinh Nhut', N'delodi6475@teknowa.com', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (22, N'sangsang', N'$2a$10$w0X4/TblvPSEdBoVAtBe9e59j.75CRR/qeL0rL3RhkdFQLVbr1J66', N'Pham Vinh Sang', N'xayehe2165@teknowa.com', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (23, N'nightmare', N'$2a$10$aAUqk.e8P1jgPYN/Uqc6ReaNAYpZxvlt5sUA.0X7w46Ig/x0wbU8.', N'Tuyen', N'sipoxif647@sunetoa.com', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (24, N'nguyen0000', N'$2a$10$9JCwP/2mjkENIhh7ZqJLL.Kh.YjMt2wQKyxWrlRBsKbGuH7hSgGsO', N'Le Van Nguyen', N'ramop70351@teknowa.com', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (25, N'tuyen2k', N'$2a$10$nvOCrbR.GlnpEqrWQP.pVu88uPZWFTxE/uidRMWJI18HJ495ndxQC', N'Tran Quang Tuyen', N'jeyerog651@eilnews.com', 1, 1, N'0GvriZcz9ypYAI2jTbcZkvRZAsEHRBKxNX6o0vCAvY89i9TvR6rfSvc0MXicXCCg', NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (26, N'tuyen2k000', N'$2a$10$qtGBp8uSu.J3ald3yczqeOOQAzm3PWC.eOGwm1.un5gpd3nxq7H/O', N'Tran Quang Tuyen', N'bolayed542@edinel.com', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (27, N'hai2311', N'$2a$10$SXfq18KS32ZtGCvV9tF2G.9Pot/aOgBi0I040iobdcutxWTKxDRyW', N'Lam Thanh Hai', N'nhutt32d@itsj-group.com.vn', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (28, N'tuyentall', N'$2a$10$mwVkaTnXRpXzJbJ4KVPcjuKwUsEEa4Nj8zfpoBd1SCesnbfQyDDGS', N'Tuyen Taller', N'fofon32657@eilnews.com', 1, 1, NULL, N'sO8yCCYBeMqbnEmQdpoGaBM6FFOqmY', NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (29, N'trung456', N'$2a$10$ws6t1pU8Q2EeDbF7aqFatOBjUBBTqpn3oWjGID3K4Smn/Fw7Ju6H6', N'Nguyen Thanh Trung', N'trung@gmail.com', 0, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (30, N'trung123', N'$2a$10$ws6t1pU8Q2EeDbF7aqFatOBjUBBTqpn3oWjGID3K4Smn/Fw7Ju6H6', N'Nguyen Thanh Trung', N'trung@gmail.com', 0, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (31, N'trung123', N'$2a$10$ws6t1pU8Q2EeDbF7aqFatOBjUBBTqpn3oWjGID3K4Smn/Fw7Ju6H6', N'Nguyen Thanh Trung', N'trung@gmail.com', 0, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (32, N'trung123', N'$2a$10$ws6t1pU8Q2EeDbF7aqFatOBjUBBTqpn3oWjGID3K4Smn/Fw7Ju6H6', N'Nguyen Thanh Trung', N'trung@gmail.com', 0, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (36, N'zhaosanghao', N'$2a$10$cIKx/1faVrfAQx2m2jbxautJgly9.gl/2jDeUgR49.QldbBzyNiTS', N'Tran Wang Tuyen', N'jipax10226@eilnews.com', 1, 1, N's7Jtz3wyedsaLcsm89Th2XTvOwRexhcv4G8gMaVIgH1HdjyHZxElsWkWPWlqMIQO', NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (37, N'haola', N'$2a$10$URU72Y.B/Svc8WPXgL0OHemGMFoaTNYD75uDgGQxmnidCkFcBWDKe', N'Duong Minh Quang', N'mekec95684@edinel.com', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (38, N'namene', N'$2a$10$Qs61cr4Our/iQPA1xDnsGevd37O7Mjeyi4ggcA/MB1mrhz1xZrte6', N'What your name', N'cevive6248@diratu.com', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (39, N'user100', N'$2a$10$3CV.rdoyJSnQopkU9bK.0u/gg4KuEU2whEfXA2j0EVIVaI5HVcYr6', N'Duong Tuan Minh', N'layolo7863@diratu.com', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (40, N'toto', N'$2a$10$aAUqk.e8P1jgPYN/Uqc6ReaNAYpZxvlt5sUA.0X7w46Ig/x0wbU8.', N'Nguyen Van To', N'tapega5192@eilnews.com', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (41, N'tuyentuctiu', N'$2a$10$wwFboM5UX.PXPmTMNg1L3eVZ/SNPKDklAjjkPHEsEXlGjqGanjx0m', N'Chan Wang Zhieng', N'ritebe6174@edinel.com', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (42, N'quangduong', N'$2a$10$KKZKeQFc7njMmm6tfgbY0OOiRFaXnsg5xfy88ILpkFfHluKa3lqmS', N'Lam Thanh Quan', N'hosegaw505@dmonies.com', 1, 0, N'iKjCOCoOp4xOqTrzlc1DVirQ856OxzTvULqdJrRCF6xHVQ6mO76pmYKZ64iZxBOv', NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (43, N'longld', N'$2a$10$1OdvbYILwxuTzxBe0BPcfuzgbBNnDobeafqpOCP3MG7tVXbZgeGy6', N'Lam Du Long', N'xejiti9070@dmonies.com', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (44, N'lahiru123', N'$2a$10$DfAtVetret9SdAC91ycIpOrgOvsPOnni8D/h/D8XXsBYTMdD/q4Hm', N'Lahiru', N'tetak34496@edinel.com', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (45, N'lahiru1235', N'$2a$10$EyUneAbDPZ2o8ZQECCkNi.dvZ3ngwAfALg0tQD3UqwwXdMWAJVVmO', N'Lahuri', N'tetak34496@edinel.com', 1, 0, N'gykMdmjjWoeGfmhpSMVaKfZs3LQJTEtrF0eph91ky6vlQItvU0HyBrM8buN98gHf', NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (46, N'sangvinh', N'$2a$10$.jgaLUoyLDUxxmnFNtwjUe9Dmbnk.kHuL.ez7kBJH3eYi8q064ha2', N'Pham Sang Vinh', N'fobixic345@diratu.com', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (47, N'tan1cu', N'$2a$10$2VsWd9xpYYh9S1hykFhEXOVQ8kgtqhJB7J1qNnGYpXHuh0ik4iF..', N'Vo Van Tan', N'ciximi9703@dmonies.com', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (48, N'nhuttd2k', N'$2a$10$uyUyOgkNNBNWPefWun10q.Lf.zCg3lojg7UXzkhmWdDYp4qTDYU/K', N'Truong Dinh Nhut', N'labir77282@eilnews.com', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (49, N'nhutlailaptrinh', N'$2a$10$xylpW6ymFHGfSE0lSuKby.IHITsGmkMSv.fLY673vmoWjDx1z7T2a', N'Nhut Truong Dinh', N'nhuttdps18825@fpt.edu.vn', 1, 0, N'n0SG8dplNhKztRKeO7iF1iMwyCNKgUGUM3ULikCpwgzmq0IGuzNC5auBpXVIgX63', NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (50, N'nguyenlv', N'$2a$10$uBHguRkG9NQpHmjC2PkpOux9n6F6pFlRjF6qQDgCFepOSAI5tvxhq', N'123', N'vnguyen2k2@gmail.com', 1, 1, NULL, NULL, NULL)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Fullname], [Email], [Gender], [Active], [Verification_code], [Reset_password_token], [Image]) VALUES (51, N'test', N'$2a$10$rfIjda9UCuA6OW5wEYlz7uG9OYl9tRCPhqiv3S7KGYbnLuMszrmGa', N'Nguyen', N'tarashaki4s@gmail.com', 0, 0, N'enUHR7Ri8nxXchcY7ZZw3R7PzdukVPH29c6gcgicJPHqM8GqvbJJmPxbpRMZqpIR', NULL, NULL)
SET IDENTITY_INSERT [dbo].[Accounts] OFF
GO
INSERT [dbo].[Categories] ([Id], [Name]) VALUES (N'1   ', N'Nike')
INSERT [dbo].[Categories] ([Id], [Name]) VALUES (N'2   ', N'Adidas')
GO
SET IDENTITY_INSERT [dbo].[OrderDetails] ON 

INSERT [dbo].[OrderDetails] ([Id], [Quantity], [ProductId], [OrderId]) VALUES (1, 1, 1, 1)
INSERT [dbo].[OrderDetails] ([Id], [Quantity], [ProductId], [OrderId]) VALUES (2, 1, 2, 1)
INSERT [dbo].[OrderDetails] ([Id], [Quantity], [ProductId], [OrderId]) VALUES (3, 2, 3, 2)
INSERT [dbo].[OrderDetails] ([Id], [Quantity], [ProductId], [OrderId]) VALUES (4, 1, 4, 2)
INSERT [dbo].[OrderDetails] ([Id], [Quantity], [ProductId], [OrderId]) VALUES (5, 5, 1, 3)
INSERT [dbo].[OrderDetails] ([Id], [Quantity], [ProductId], [OrderId]) VALUES (6, 2, 6, 3)
INSERT [dbo].[OrderDetails] ([Id], [Quantity], [ProductId], [OrderId]) VALUES (7, 1, 6, 4)
INSERT [dbo].[OrderDetails] ([Id], [Quantity], [ProductId], [OrderId]) VALUES (8, 1, 7, 5)
INSERT [dbo].[OrderDetails] ([Id], [Quantity], [ProductId], [OrderId]) VALUES (9, 1, 8, 5)
INSERT [dbo].[OrderDetails] ([Id], [Quantity], [ProductId], [OrderId]) VALUES (10, 1, 6, 12)
INSERT [dbo].[OrderDetails] ([Id], [Quantity], [ProductId], [OrderId]) VALUES (11, 1, 4, 12)
INSERT [dbo].[OrderDetails] ([Id], [Quantity], [ProductId], [OrderId]) VALUES (12, 1, 2, 12)
INSERT [dbo].[OrderDetails] ([Id], [Quantity], [ProductId], [OrderId]) VALUES (13, 1, 5, 12)
INSERT [dbo].[OrderDetails] ([Id], [Quantity], [ProductId], [OrderId]) VALUES (14, 1, 1, 13)
INSERT [dbo].[OrderDetails] ([Id], [Quantity], [ProductId], [OrderId]) VALUES (15, 1, 6, 14)
INSERT [dbo].[OrderDetails] ([Id], [Quantity], [ProductId], [OrderId]) VALUES (16, 1, 2, 15)
INSERT [dbo].[OrderDetails] ([Id], [Quantity], [ProductId], [OrderId]) VALUES (17, 1, 2, 17)
INSERT [dbo].[OrderDetails] ([Id], [Quantity], [ProductId], [OrderId]) VALUES (18, 1, 4, 22)
INSERT [dbo].[OrderDetails] ([Id], [Quantity], [ProductId], [OrderId]) VALUES (19, 1, 5, 22)
INSERT [dbo].[OrderDetails] ([Id], [Quantity], [ProductId], [OrderId]) VALUES (20, 1, 5, 23)
INSERT [dbo].[OrderDetails] ([Id], [Quantity], [ProductId], [OrderId]) VALUES (21, 1, 4, 23)
INSERT [dbo].[OrderDetails] ([Id], [Quantity], [ProductId], [OrderId]) VALUES (22, 1, 5, 24)
INSERT [dbo].[OrderDetails] ([Id], [Quantity], [ProductId], [OrderId]) VALUES (23, 1, 5, 25)
INSERT [dbo].[OrderDetails] ([Id], [Quantity], [ProductId], [OrderId]) VALUES (24, 1, 4, 25)
INSERT [dbo].[OrderDetails] ([Id], [Quantity], [ProductId], [OrderId]) VALUES (25, 1, 5, 26)
INSERT [dbo].[OrderDetails] ([Id], [Quantity], [ProductId], [OrderId]) VALUES (26, 1, 4, 26)
SET IDENTITY_INSERT [dbo].[OrderDetails] OFF
GO
SET IDENTITY_INSERT [dbo].[Orders] ON 

INSERT [dbo].[Orders] ([Id], [Createdate], [Address], [Account_Id], [Status]) VALUES (1, CAST(N'2021-03-22' AS Date), N'Quận 12,TP Hồ Chí Minh', 10, 1)
INSERT [dbo].[Orders] ([Id], [Createdate], [Address], [Account_Id], [Status]) VALUES (2, CAST(N'2021-03-22' AS Date), N'Biên Hòa ,Đồng Nai', 10, 1)
INSERT [dbo].[Orders] ([Id], [Createdate], [Address], [Account_Id], [Status]) VALUES (3, CAST(N'2021-03-23' AS Date), N'Chư Sê,Gia Lai', 10, 1)
INSERT [dbo].[Orders] ([Id], [Createdate], [Address], [Account_Id], [Status]) VALUES (4, CAST(N'2021-03-23' AS Date), N'Quận Cầu giấy,Hà Nội', 10, 1)
INSERT [dbo].[Orders] ([Id], [Createdate], [Address], [Account_Id], [Status]) VALUES (5, CAST(N'2021-04-05' AS Date), N'Quận 3 ,TP Hồ Chí Minh', 10, 0)
INSERT [dbo].[Orders] ([Id], [Createdate], [Address], [Account_Id], [Status]) VALUES (6, CAST(N'2021-04-05' AS Date), N'Xã Dun,Huyện Chư Sê,Gia Lai', 10, 0)
INSERT [dbo].[Orders] ([Id], [Createdate], [Address], [Account_Id], [Status]) VALUES (12, CAST(N'2022-12-08' AS Date), N'', 46, 0)
INSERT [dbo].[Orders] ([Id], [Createdate], [Address], [Account_Id], [Status]) VALUES (13, CAST(N'2022-12-08' AS Date), N'', 46, 0)
INSERT [dbo].[Orders] ([Id], [Createdate], [Address], [Account_Id], [Status]) VALUES (14, CAST(N'2022-12-08' AS Date), N'', 46, 0)
INSERT [dbo].[Orders] ([Id], [Createdate], [Address], [Account_Id], [Status]) VALUES (15, CAST(N'2022-12-08' AS Date), N'', 46, 0)
INSERT [dbo].[Orders] ([Id], [Createdate], [Address], [Account_Id], [Status]) VALUES (17, CAST(N'2022-12-08' AS Date), N'123123123', 46, 0)
INSERT [dbo].[Orders] ([Id], [Createdate], [Address], [Account_Id], [Status]) VALUES (18, CAST(N'2022-12-08' AS Date), N'', 46, 0)
INSERT [dbo].[Orders] ([Id], [Createdate], [Address], [Account_Id], [Status]) VALUES (19, CAST(N'2022-12-08' AS Date), N'', 46, 0)
INSERT [dbo].[Orders] ([Id], [Createdate], [Address], [Account_Id], [Status]) VALUES (20, CAST(N'2022-12-08' AS Date), N'', 46, 0)
INSERT [dbo].[Orders] ([Id], [Createdate], [Address], [Account_Id], [Status]) VALUES (21, CAST(N'2022-12-08' AS Date), N'123123', 46, 0)
INSERT [dbo].[Orders] ([Id], [Createdate], [Address], [Account_Id], [Status]) VALUES (22, CAST(N'2022-12-08' AS Date), N'123', 46, 0)
INSERT [dbo].[Orders] ([Id], [Createdate], [Address], [Account_Id], [Status]) VALUES (23, CAST(N'2022-12-08' AS Date), N'123123', 46, 0)
INSERT [dbo].[Orders] ([Id], [Createdate], [Address], [Account_Id], [Status]) VALUES (24, CAST(N'2022-12-08' AS Date), N'123123', 46, 0)
INSERT [dbo].[Orders] ([Id], [Createdate], [Address], [Account_Id], [Status]) VALUES (25, CAST(N'2022-12-08' AS Date), N'123123', 46, 0)
INSERT [dbo].[Orders] ([Id], [Createdate], [Address], [Account_Id], [Status]) VALUES (26, CAST(N'2022-12-08' AS Date), N'test', 46, 0)
SET IDENTITY_INSERT [dbo].[Orders] OFF
GO
SET IDENTITY_INSERT [dbo].[Products] ON 

INSERT [dbo].[Products] ([Id], [CategoryId], [Name], [Price], [Size], [Image1], [Image2], [Amount], [Color], [Active], [Quantitysold], [Describe], [Create_date]) VALUES (1, N'1   ', N'Nike Red White', 2300000, 40, N'3.jpg', N'3-3.jpg', 13, N'White,Red', 1, 10, N'Hiện đại và thời trang khi diện item mới của Nike. Màu sắc lạ mắt, chất liệu thoáng mát, nhẹ nhàng, phù hợp với những chàng trai yêu phong cách sports.', CAST(N'2022-10-10' AS Date))
INSERT [dbo].[Products] ([Id], [CategoryId], [Name], [Price], [Size], [Image1], [Image2], [Amount], [Color], [Active], [Quantitysold], [Describe], [Create_date]) VALUES (2, N'1   ', N'Nike Just for fun', 2400000, 39, N'2.jpg', N'2-2.jpg', 13, N'White,Orage', 1, 10, N'Hiện đại và thời trang khi diện item mới của Nike. Màu sắc lạ mắt, chất liệu thoáng mát, nhẹ nhàng, phù hợp với những chàng trai yêu phong cách sports.', CAST(N'2020-10-12' AS Date))
INSERT [dbo].[Products] ([Id], [CategoryId], [Name], [Price], [Size], [Image1], [Image2], [Amount], [Color], [Active], [Quantitysold], [Describe], [Create_date]) VALUES (3, N'2   ', N'Adidas Yeezy 350', 3300000, 39, N'14.jpg', N'14-14.jpg', 13, N'White', 1, 10, N'Hiện đại và thời trang khi diện item mới của Nike. Màu sắc lạ mắt, chất liệu thoáng mát, nhẹ nhàng, phù hợp với những chàng trai yêu phong cách sports.', CAST(N'2020-10-10' AS Date))
INSERT [dbo].[Products] ([Id], [CategoryId], [Name], [Price], [Size], [Image1], [Image2], [Amount], [Color], [Active], [Quantitysold], [Describe], [Create_date]) VALUES (4, N'2   ', N'Adidas Yeezy 700', 2300000, 39, N'17.jpg', N'17-17.jpg', 12, N'Gray', 1, 16, N'Hiện đại và thời trang khi diện item mới của Nike. Màu sắc lạ mắt, chất liệu thoáng mát, nhẹ nhàng, phù hợp với những chàng trai yêu phong cách sports.', CAST(N'2020-10-10' AS Date))
INSERT [dbo].[Products] ([Id], [CategoryId], [Name], [Price], [Size], [Image1], [Image2], [Amount], [Color], [Active], [Quantitysold], [Describe], [Create_date]) VALUES (5, N'1   ', N'Nike White', 2300000, 39, N'6.jpg', N'6-6.jpg', 1, N'White', 1, 12, N'Hiện đại và thời trang khi diện item mới của Nike. Màu sắc lạ mắt, chất liệu thoáng mát, nhẹ nhàng, phù hợp với những chàng trai yêu phong cách sports.', CAST(N'2020-10-10' AS Date))
INSERT [dbo].[Products] ([Id], [CategoryId], [Name], [Price], [Size], [Image1], [Image2], [Amount], [Color], [Active], [Quantitysold], [Describe], [Create_date]) VALUES (6, N'2   ', N'Adidas Sport', 2300000, 39, N'9.jpg', N'9-9.jpg', 13, N'Orange', 1, 20, N'Hiện đại và thời trang khi diện item mới của Nike. Màu sắc lạ mắt, chất liệu thoáng mát, nhẹ nhàng, phù hợp với những chàng trai yêu phong cách sports.', CAST(N'2022-11-10' AS Date))
INSERT [dbo].[Products] ([Id], [CategoryId], [Name], [Price], [Size], [Image1], [Image2], [Amount], [Color], [Active], [Quantitysold], [Describe], [Create_date]) VALUES (7, N'1   ', N'Nike Blue', 2300000, 39, N'5.jpg', N'5-5.jpg', 13, N'Blue', 1, 10, N'Hiện đại và thời trang khi diện item mới của Nike. Màu sắc lạ mắt, chất liệu thoáng mát, nhẹ nhàng, phù hợp với những chàng trai yêu phong cách sports.', CAST(N'2022-11-09' AS Date))
INSERT [dbo].[Products] ([Id], [CategoryId], [Name], [Price], [Size], [Image1], [Image2], [Amount], [Color], [Active], [Quantitysold], [Describe], [Create_date]) VALUES (8, N'2   ', N'Adidas Black', 2300000, 39, N'10.jpg', N'10-10.jpg', 13, N'Black', 1, 10, N'Hiện đại và thời trang khi diện item mới của Nike. Màu sắc lạ mắt, chất liệu thoáng mát, nhẹ nhàng, phù hợp với những chàng trai yêu phong cách sports.', CAST(N'2022-11-08' AS Date))
INSERT [dbo].[Products] ([Id], [CategoryId], [Name], [Price], [Size], [Image1], [Image2], [Amount], [Color], [Active], [Quantitysold], [Describe], [Create_date]) VALUES (9, N'2   ', N'Adidas Sport 1', 2300000, 39, N'19.jpg', N'19-19.jpg', 13, N'White,Pink', 1, 10, N'Hiện đại và thời trang khi diện item mới của Nike. Màu sắc lạ mắt, chất liệu thoáng mát, nhẹ nhàng, phù hợp với những chàng trai yêu phong cách sports.', CAST(N'2020-10-10' AS Date))
INSERT [dbo].[Products] ([Id], [CategoryId], [Name], [Price], [Size], [Image1], [Image2], [Amount], [Color], [Active], [Quantitysold], [Describe], [Create_date]) VALUES (10, N'2   ', N'Adidas Sport 2', 2300000, 39, N'18.jpg', N'18-18.jpg', 13, N'White', 1, 5, N'Hiện đại và thời trang khi diện item mới của Nike. Màu sắc lạ mắt, chất liệu thoáng mát, nhẹ nhàng, phù hợp với những chàng trai yêu phong cách sports.', CAST(N'2020-11-20' AS Date))
INSERT [dbo].[Products] ([Id], [CategoryId], [Name], [Price], [Size], [Image1], [Image2], [Amount], [Color], [Active], [Quantitysold], [Describe], [Create_date]) VALUES (11, N'2   ', N'Adidas', 1200000, 39, N'14.jpg', N'14-14.jpg', 10, N'White', 1, 11, N'Nice', CAST(N'2021-09-11' AS Date))
SET IDENTITY_INSERT [dbo].[Products] OFF
GO
SET IDENTITY_INSERT [dbo].[Rates] ON 

INSERT [dbo].[Rates] ([ID], [Stars], [Comment], [Account], [Product], [RatedDate]) VALUES (3, 5, N'test add Rate', 5, 1, CAST(N'2022-11-21T10:53:05.637' AS DateTime))
INSERT [dbo].[Rates] ([ID], [Stars], [Comment], [Account], [Product], [RatedDate]) VALUES (4, 5, N'TestComment 1', 8, 1, CAST(N'2022-11-21T15:40:46.290' AS DateTime))
INSERT [dbo].[Rates] ([ID], [Stars], [Comment], [Account], [Product], [RatedDate]) VALUES (5, 5, N'TestComment 2', 9, 1, CAST(N'2022-11-21T15:40:46.793' AS DateTime))
INSERT [dbo].[Rates] ([ID], [Stars], [Comment], [Account], [Product], [RatedDate]) VALUES (6, 5, N'TestComment 3', 6, 1, CAST(N'2022-11-21T15:40:47.297' AS DateTime))
INSERT [dbo].[Rates] ([ID], [Stars], [Comment], [Account], [Product], [RatedDate]) VALUES (7, 5, N'TestComment 1', 8, 1, CAST(N'2022-11-21T15:41:04.843' AS DateTime))
INSERT [dbo].[Rates] ([ID], [Stars], [Comment], [Account], [Product], [RatedDate]) VALUES (8, 5, N'TestComment 2', 9, 1, CAST(N'2022-11-21T15:41:05.357' AS DateTime))
INSERT [dbo].[Rates] ([ID], [Stars], [Comment], [Account], [Product], [RatedDate]) VALUES (9, 5, N'TestComment 3', 6, 1, CAST(N'2022-11-21T15:41:05.870' AS DateTime))
INSERT [dbo].[Rates] ([ID], [Stars], [Comment], [Account], [Product], [RatedDate]) VALUES (10, 5, N'TestComment 1', 8, 1, CAST(N'2022-11-21T15:41:08.100' AS DateTime))
INSERT [dbo].[Rates] ([ID], [Stars], [Comment], [Account], [Product], [RatedDate]) VALUES (11, 5, N'TestComment 2', 9, 1, CAST(N'2022-11-21T15:41:08.610' AS DateTime))
INSERT [dbo].[Rates] ([ID], [Stars], [Comment], [Account], [Product], [RatedDate]) VALUES (12, 5, N'TestComment 3', 6, 1, CAST(N'2022-11-21T15:41:09.127' AS DateTime))
INSERT [dbo].[Rates] ([ID], [Stars], [Comment], [Account], [Product], [RatedDate]) VALUES (13, 5, N'Hello', 11, 2, CAST(N'2022-11-22T18:14:07.703' AS DateTime))
INSERT [dbo].[Rates] ([ID], [Stars], [Comment], [Account], [Product], [RatedDate]) VALUES (14, 5, N'Test comment', 11, 2, CAST(N'2022-11-22T18:15:58.323' AS DateTime))
INSERT [dbo].[Rates] ([ID], [Stars], [Comment], [Account], [Product], [RatedDate]) VALUES (15, 5, N'Add 5 star to product 3', 11, 3, CAST(N'2022-11-22T18:45:23.060' AS DateTime))
INSERT [dbo].[Rates] ([ID], [Stars], [Comment], [Account], [Product], [RatedDate]) VALUES (16, 5, N'Hello', 11, 3, CAST(N'2022-11-22T18:46:05.360' AS DateTime))
INSERT [dbo].[Rates] ([ID], [Stars], [Comment], [Account], [Product], [RatedDate]) VALUES (17, 5, N'add 4 stars to product 4', 11, 2, CAST(N'2022-11-22T18:48:13.147' AS DateTime))
INSERT [dbo].[Rates] ([ID], [Stars], [Comment], [Account], [Product], [RatedDate]) VALUES (18, 1, N'Too bad', 11, 2, CAST(N'2022-11-22T19:22:28.953' AS DateTime))
INSERT [dbo].[Rates] ([ID], [Stars], [Comment], [Account], [Product], [RatedDate]) VALUES (19, 4, N'Very good !!', 11, 4, CAST(N'2022-11-22T19:24:17.140' AS DateTime))
INSERT [dbo].[Rates] ([ID], [Stars], [Comment], [Account], [Product], [RatedDate]) VALUES (20, 5, N'', 11, 4, CAST(N'2022-11-22T19:24:30.700' AS DateTime))
INSERT [dbo].[Rates] ([ID], [Stars], [Comment], [Account], [Product], [RatedDate]) VALUES (21, 3, N'test comment before commit ', 11, 2, CAST(N'2022-11-22T20:07:57.963' AS DateTime))
INSERT [dbo].[Rates] ([ID], [Stars], [Comment], [Account], [Product], [RatedDate]) VALUES (22, 5, N'Test comment', 11, 1, CAST(N'2022-11-22T21:08:06.903' AS DateTime))
INSERT [dbo].[Rates] ([ID], [Stars], [Comment], [Account], [Product], [RatedDate]) VALUES (23, 5, N'sản phẩm tốt !', 11, 8, CAST(N'2022-11-23T15:26:51.550' AS DateTime))
INSERT [dbo].[Rates] ([ID], [Stars], [Comment], [Account], [Product], [RatedDate]) VALUES (24, 5, N'adsaaaaaaaa', 11, 5, CAST(N'2022-11-24T14:06:22.183' AS DateTime))
INSERT [dbo].[Rates] ([ID], [Stars], [Comment], [Account], [Product], [RatedDate]) VALUES (25, 5, N'adsaaaaaaaa', 11, 5, CAST(N'2022-11-24T14:06:26.427' AS DateTime))
INSERT [dbo].[Rates] ([ID], [Stars], [Comment], [Account], [Product], [RatedDate]) VALUES (26, 5, N'Giay dep', 11, 6, CAST(N'2022-12-04T16:18:09.683' AS DateTime))
INSERT [dbo].[Rates] ([ID], [Stars], [Comment], [Account], [Product], [RatedDate]) VALUES (27, 1, N'Giay xau 1 sao', 11, 1, CAST(N'2022-12-05T10:29:16.763' AS DateTime))
SET IDENTITY_INSERT [dbo].[Rates] OFF
GO
INSERT [dbo].[Roles] ([Id], [Name]) VALUES (N'1   ', N'ROLE_USER')
INSERT [dbo].[Roles] ([Id], [Name]) VALUES (N'2   ', N'ROLE_MODERATOR')
INSERT [dbo].[Roles] ([Id], [Name]) VALUES (N'3   ', N'ROLE_ADMIN')
GO
ALTER TABLE [dbo].[Account_Role]  WITH CHECK ADD FOREIGN KEY([Account_Id])
REFERENCES [dbo].[Accounts] ([Id])
GO
ALTER TABLE [dbo].[Account_Role]  WITH CHECK ADD FOREIGN KEY([Role_Id])
REFERENCES [dbo].[Roles] ([Id])
GO
ALTER TABLE [dbo].[CommentImages]  WITH CHECK ADD FOREIGN KEY([CommentID])
REFERENCES [dbo].[Rates] ([ID])
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
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD FOREIGN KEY([Account_Id])
REFERENCES [dbo].[Accounts] ([Id])
GO
ALTER TABLE [dbo].[Products]  WITH CHECK ADD  CONSTRAINT [fk_htk_id_sanpham] FOREIGN KEY([CategoryId])
REFERENCES [dbo].[Categories] ([Id])
GO
ALTER TABLE [dbo].[Products] CHECK CONSTRAINT [fk_htk_id_sanpham]
GO
ALTER TABLE [dbo].[Rates]  WITH CHECK ADD FOREIGN KEY([Account])
REFERENCES [dbo].[Accounts] ([Id])
GO
ALTER TABLE [dbo].[Rates]  WITH CHECK ADD FOREIGN KEY([Product])
REFERENCES [dbo].[Products] ([Id])
GO
