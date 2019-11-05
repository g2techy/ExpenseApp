USE [TradingApp]
GO

/****** Object:  Table [dbo].[type_master]    Script Date: 10/15/2019 10:23:35 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[type_master](
	[type_id] [bigint] NOT NULL,
	[type_name] [varchar](50) NOT NULL,
	[is_active] [bit] NOT NULL CONSTRAINT [DF_TypeMaster_IsActive]  DEFAULT ((1)),
	[created_by] [bigint] NOT NULL,
	[created_date] [datetime] NOT NULL,
	[updated_by] [bigint] NULL,
	[updated_date] [datetime] NULL,
 CONSTRAINT [PK_TypeMaster] PRIMARY KEY CLUSTERED 
(
	[type_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


