USE [TradingApp]
GO

/****** Object:  Table [dbo].[expenses]    Script Date: 10/15/2019 10:22:47 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[expenses](
	[expense_id] [bigint] IDENTITY(1,1) NOT NULL,
	[expense_name] [varchar](100) NOT NULL,
	[expense_date] [datetime] NOT NULL,
	[expense_amount] [decimal](18, 0) NOT NULL,
	[category_id] [bigint] NOT NULL,
	[is_expense] [bit] NOT NULL CONSTRAINT [DF_expenses_is_expense]  DEFAULT ((1)),
	[expense_desc] [varchar](1000) NULL,
	[fund_source_id] [int] NOT NULL,
	[is_active] [bit] NOT NULL CONSTRAINT [DF_expenses_is_active]  DEFAULT ((1)),
	[created_by] [bigint] NOT NULL,
	[created_date] [datetime] NOT NULL,
	[updated_by] [bigint] NULL,
	[updated_date] [datetime] NULL
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


