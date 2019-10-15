USE [TradingApp]
GO

/****** Object:  Table [dbo].[stock_master]    Script Date: 10/15/2019 10:23:04 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[stock_master](
	[stock_id] [bigint] IDENTITY(1,1) NOT NULL,
	[stock_code] [char](6) NOT NULL,
	[stock_name] [varchar](100) NOT NULL,
	[stock_desc] [varchar](1000) NULL,
	[is_active] [bit] NOT NULL CONSTRAINT [DF_StockMaster_IsActive]  DEFAULT ((1)),
	[created_by] [bigint] NOT NULL,
	[creatd_date] [datetime] NOT NULL,
	[updated_by] [bigint] NULL,
	[updated_date] [datetime] NULL,
 CONSTRAINT [PK_StockMaster] PRIMARY KEY CLUSTERED 
(
	[stock_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


