USE [TradingApp]
GO

/****** Object:  Table [dbo].[type_value_master]    Script Date: 10/15/2019 10:23:51 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[type_value_master](
	[type_value_id] [bigint] IDENTITY(1,1) NOT NULL,
	[type_value_name] [varchar](50) NOT NULL,
	[type_id] [bigint] NOT NULL,
	[parent_type_value_id] [bigint] NOT NULL CONSTRAINT [DF_TypeValueMaster_ParentTypeValueID]  DEFAULT ((0)),
	[is_active] [bit] NOT NULL CONSTRAINT [DF_TypeValueMaster_IsActive]  DEFAULT ((1)),
	[created_by] [bigint] NOT NULL,
	[created_date] [datetime] NOT NULL,
	[updated_by] [bigint] NULL,
	[updated_date] [datetime] NULL,
 CONSTRAINT [PK_TypeValueMaster] PRIMARY KEY CLUSTERED 
(
	[type_value_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[type_value_master]  WITH CHECK ADD  CONSTRAINT [FK_TypeValueMaster_TypeValueMaster] FOREIGN KEY([type_value_id])
REFERENCES [dbo].[type_value_master] ([type_value_id])
GO

ALTER TABLE [dbo].[type_value_master] CHECK CONSTRAINT [FK_TypeValueMaster_TypeValueMaster]
GO


