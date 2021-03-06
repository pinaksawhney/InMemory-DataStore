## REDIS-Like In-Memory Data Store

#### Basic Commands

Data store server should accept the following commands:

- SET key value – Set a data store entry with variable key and value. Neither the key nor value will contain spaces.
- GET key – Print out the value of the variable key, or NULL if that variable is not set.
- UNSET key – Unset the variable key, making it just like that variable was never set.
- NUMEQUALTO value – Print out the number of entries that are currently set to value. If no entries equal that value, print 0.
- END – Exit the program. Your program will always receive this as its last command.

Example command sequences:

#### INPUT OUTPUT


SET ex 10 10

GET ex

UNSET ex

GET ex NULL

END

INPUT OUTPUT


SET a 10

SET b 10

NUMEQUALTO 10 2

NUMEQUALTO 20 0

SET b 30

NUMEQUALTO 10 1

END

#### Transaction Commands

- BEGIN: Open a new transaction block. Transaction blocks can be nested; a BEGIN can be issued inside of an existing block.
- ROLLBACK – Undo all of the commands issued in the current transaction block, and close the block. Print nothing if successful, or print NO TRANSACTION if no transaction is in progress.
- COMMIT – Close all open transaction blocks, permanently applying the changes made in them. Print nothing if successful, or print NO TRANSACTION if no transaction is in progress. Any data commands that run outside of a transaction block should commit immediately.

Example command sequences:

#### INPUT OUTPUT


BEGIN

SET a 10

GET a 10

BEGIN

SET a 20

GET a 20


ROLLBACK

GET a 10

ROLLBACK

GET a NULL

END

#### INPUT OUTPUT


BEGIN

SET a 30

BEGIN

SET a 40

COMMIT

GET a 40

ROLLBACK NO TRANSACTION

END


#### INPUT OUTPUT


SET a 50

BEGIN

GET a 50

SET a 60

BEGIN

UNSET a

GET a NULL

ROLLBACK

GET a 60

COMMIT

GET a 60

END

#### INPUT OUTPUT


SET a 10

BEGIN

NUMEQUALTO 10 1

BEGIN

UNSET a

NUMEQUALTO 10 0

ROLLBACK

NUMEQUALTO 10 1

COMMIT

END
