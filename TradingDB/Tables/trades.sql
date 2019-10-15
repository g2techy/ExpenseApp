USE [TradingApp]
GO

/****** Object:  Table [dbo].[trades]    Script Date: 10/15/2019 10:23:20 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[trades](
	[trade_id] [bigint] IDENTITY(1,1) NOT NULL,
	[trade_type_id] [bigint] NOT NULL,
	[exchange_id] [bigint] NOT NULL,
	[stock_id] [bigint] NOT NULL,
	[quantity] [bigint] NOT NULL,
	[stock_price] [decimal](10, 2) NOT NULL,
	[brokerage] [decimal](10, 2) NOT NULL,
	[GST] [decimal](10, 2) NOT NULL,
	[trade_date] [datetime] NOT NULL,
	[is_active] [bit] NOT NULL CONSTRAINT [DF_Trades_IsActive]  DEFAULT ((1)),
	[created_by] [bigint] NOT NULL,
	[created_date] [datetime] NOT NULL CONSTRAINT [DF_Trades_CreatedDate]  DEFAULT (getdate()),
	[updated_by] [bigint] NULL,
	[updated_date] [datetime] NULL
) ON [PRIMARY]

GO


