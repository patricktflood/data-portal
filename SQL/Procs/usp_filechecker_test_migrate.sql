USE [pf_data]
GO
/****** Object:  StoredProcedure [dbo].[usp_filechecker_test_migrate]    Script Date: 03/10/2014 22:12:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER procedure [dbo].[usp_filechecker_test_migrate]

as

truncate table [pf_data].dbo.[filechecker_test]

insert into [pf_data].dbo.[filechecker_test]
(
	 [ID]
	,[first_name]
	,[last_name]
	,[dob]
)
select
	 isnull([ID],'')
	,isnull([first_name],'')
	,isnull([last_name],'')
	,isnull([dob],'')
from [pf_data].dbo.[filechecker_test_temp]

delete from [pf_data].dbo.[filechecker_test] where [ID] is null

truncate table [pf_data].dbo.[filechecker_test_temp]