create	schema `testbench`;
create table `test` (
	`ID` int primary key not null,
    `Product Name` varchar(45) not null unique,
    `Quantity` int default '0',
    `Price` int default '0'
);
