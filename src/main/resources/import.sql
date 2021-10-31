-- Tests
insert into "TEST" ("ID", "NAME") values (NEXTVAL('"TEST_SEQ"'), 'Test 1');
insert into "TEST" ("ID", "NAME") values (NEXTVAL('"TEST_SEQ"'), 'Test 2');

-- Users
insert into "User" ("id", "email", "password", "presentation", "role", "username", "ACCOUNT_NOT_EXPIRED", "ACCOUNT_NOT_LOCKED", "CREDENTIAL_NOT_EXPIRED", "ENABLED") values (NEXTVAL('"USER_SEQ"'), 'admin@gmail.com', '$2a$12$Hp3IVrTI.3OoiBgNFUdJKe7/8rDSs5Wk8tAhUod5VIMgJS8tRpKru', 'Sono un admin', 'ADMIN', 'admin', true, true, true, true);
insert into "User" ("id", "email", "password", "presentation", "role", "username", "ACCOUNT_NOT_EXPIRED", "ACCOUNT_NOT_LOCKED", "CREDENTIAL_NOT_EXPIRED", "ENABLED") values (NEXTVAL('"USER_SEQ"'), 'gamer@gmail.com', '$2a$12$SR1sVc2woMOWcBpHbYDI4.TIhmVmN0utFN48gTRI8XFW17Rn88dda', 'Sono un gamer', 'GAMER', 'gamer', true, true, true, true);

--Ranks
insert into "Rank"("id","gameType","user_id","score") values (NEXTVAL('"RANK_SEQ"'), 'MEMORY', 1,5);
insert into "Rank"("id","gameType","user_id","score") values (NEXTVAL('"RANK_SEQ"'), 'MINEFIELD', 1,15);
insert into "Rank"("id","gameType","user_id","score") values (NEXTVAL('"RANK_SEQ"'), 'MINEFIELD', 1,25);
insert into "Rank"("id","gameType","user_id","score") values (NEXTVAL('"RANK_SEQ"'), 'MINEFIELD', 1,35);
insert into "Rank"("id","gameType","user_id","score") values (NEXTVAL('"RANK_SEQ"'), 'MINEFIELD', 1,45);

--MinefieldInstance
insert into "MinefieldInstance"("id","columns","bombs","treasures","life","name","level") values (NEXTVAL('"MINEFIELD_SEQ"'),5,5,5,5,'Configurazione di Prova1','EASY');
insert into "MinefieldInstance"("id","columns","bombs","treasures","life","name","level") values (NEXTVAL('"MINEFIELD_SEQ"'),7,7,7,7,'Configurazione di Prova2','MEDIUM');

--MemoryInstances
insert into "MemoryInstance"("id","name","columns","rows","maxErrors","victoryPoints", "timer") values (NEXTVAL('"MEMORY_SEQ"'),'Senza Tempo',5,6,15,1,0);
insert into "MemoryInstance"("id","name","columns","rows","maxErrors","victoryPoints", "timer") values (NEXTVAL('"MEMORY_SEQ"'),'Tik-Tak-Tik-Tak',2,3,3,3,9000);
insert into "MemoryInstance"("id","name","columns","rows","maxErrors","victoryPoints", "timer") values (NEXTVAL('"MEMORY_SEQ"'),'20 Carte!',5,4,10,2,29000);
insert into "MemoryInstance"("id","name","columns","rows","maxErrors","victoryPoints", "timer") values (NEXTVAL('"MEMORY_SEQ"'),'Con Calma!',6,6,18,1,51000);
insert into "MemoryInstance"("id","name","columns","rows","maxErrors","victoryPoints", "timer") values (NEXTVAL('"MEMORY_SEQ"'),'Il Quadrato',4,4,8,4,23000);
insert into "MemoryInstance"("id","name","columns","rows","maxErrors","victoryPoints", "timer") values (NEXTVAL('"MEMORY_SEQ"'),'Tester',2,1,100,100,3000);
