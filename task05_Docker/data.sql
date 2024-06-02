INSERT INTO tpp_ref_account_type (value)
VALUES ('����������'),
       ('����������������');

INSERT INTO tpp_ref_product_class (value, gbi_code, gbi_name, product_row_code, product_row_name, subclass_code, subclass_name)
VALUES ('03.012.002', '03', '��������� ������', '012', '����. �������', '002', '��������'),
       ('02.001.005', '02', '��������� ������', '001', '�����', '005', '�������');

INSERT INTO tpp_ref_product_register_type (value
                                          , register_type_name
                                          , product_class_code
                                          , account_type)
VALUES ('03.012.002_47533_ComSoLd', '�������� ��.', '03.012.002', '����������'),
       ('02.001.005_45343_CoDowFF', '�������. �����.', '02.001.005', '����������');

INSERT INTO account_pool (branch_code
                         , currency_code
                         , mdm_code
                         , priority_code
                         , registry_type_code)
VALUES ('0022', '800', '15', '00', '03.012.002_47533_ComSoLd'),
       ('0021', '500', '13', '00', '02.001.005_45343_CoDowFF');

INSERT INTO account(account_pool_id, account_number, bussy)
SELECT id, '475335516415314841861', false
FROM account_pool
WHERE registry_type_code = '03.012.002_47533_ComSoLd';

INSERT INTO account(account_pool_id, account_number, bussy)
SELECT id, '4753321651354151', false
FROM account_pool
WHERE registry_type_code = '03.012.002_47533_ComSoLd';

INSERT INTO account(account_pool_id, account_number, bussy)
SELECT id, '4753352543276345', false
FROM account_pool
WHERE registry_type_code = '03.012.002_47533_ComSoLd';

INSERT INTO account(account_pool_id, account_number, bussy)
SELECT id, '453432352436453276', false
FROM account_pool
WHERE registry_type_code = '02.001.005_45343_CoDowFF';

INSERT INTO account(account_pool_id, account_number, bussy)
SELECT id, '45343221651354151', false
FROM account_pool
WHERE registry_type_code = '02.001.005_45343_CoDowFF';

INSERT INTO account(account_pool_id, account_number, bussy)
SELECT id, '4534352543276345', false
FROM account_pool
WHERE registry_type_code = '02.001.005_45343_CoDowFF';
