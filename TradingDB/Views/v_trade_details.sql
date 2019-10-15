USE [TradingApp]
GO

/****** Object:  View [dbo].[v_trade_details]    Script Date: 10/15/2019 10:25:02 AM ******/
DROP VIEW [dbo].[v_trade_details]
GO

/****** Object:  View [dbo].[v_trade_details]    Script Date: 10/15/2019 10:25:02 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE View [dbo].[v_trade_details] as
select t.trade_id, t.trade_type_id, ttv.type_value_name as trade_type_name, t.exchange_id, ev.type_value_name as exchange_name,
		t.stock_id, sm.stock_code as stock_name, t.quantity, t.stock_price, t.brokerage, t.GST, 
		((t.quantity * t.stock_price) + t.brokerage + t.GST) as trade_value,
		t.trade_date, t.is_active, t.created_by, t.created_date, t.updated_by, t.updated_date
  from trades t
  left join type_value_master ttv on ttv.type_value_id = t.trade_type_id
  left join type_value_master ev on ev.type_value_id = t.exchange_id
  left join stock_master sm on sm.stock_id = t.stock_id
GO


