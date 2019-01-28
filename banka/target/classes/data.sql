insert into Casopis(naziv, merchant_id, merchant_password) values ('nac geo', '22', 'bbbb');
insert into Casopis(naziv, merchant_id, merchant_password) values ('politikin zabavnik', '11', 'aaaa');
insert into Casopis(naziv, merchant_id, merchant_password) values ('vreme', '33', 'cccc');
insert into Casopis(naziv, merchant_id, merchant_password) values ('politikia', '44', 'dddd');
insert into Casopis(naziv, merchant_id, merchant_password) values ('nin', '55', 'eeee');
insert into Casopis(naziv, merchant_id, merchant_password) values ('nedeljnik', '66', 'ffff');


insert into Banka(swift_kod, naziv, port) values ('BANKRS22', 'Banka_1', '9092');
insert into Banka(swift_kod, naziv, port) values ('BKKKRS22', 'Banka_2', '9093');
insert into Banka(swift_kod, naziv, port) values ('BAKKRS22', 'Banka_3', '9094');

insert into Racun(broj_racuna, vlasnik_racuna, casopis_id, banka_id, stanje_racuna, datum_vazenja, sigurnosni_kod) values ('909211111111111111', 'MILOS NISIC', 1, 1, '50000', '2020-12-20', '123');
insert into Racun(broj_racuna, vlasnik_racuna, casopis_id, banka_id, stanje_racuna, datum_vazenja, sigurnosni_kod) values ('909222222222222222', 'MILOS NISIC', 2, 1, '50000', '2020-12-20', '124');
insert into Racun(broj_racuna, vlasnik_racuna, casopis_id, banka_id, stanje_racuna, datum_vazenja, sigurnosni_kod) values ('909211011111111111', 'MILOS NISIC', 3, 1, '50000', '2020-12-20', '123');
insert into Racun(broj_racuna, vlasnik_racuna, casopis_id, banka_id, stanje_racuna, datum_vazenja, sigurnosni_kod) values ('909222220222222222', 'MILOS NISIC', 4, 1, '50000', '2020-12-20', '123');
insert into Racun(broj_racuna, vlasnik_racuna, casopis_id, banka_id, stanje_racuna, datum_vazenja, sigurnosni_kod) values ('909310111111111111', 'MILOS NISIC', 5, 2, '50000', '2020-12-20', '123');
insert into Racun(broj_racuna, vlasnik_racuna, casopis_id, banka_id, stanje_racuna, datum_vazenja, sigurnosni_kod) values ('909222222222220222', 'MILOS NISIC', 6, 1, '50000', '2020-12-20', '123');