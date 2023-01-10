INSERT INTO delivery_point (create_date, update_date, name, value)
VALUES (current_date, null, 'Branch', 1),
       (current_date, null, 'Distribution Centre', 2),
       (current_date, null, 'Transfer Centre', 3);

INSERT INTO sack (create_date, update_date, barcode, delivery_point, state)
VALUES (current_date, null, 'C725799', 2, 1),
       (current_date, null, 'C725800', 3, 1);

INSERT INTO package (create_date, update_date, barcode, delivery_point, state, desi,sack_id)
VALUES (current_date, null, 'P7988000121', 1, 1, 5, null),
       (current_date, null, 'P7988000122', 1, 1, 5, null),
       (current_date, null, 'P7988000123', 1, 1, 9, null),
       (current_date, null, 'P8988000120', 2, 1, 33, null),
       (current_date, null, 'P8988000121', 2, 1, 17, null),
       (current_date, null, 'P8988000122', 2, 1, 26, 1),
       (current_date, null, 'P8988000123', 2, 1, 35, null),
       (current_date, null, 'P8988000124', 2, 1, 1, null),
       (current_date, null, 'P8988000125', 2, 1, 200, null),
       (current_date, null, 'P8988000126', 2, 1, 50, 1),
       (current_date, null, 'P9988000126', 3, 1, 15, null),
       (current_date, null, 'P9988000127', 3, 1, 16, null),
       (current_date, null, 'P9988000128', 3, 1, 55, 2),
       (current_date, null, 'P9988000129', 3, 1, 28, 2),
       (current_date, null, 'P9988000130', 3, 1, 17, null);

INSERT INTO sack_packages (sack_sack_id, packages_package_id)
VALUES (1, 6),
       (1, 10),
       (2, 13),
       (2, 14);