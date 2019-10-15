USE [TradingApp]
GO

/****** Object:  View [dbo].[v_expense_tag_details]    Script Date: 10/15/2019 10:24:34 AM ******/
DROP VIEW [dbo].[v_expense_tag_details]
GO

/****** Object:  View [dbo].[v_expense_tag_details]    Script Date: 10/15/2019 10:24:34 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create view [dbo].[v_expense_tag_details] as 
select et.*, tm.type_value_name as tag_name
  from expense_tags et
  left join type_value_master tm on tm.type_value_id = et.tag_id
GO


