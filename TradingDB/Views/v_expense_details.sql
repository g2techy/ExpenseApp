USE [TradingApp]
GO

/****** Object:  View [dbo].[v_expense_details]    Script Date: 10/15/2019 10:24:49 AM ******/
DROP VIEW [dbo].[v_expense_details]
GO

/****** Object:  View [dbo].[v_expense_details]    Script Date: 10/15/2019 10:24:49 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO



CREATE view [dbo].[v_expense_details] as 
select ed.expense_id, ed.expense_name, ed.expense_date, ed.expense_amount, ed.category_id,
	   cvm.type_value_name as category_name, ed.is_expense, ed.expense_desc, ed.fund_source_id,
	   fsm.type_value_name as fund_source_name, 
	   isnull(STUFF((SELECT ';' + td.tag_name FROM v_expense_tag_details td
			Where td.expense_id = ed.expense_id FOR XML PATH('')),1,1,''), '') as expense_tags,
	   ed.is_active, ed.created_by, ed.created_date, ed.updated_by, ed.updated_date
  from expenses ed
  left join type_value_master cvm on cvm.type_value_id = ed.category_id
  left join type_value_master fsm on fsm.type_value_id = ed.fund_source_id

GO


