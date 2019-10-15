USE [TradingApp]
GO

/****** Object:  Table [dbo].[expense_tags]    Script Date: 10/15/2019 10:22:17 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[expense_tags](
	[expense_tag_id] [bigint] IDENTITY(1,1) NOT NULL,
	[expense_id] [bigint] NOT NULL,
	[tag_id] [bigint] NOT NULL,
	[created_by] [bigint] NOT NULL,
	[created_date] [datetime] NOT NULL,
	[updated_by] [bigint] NULL,
	[updated_date] [datetime] NULL
) ON [PRIMARY]

GO


