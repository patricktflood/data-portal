USE [pf_data]
GO
/****** Object:  StoredProcedure [dbo].[usp_filechecker_test_verify_data]    Script Date: 03/10/2014 22:12:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER procedure [dbo].[usp_filechecker_test_verify_data]
	--@appl char(100) output,
	--@ID char(100) output,
	--@value char(255) output,
	--@data_error_desc char(200) output,
	--@data_check_rule int output
as

SET NOCOUNT ON

truncate table [pf_data].dbo.[file_data_errors]
	
declare @Campaign_code varchar(128)
set @Campaign_code = 'TEST'


-- 1
	insert into [pf_data].dbo.[file_data_errors]
	select @Campaign_code, [ID], [ID], '[ID] must be a number' as '[ID] must be a number', 1
	from [pf_data].dbo.[filechecker_test]
	where [ID] like '[A-Z]'
	
-- 2
	insert into [pf_data].dbo.[file_data_errors]
	select @Campaign_code, [ID], [first_name], '[first_name] must start with an upper case letter' as '[first_name] must start with an upper case letter', 2
	from [pf_data].dbo.[filechecker_test]
	where substring([first_name],1,1) collate latin1_general_cs_as <> upper(substring([first_name],1,1)) collate latin1_general_cs_as

-- 3
	insert into [pf_data].dbo.[file_data_errors]
	select @Campaign_code, [ID], [last_name], '[last_name] is mandatory' as '[last_name] is mandatory', 3
	from [pf_data].dbo.[filechecker_test]
	where [last_name] = ''

-- 4
	insert into [pf_data].dbo.[file_data_errors]
	select @Campaign_code, [ID], [DOB], '[DOB] must be in dd/mm/yyyy format' as '[DOB] must be in dd/mm/yyyy format', 4
	from [pf_data].dbo.[filechecker_test]
	where [DOB] not like '[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]'

	--exec [pf_data].dbo.usp_Email_Paper_Data_Errors @Campaign_code
		
--select 
--	@appl = appl,
--	@ID = ID,
--	@value = value,
--	@data_error_desc = data_error_desc,
--	@data_check_rule = data_check_rule
--from [pf_data].dbo.[file_data_errors] where appl = @Campaign_code 	

select 
	ltrim(rtrim(appl)) as appl,
	ltrim(rtrim(ID)) as ID,
	ltrim(rtrim(value)) as value,
	ltrim(rtrim(data_error_desc)) as data_error_desc,
	ltrim(rtrim(data_check_rule)) as data_check_rule
from [pf_data].dbo.[file_data_errors] where appl = @Campaign_code 

