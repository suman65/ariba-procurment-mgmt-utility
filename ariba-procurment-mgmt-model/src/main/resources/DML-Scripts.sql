INSERT INTO public.roles(id,description,name) VALUES (1, 'Admin', 'ROLE_ADMIN');
INSERT INTO public.roles(id,description,name) VALUES (2, 'Consumer', 'ROLE_USER');

INSERT INTO users (id,username, country, first_name,last_name, password, is_Enabled,is_otp_validated) VALUES
    (1,'admin', 'India', 'Admin','Shri', '$2a$10$Z5F2Elzpnwx6kp0CYLmdo.Tcv8SZWMANvlr/PWr6.IxWWXnAi7KNC',true,false),
	(2,'user', 'India', 'Use','Sow', '$2a$10$KFsPE4H9buyijUht5nQU..qdpkDRA5q6zPeACtLVWAaDh3kMzPxXG', true,true),
   	(3,'suman', 'India', 'Suman' ,'Tippu', '$2a$10$Z5F2Elzpnwx6kp0CYLmdo.Tcv8SZWMANvlr/PWr6.IxWWXnAi7KNC',true,true),
    (4,'suman1', 'India', 'Suman1','Tippu1', '$2a$10$Z5F2Elzpnwx6kp0CYLmdo.Tcv8SZWMANvlr/PWr6.IxWWXnAi7KNC',true,true);
	
INSERT INTO USER_AUTHORITY (USER_ID, ROLE_ID) VALUES (1, 1);
INSERT INTO USER_AUTHORITY (USER_ID, ROLE_ID) VALUES (2, 2);


