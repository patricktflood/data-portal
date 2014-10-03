USE [pf_data]
GO

/****** Object:  Table [dbo].[file_data_errors]    Script Date: 03/10/2014 21:47:40 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[file_data_errors](
	[appl] [char](100) NULL,
	[ID] [char](100) NULL,
	[Value] [char](255) NULL,
	[Data_Error_Desc] [char](200) NULL,
	[Data_Check_Rule] [int] NULL
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


